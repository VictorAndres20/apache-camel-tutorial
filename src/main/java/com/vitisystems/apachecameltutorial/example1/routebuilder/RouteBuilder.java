package com.vitisystems.apachecameltutorial.example1.routebuilder;

import com.vitisystems.apachecameltutorial.example1.pojos.DataFormat;
import com.vitisystems.apachecameltutorial.example1.processors.ProcessorAPI;
import com.vitisystems.apachecameltutorial.example1.processors.ProcessorGetData;
import com.vitisystems.apachecameltutorial.example1.processors.ProcessorSetData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RouteBuilder extends org.apache.camel.builder.RouteBuilder{
    
    /** This is the POJO that getted from the JSON response */
    private JacksonDataFormat dataFormat;

    public RouteBuilder() {
        this.dataFormat=new JacksonDataFormat(DataFormat.class);
    }    
    
    @Override
    public void configure(){
        this.consumeAPIParamsURL();
    }
    
    public void consumeAPIParamsURL()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .process(exchange->{
                    exchange.getOut().setHeader("param1", "CO");
                })
                .to("direct:consumeAPI")
                .end();
        
        this.from("direct:consumeAPI")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .recipientList(simple("http://services.groupkt.com/country/get/iso2code/${header.param1}"))
                .log("${body}")
                .end();
    }
    
    public void consumeAPI()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .to("direct:consumeAPI")
                .end();
        
        this.from("direct:consumeAPI")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://services.groupkt.com/country/get/all")
                //.log("${body}")
                .unmarshal(this.dataFormat)
                .process(new ProcessorAPI())
                .end();
    }
    
    public void exchangeObjects()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .process(new ProcessorSetData())
                .to("direct:otraRuta")
                .end();
        
        this.from("direct:otraRuta")
                .process(new ProcessorGetData())
                .end();
    }
    
    public void usingProcessorAndExchange()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Body de mensaje de entrada ---> "+exchange.getIn().getBody());
                        System.out.println("HEaders de mensaje de entrada ---> "+exchange.getIn().getHeader("Algun nombre"));
                        exchange.getOut().setBody("Este es el body desde exchange");
                        exchange.getOut().setHeader("Header1", "Valor cabecera 1");
                    }
                })
                .to("direct:otraRuta")
                .end();
        
        this.from("direct:otraRuta")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Body de mensaje de entrada ---> "+exchange.getIn().getBody());
                        System.out.println("Headers de mensaje de entrada ---> "+exchange.getIn().getHeader("Header1"));

                    }
                })
                .end();
    }
    
    public void settingHeadersBodyToOther()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .setHeader("Header1", constant("Valor header 1"))
                .setHeader("HEader2", constant("valor header 2"))
                .setBody(constant("Esto es el body que le llega a otraRuta"))
                .to("direct:otraRuta")
                .end();
        
        this.from("direct:otraRuta")
                .log("Procesamiento lllamado")
                .log("El body es -> ${body} // Los headers son -> ${header.Header1} // ${header.Header2}")
                .end();
    }
    
    public void llamadoAOtraRuta()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .to("direct:otraRuta")
                .end();
        
        /** 
         * Otra ruta a ser llamada
         * Nótese que esta lleva como componente Camel direct, 
         * ya que va a ser una ruta DIRECTA
         */
        this.from("direct:otraRuta")
                .log("Procesamiento lllamado")
                .end();
    }
    
    public void flujoSegundo()
    {
        this.from("timer:simple?period=1000")
                .log("Process...")
                .end();
    }   
}

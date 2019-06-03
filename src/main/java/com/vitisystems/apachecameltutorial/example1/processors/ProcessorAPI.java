package com.vitisystems.apachecameltutorial.example1.processors;

import com.vitisystems.apachecameltutorial.example1.pojos.DataFormat;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessorAPI implements Processor{    

    @Override
    public void process(Exchange exchange) throws Exception {
        /** Get the POJO unmarshalled from RouteBuilder */
        /* EJMEPLO DE NUEVA LINEA APRNEDIENDO GIT BIEN */
        DataFormat df=exchange.getIn().getBody(DataFormat.class);
        if(df==null)
        {
            System.err.println("Data format nulo");
        }
        else
        {
            System.out.println(df.getRestResponse().getResult());
        }
    }  
    
    public void examplegit(){
        System.out.println("Hola");
    }
    
    public void examplegit3(){
        System.out.println("Hola");
    }
}

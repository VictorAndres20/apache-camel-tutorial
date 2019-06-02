package com.vitisystems.apachecameltutorial.example1.processors;

import com.vitisystems.apachecameltutorial.example1.pojos.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessorGetData implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        Person person =exchange.getIn().getBody(Person.class);
        if(person==null)
        {
            System.out.println("NULO");
        }
        else
        {
            System.out.println(person.getNombre());
        }        
    }
}

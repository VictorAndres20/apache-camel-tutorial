package com.vitisystems.apachecameltutorial.example1.processors;

import com.vitisystems.apachecameltutorial.example1.pojos.Person;
import java.sql.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessorSetData implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getOut().setBody(new Person("Pedro",28,new Date(123)));
    }    
}

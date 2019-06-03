package com.vitisystems.apachecameltutorial.example1.main;
import com.vitisystems.apachecameltutorial.example1.routebuilder.RouteBuilder;

public class Main {
    
    public static void main(String... args) throws Exception
    {
        org.apache.camel.main.Main main=new org.apache.camel.main.Main();
        main.addRouteBuilder(new RouteBuilder());
        main.run(args);
    }
}

package com.Haborer.rest;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("com.Haborer.rest");
    }
}

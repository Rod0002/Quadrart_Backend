package com.quadrart.Storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    
    private String location = "./uploadImagens";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}

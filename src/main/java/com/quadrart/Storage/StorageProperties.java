package com.quadrart.Storage;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * Cria uma classe de propriedades do Storage, definindo por exemplo
 * o local onde essa pasta irá se localizar.
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    
    @Value("${path.fileSystem}")
    private String location;
=======
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    
    private String location = "./uploadImagens";
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

    public String getLocation() {
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}

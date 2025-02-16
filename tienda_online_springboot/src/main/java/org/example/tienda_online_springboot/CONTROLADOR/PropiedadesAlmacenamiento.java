package org.example.tienda_online_springboot.CONTROLADOR;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
@Component
public class PropiedadesAlmacenamiento {

    private String location = "directorio-subidas";

    public String getUbicacion(){
        return location;
    }

    public void setUbicacion(String location){
        this.location=location;
    }
}

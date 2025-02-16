package org.example.tienda_online_springboot.CONTROLADOR;

public class ExcepcionFicheroNoEncontrado extends ExcepcionAlmacenamiento{

    public ExcepcionFicheroNoEncontrado(String mensaje) {
        super(mensaje);
    }

    public ExcepcionFicheroNoEncontrado(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

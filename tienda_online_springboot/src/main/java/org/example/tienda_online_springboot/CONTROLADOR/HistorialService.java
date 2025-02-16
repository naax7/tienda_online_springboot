package org.example.tienda_online_springboot.CONTROLADOR;

import org.example.tienda_online_springboot.MODELO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistorialService {

    HistorialRepository historialRepository;
    ClienteRepository clienteRepository;
    ProductoRepository productoRepository;

    @Autowired
    public HistorialService(HistorialRepository historialRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.historialRepository = historialRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    public HistorialService(){}

    public List<Historial> findAll(){
        return historialRepository.findAll();
    }

    public Historial findById(Long id){
        return historialRepository.findById(id).orElseThrow();
    }

    public Historial insert(Historial historial){
        Cliente cliente = null;
        Producto producto = null;
        try{
            cliente = clienteRepository.findById(historial.getCliente().getId()).orElseThrow();
            producto = productoRepository.findById(historial.getProducto().getId()).orElseThrow();
            historial.setCliente(cliente);
            historial.setProducto(producto);
        }catch(NoSuchElementException e){
            throw new RuntimeException("No se encontró el cliente o el producto");
        }
        if(historial.getTipo().equalsIgnoreCase("Compra")){
            if (producto.getStock() >= historial.getCantidad()){
                producto.setStock(producto.getStock() - historial.getCantidad());
                productoRepository.save(producto);
            }else{
                throw new RuntimeException("No hay suficiente stock");
            }
        }
        if(historial.getTipo().equalsIgnoreCase("devolucion")){
            if(historial.getFechaCompra().datesUntil(LocalDate.now()).count() <= 30){
                producto.setStock(producto.getStock() + historial.getCantidad());
                productoRepository.save(producto);
            }else{
                throw new RuntimeException("Han pasasdo más de 30 días");
            }
        }
        return historialRepository.save(historial);
    }

    public Historial update(Historial historial){
        try{
            historialRepository.findById(historial.getId()).orElseThrow();
        }catch(NoSuchElementException e){
            throw new RuntimeException("No existe ese historial");
        }
        return historialRepository.save(historial);
    }

    public String delete(Long id){
        String mensaje = "Historial con id: " + id + " eliminado";
        try{
            historialRepository.findById(id).orElseThrow();
            historialRepository.deleteById(id);
        }catch (NoSuchElementException e){
            mensaje = "El historial con id: " + id + " no existe";
        }
        return mensaje;
    }
}

package org.example.tienda_online_springboot.CONTROLADOR;

import org.example.tienda_online_springboot.MODELO.Producto;
import org.example.tienda_online_springboot.MODELO.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService {

    ProductoRepository productoRepository;
    ServicioAlmacenamiento servicioAlmacenamiento;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, ServicioAlmacenamiento servicioAlmacenamiento) {
        this.productoRepository = productoRepository;
        this.servicioAlmacenamiento = servicioAlmacenamiento;
    }

    public ProductoService(){}

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(Long id){
        return productoRepository.findById(id).orElseThrow();
    }

    public Producto insert(Producto producto){

        if(productoRepository.findProductoByNombre(producto.getNombre()) != null){
            throw new RuntimeException("El nombre ya existe");
        }

        if (producto.getPrecio().doubleValue() < 10 || producto.getPrecio().doubleValue() == 0){
            producto.setDescripcion(producto.getDescripcion()+" - Producto de oferta");
        }
        if (producto.getPrecio().doubleValue() > 200){
            producto.setDescripcion(producto.getDescripcion()+" - Producto de calidad");
        }
        return productoRepository.save(producto);
    }

    public Producto insert (Producto producto, MultipartFile imagen){
        servicioAlmacenamiento.store(imagen);
        producto.setImagen_url(servicioAlmacenamiento.load(imagen.getOriginalFilename()).toString());
        return insert(producto);
    }

    public Producto update(Producto producto){
        try{
            productoRepository.findById(producto.getId()).orElseThrow();
        }catch(NoSuchElementException e) {
            throw new RuntimeException("No existe ese producto");
        }
        producto.setDescripcion(producto.getDescripcion().replaceAll(" - Producto de calidad", "").replaceAll(" - Producto de oferta", ""));
        if (producto.getPrecio().doubleValue() < 10 || producto.getPrecio().doubleValue() == 0){
            producto.setDescripcion(producto.getDescripcion()+" - Producto de oferta");
        }
        if (producto.getPrecio().doubleValue() > 200){
            producto.setDescripcion(producto.getDescripcion()+" - Producto de calidad");
        }
        return productoRepository.save(producto);
    }

    public Producto update (Producto producto, MultipartFile imagen){
        servicioAlmacenamiento.store(imagen);
        producto.setImagen_url(servicioAlmacenamiento.load(imagen.getOriginalFilename()).toString());
        return update(producto);
    }

    public String delete(Long id) {
        String mensaje = "Producto con id: " + id + " eliminado";
        try {
            productoRepository.findById(id).orElseThrow();
            productoRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            mensaje = "El producto con id: " + id + " no existe";
        }
        return mensaje;
    }
}

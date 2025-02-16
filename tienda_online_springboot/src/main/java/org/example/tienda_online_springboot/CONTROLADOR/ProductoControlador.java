package org.example.tienda_online_springboot.CONTROLADOR;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.example.tienda_online_springboot.MODELO.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("producto")
//@CacheConfig(cacheNames = "productos")
public class ProductoControlador {

    ProductoService productoService;
    ObjectMapper mapper;

    @Autowired
    public ProductoControlador(ProductoService productoService, ObjectMapper mapper) {
        this.productoService = productoService;
        this.mapper = mapper;
    }

    public ProductoControlador(){}

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    //@Cacheable
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Producto> insert(@Valid @RequestBody Producto producto){
        return ResponseEntity.ok(productoService.insert(producto));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Producto> insert(@RequestPart("producto") @Valid String json, @RequestPart("imagen") MultipartFile imagen) throws JsonProcessingException {
        Producto producto = mapper.treeToValue(mapper.readTree(json), Producto.class);
        return ResponseEntity.ok(productoService.insert(producto, imagen));
    }

    @PutMapping
    public ResponseEntity<Producto> update(@Valid @RequestBody Producto producto){
        return ResponseEntity.ok(productoService.update(producto));
    }

    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<Producto> update(@RequestPart("producto") @Valid String json, @RequestPart("imagen") MultipartFile imagen) throws JsonProcessingException {
        Producto producto = mapper.treeToValue(mapper.readTree(json), Producto.class);
        return ResponseEntity.ok(productoService.update(producto, imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(productoService.delete(id));
    }
}

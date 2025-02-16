package org.example.tienda_online_springboot.CONTROLADOR;

import jakarta.validation.Valid;
import org.example.tienda_online_springboot.MODELO.Historial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
//@CacheConfig(cacheNames = "historiales")
public class HistorialControlador {
    HistorialService historialService;

    @Autowired
    public HistorialControlador(HistorialService historialService) {
        this.historialService = historialService;
    }

    public HistorialControlador(){}

    @GetMapping
    public ResponseEntity<List<Historial>> findAll(){
        return ResponseEntity.ok(historialService.findAll());
    }

    @GetMapping("/{id}")
    //@Cacheable
    public ResponseEntity<Historial> findById(@PathVariable Long id){
        return ResponseEntity.ok(historialService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Historial> insert(@Valid @RequestBody Historial historial){
        return ResponseEntity.ok(historialService.insert(historial));
    }

    @PutMapping
    public ResponseEntity<Historial> update(@Valid @RequestBody Historial historial){
        return ResponseEntity.ok(historialService.update(historial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(historialService.delete(id));
    }
}

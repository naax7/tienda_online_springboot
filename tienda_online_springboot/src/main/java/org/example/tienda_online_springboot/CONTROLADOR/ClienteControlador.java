package org.example.tienda_online_springboot.CONTROLADOR;

import jakarta.validation.Valid;
import org.example.tienda_online_springboot.MODELO.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
//@CacheConfig(cacheNames = "clientes")
public class ClienteControlador {
    ClienteService clienteService;

    @Autowired
    public ClienteControlador(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    public ClienteControlador(){}

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    //@Cacheable
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.insert(cliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.delete(id));
    }
}

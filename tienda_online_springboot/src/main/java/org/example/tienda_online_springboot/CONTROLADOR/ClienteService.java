package org.example.tienda_online_springboot.CONTROLADOR;

import org.example.tienda_online_springboot.MODELO.Cliente;
import org.example.tienda_online_springboot.MODELO.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {
    ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public ClienteService(){}

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElseThrow();
    }

    public Cliente insert(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente){
        try{
            clienteRepository.findById(cliente.getId()).orElseThrow();
        }catch(NoSuchElementException e){
            throw new RuntimeException("No existe ese cliente");
        }
        return clienteRepository.save(cliente);
    }

    public String delete(Long id){
        String mensaje = "Cliente con id: " + id + " eliminado";
        try{
            clienteRepository.findById(id).orElseThrow();
            clienteRepository.deleteById(id);
        }catch(NoSuchElementException e){
            mensaje = "El cliente con id: " + id + " no existe";
        }
        return mensaje;
    }
}

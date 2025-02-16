package org.example.tienda_online_springboot.MODELO;

import jakarta.validation.constraints.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findProductoByNombre(@Size(max = 100) @NotNull @NotBlank @Pattern(regexp = "[A-Za-zÁÉÍÓÚÜáéíóúü ]{1,100}", message = "El nombre tiene un formato obligatorio de 1 a 100 caracteres alfabéticos") String nombre);
}

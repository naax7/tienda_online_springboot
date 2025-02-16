package org.example.tienda_online_springboot.MODELO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Column(name = "nombre", nullable = false, length = 100)
    @Pattern(regexp = "[A-Za-z0-9ÁÉÍÓÚÜáéíóúü ]{1,100}", message = "El nombre tiene un formato obligatorio de 1 a 100 caracteres alfanuméricos")
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    @NotBlank
    @NotEmpty
    private String descripcion;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private BigDecimal precio;

    @NotNull
    @Column(name = "stock", nullable = false)
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    @Column(name = "imagen_url")
    private String imagen_url;

    @OneToMany(mappedBy = "producto")
    /*@JsonIgnore*/
    /*@JsonBackReference("producto")*/
    /*@JsonManagedReference("producto")*/
    @JsonIgnoreProperties({"producto"})
    private Set<Historial> historials = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public Set<Historial> getHistorials() {
        return historials;
    }

    public void setHistorials(Set<Historial> historials) {
        this.historials = historials;
    }

}
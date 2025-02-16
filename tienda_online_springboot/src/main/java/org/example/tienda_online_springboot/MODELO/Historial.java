package org.example.tienda_online_springboot.MODELO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "historial")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    /*@JsonManagedReference("cliente")*/
    /*@JsonManagedReference*/
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "historials"})
    /*@JsonBackReference("cliente")*/
    private Cliente cliente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "producto_id", nullable = false)
    /*@JsonManagedReference("producto")*/
    /*@JsonManagedReference*/
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "historials"})
    /*@JsonBackReference("producto")*/
    private org.example.tienda_online_springboot.MODELO.Producto producto;

    @NotNull
    @Column(name = "fecha_compra", nullable = false)
    @PastOrPresent(message = "La fecha de compra solo puede ser actual o anterior")
    private LocalDate fechaCompra;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "cantidad")
    @Min(value = 1, message = "Mínimo tienes que comprar una cosa")
    private Integer cantidad;

    @NotBlank
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "(Compra|Devolucion)", message = "El tipo de historial solo puede ser Compra o Devolucion")
    @Column(name = "tipo", length = 100)
    private String tipo;

    @NotBlank
    @NotEmpty
    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public org.example.tienda_online_springboot.MODELO.Producto getProducto() {
        return producto;
    }

    public void setProducto(org.example.tienda_online_springboot.MODELO.Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
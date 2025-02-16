package org.example.tienda_online_springboot.MODELO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @NotBlank
    @Column(name = "nombre", nullable = false, length = 50)
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚÜáéíóúü ]{1,50}", message = "El nombre tiene un formato obligatorio de 1 a 50 caracteres alfabéticos")
    private String nombre;

    @Size(max = 50)
    @NotNull
    @NotBlank
    @Column(name = "apellido", nullable = false, length = 50)
    @Pattern(regexp = "[A-Za-z0-9ÁÉÍÓÚÜáéíóúü ]{1,50}", message = "El apellido tiene un formato obligatorio de 1 a 50 caracteres alfanuméricos")
    private String apellido;

    @Size(max = 50)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 50)
    @Pattern(regexp = "[A-Za-z0-9._-]{1,50}", message = "El nickname tiene un formato obligatorio de 1 a 50 caracteres alfanuméricos y símbolos básicos")
    private String nickname;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "[A-Za-z0-9]{5,}", message = "La contraseña tiene que tener mínimo una letra mayúscula, una minúscula y un número y estar formada mínimo de 5 caracteres")
    private String password;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    @Pattern(regexp = "[6789][0-9]{8}", message = "El teléfono tiene que empezar por 6, 7, 8 o 9 seguido de 8 digitos más")
    private String telefono;

    @Size(max = 100, min = 8, message = "El domicilio tiene que tener mínimo 8 caracteres y máximo 100")
    @Column(name = "domicilio", length = 100)
    @NotBlank
    private String domicilio;

    @OneToMany(mappedBy = "cliente")
    /*@JsonIgnore*/
    /*@JsonBackReference("cliente")*/
    /*@JsonManagedReference("cliente")*/
    @JsonIgnoreProperties({"cliente"})
    private Set<org.example.tienda_online_springboot.MODELO.Historial> historials = new LinkedHashSet<>();

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Set<org.example.tienda_online_springboot.MODELO.Historial> getHistorials() {
        return historials;
    }

    public void setHistorials(Set<org.example.tienda_online_springboot.MODELO.Historial> historials) {
        this.historials = historials;
    }

}
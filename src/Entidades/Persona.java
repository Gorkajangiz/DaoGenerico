/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author edria
 */
public class Persona {
    private String DNI;
    private String nombre;
    private String apellido;
    private Integer telefono;

    public Persona(String DNI, String Nombre, String Apellido, Integer Telefono) {
        this.DNI = DNI;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.telefono = Telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer Telefono) {
        this.telefono = Telefono;
    }
}

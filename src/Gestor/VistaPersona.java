/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author gorka
 */
public class VistaPersona {

    public static void main(String args[]) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Boolean ejecutando = true;
        DaoPersonaClase dao = new DaoPersonaClase();
        Collection<Persona> c = null;
        while (ejecutando) {
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Buscar por nombre");
            System.out.println("2. Buscar por Apellido");
            System.out.println("3. Buscar por Telefono");
            System.out.println("4. Buscar por DNI");
            System.out.println("5. Buscar por ID");
            System.out.println("X. Cerrar");
            String respuesta = sc.nextLine();
            switch (respuesta.toUpperCase()) {
                case "1":
                    System.out.println("Nombre:");
                    String nombre = sc.nextLine();
                    c = dao.findByName(nombre);
                    for (Persona p : c) {
                        System.out.println("--------------------------------");
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Apellido: " + p.getApellido());
                        System.out.println("DNI: " + p.getDNI());
                        System.out.println("Telefono: " + p.getTelefono());
                        System.out.println("ID: " + p.getId());
                        System.out.println("--------------------------------");
                    }
                    break;
                case "2":
                    System.out.println("Apellido:");
                    String apellido = sc.nextLine();
                    c = dao.findBySurname(apellido);
                    for (Persona p : c) {
                        System.out.println("--------------------------------");
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Apellido: " + p.getApellido());
                        System.out.println("DNI: " + p.getDNI());
                        System.out.println("Telefono: " + p.getTelefono());
                        System.out.println("ID: " + p.getId());
                        System.out.println("--------------------------------");
                    }
                    break;
                case "3":
                    System.out.println("Telefono:");
                    Integer telefono = Integer.valueOf(sc.nextLine());
                    c = dao.findByPhone(telefono);
                    for (Persona p : c) {
                        System.out.println("--------------------------------");
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Apellido: " + p.getApellido());
                        System.out.println("DNI: " + p.getDNI());
                        System.out.println("Telefono: " + p.getTelefono());
                        System.out.println("ID: " + p.getId());
                        System.out.println("--------------------------------");
                    }
                    break;
                case "4":
                    System.out.println("DNI:");
                    String DNI = sc.nextLine();
                    c = dao.findByDNI(DNI);
                    for (Persona p : c) {
                        System.out.println("--------------------------------");
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Apellido: " + p.getApellido());
                        System.out.println("DNI: " + p.getDNI());
                        System.out.println("Telefono: " + p.getTelefono());
                        System.out.println("ID: " + p.getId());
                        System.out.println("--------------------------------");
                    }
                    break;
                case "5":
                    System.out.println("ID:");
                    Long id = Long.parseLong(sc.nextLine());
                    Persona p = dao.findById(id);
                    System.out.println("--------------------------------");
                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Apellido: " + p.getApellido());
                    System.out.println("DNI: " + p.getDNI());
                    System.out.println("Telefono: " + p.getTelefono());
                    System.out.println("ID: " + p.getId());
                    System.out.println("--------------------------------");
                    break;
                case "X":
                    ejecutando = false;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}

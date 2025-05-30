/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.SQLException;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author gorka
 */
public class VistaPersona {

    public static void main(String args[]) throws SQLException {
        try {
            Scanner sc = new Scanner(System.in);
            Boolean ejecutando = true;
            PersonaControl PC = new PersonaControl();
            Collection<Persona> c = null;
            while (ejecutando) {
                System.out.println("¿Que quieres hacer?");
                System.out.println("1. Buscar por nombre");
                System.out.println("2. Buscar por Apellido");
                System.out.println("3. Buscar por Telefono");
                System.out.println("4. Buscar por DNI");
                System.out.println("5. Buscar por ID");
                System.out.println("6. Insertar en la tabla");
                System.out.println("7. Borrar de la tabla");
                System.out.println("8. Actualizar en la tabla");
                System.out.println("X. Cerrar");
                String respuesta = sc.nextLine();
                switch (respuesta.toUpperCase()) {
                    case "1":
                        System.out.println("Nombre:");
                        String nombre = sc.nextLine();
                        c = PC.cogerPorNombre(nombre);
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
                        c = PC.cogerPorApellido(apellido);
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
                        c = PC.cogerPorTelefono(telefono);
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
                        Persona p = PC.cogerPorDNI(DNI);
                        System.out.println("--------------------------------");
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Apellido: " + p.getApellido());
                        System.out.println("DNI: " + p.getDNI());
                        System.out.println("Telefono: " + p.getTelefono());
                        System.out.println("ID: " + p.getId());
                        System.out.println("--------------------------------");
                        break;
                    case "5":
                        System.out.println("ID:");
                        Long id = Long.valueOf(sc.nextLine());
                        p = PC.cogerPorId(id);
                        System.out.println("--------------------------------");
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Apellido: " + p.getApellido());
                        System.out.println("DNI: " + p.getDNI());
                        System.out.println("Telefono: " + p.getTelefono());
                        System.out.println("ID: " + p.getId());
                        System.out.println("--------------------------------");
                        break;

                    case "6":
                        System.out.println("Dame el nombre:");
                        String nombreIn = sc.nextLine();
                        System.out.println("Dame el apellido:");
                        String apellidoIn = sc.nextLine();
                        System.out.println("Dame el telefono:");
                        Integer telefonoIn = Integer.valueOf(sc.nextLine());
                        System.out.println("Dame el DNI:");
                        String dniIn = sc.nextLine();
                        PC.insertar(dniIn, nombreIn, apellidoIn, telefonoIn);
                        System.out.println("La persona ha sido insertada con éxito");
                        break;
                    case "7":
                        System.out.println("Por qué quieres borrar:");
                        System.out.println("1. Borrar por persona");
                        System.out.println("2. Borrar por ID");
                        String respuestaDel = sc.nextLine();
                        switch (respuestaDel) {
                            case "1":
                                System.out.println("Dame su DNI:");
                                String DNIDel = sc.nextLine();
                                p = PC.cogerPorDNI(DNIDel);
                                PC.borrarPersona(p);
                                break;
                            case "2":
                                System.out.println("Dame su ID:");
                                Long IDDel = Long.valueOf(sc.nextLine());
                                Persona pTemp = PC.cogerPorId(IDDel);
                                PC.borrarPersona(pTemp);
                                break;

                            default:
                                throw new AssertionError();
                        }
                        break;
                    case "8":
                        System.out.println("Dame el nombre:");
                        String nombreUp = sc.nextLine();
                        System.out.println("Dame el apellido:");
                        String apellidoUp = sc.nextLine();
                        System.out.println("Dame el telefono:");
                        Integer telefonoUp = Integer.valueOf(sc.nextLine());
                        System.out.println("Dame el DNI de la persona que quieres cambiar:");
                        String dniUp = sc.nextLine();
                        PC.editar(nombreUp, apellidoUp, telefonoUp, dniUp);
                        System.out.println("La persona ha sido editada con éxito");
                        break;
                    case "X":
                        ejecutando = false;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex + "               ¯\\_(ツ)_/¯");
        } catch (NumberFormatException ex) {
            System.out.println(":(     " + ex);
        } catch (NoSuchElementException ex) {
            System.out.println("La persona no existe");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

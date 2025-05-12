/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import java.util.Scanner;

/**
 *
 * @author gorka
 */
public class main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Boolean ejecutando = true;
        DaoPersonaClase dao = new DaoPersonaClase();
        while (ejecutando) {
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Buscar por nombre");
            System.out.println("O. En progreso");
            System.out.println("X. Cerrar");
            String respuesta = sc.nextLine();
            switch (respuesta.toUpperCase()) {
                case "1":
                    System.out.println("Nombre:");
                    String nombre = sc.nextLine();
                    System.out.println(dao.findByName(nombre));
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

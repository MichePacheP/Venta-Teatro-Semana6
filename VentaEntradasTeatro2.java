/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VentaEntradasTeatro2;

import java.util.Scanner;

public class VentaEntradasTeatro2 {
    
    static String nombreTeatro = "Teatro Moro";
    static int capacidadTeatro = 100;
    static int totalAPagar = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String[] asientos = new String[50];
        String[] nombres = new String[50];

        for (int i = 0; i < asientos.length; i++) {
            asientos[i] = "D";
        }
            System.out.println("Bienvenido/a a "+ nombreTeatro);
            System.out.println("");
            int continuar = 1;
        do {
            System.out.println("___MENU___");
            System.out.println("1. Reservar entradas.");
            System.out.println("Si desea primero reservar su ubicacion, seleccione la opcion 1.");
            System.out.println("2. Comprar asientos.");
            System.out.println("Si desea comprar de forma inmediata su boleto, seleccione la opcion 2");
            System.out.println("3. Modificar compra.");
            System.out.println("Si desea eliminar alguna reserva o compra, seleccione la opcion 3.");
            System.out.println("4. Imprimir boleta.");
            System.out.println("Si desea imprimir su boleta luego de comprarla, seleccione la opcion 4");
            System.out.println("5. Salir del sistema.");
            System.out.println("Si no desea realizar ninguna operacion, seleccione la opcion 5");
            System.out.println("");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    reservarEntradas(asientos, nombres, sc);
                    break;
                case 2:
                    comprarAsiento(asientos, nombres, sc);
                    break;
                case 3:
                    modificarCompra(asientos, nombres, sc);
                    break;
                case 4:
                    System.out.println("-------- BOLETA --------");
                    System.out.println("       "+nombreTeatro+"       ");
                    int cantidadEntradas = 0;
    
                    for (int i = 0; i < asientos.length; i++) {
                    if (asientos[i].equals("C")) {
                        cantidadEntradas++;
                        System.out.println("Asiento: " + (i + 1) + " - Nombre: " + nombres[i]);
                    }
                    }

                    if (cantidadEntradas == 0) {
                        System.out.println("No hay entradas compradas aun.");
                    } else {
                        System.out.println("-------------------------");
                        System.out.println("Cantidad de entradas: " + cantidadEntradas);
                        System.out.println("Total pagado: $" + totalAPagar);
                    }
                    System.out.println("-------------------------");
                    break;
                case 5:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    System.out.println("");
            }
            System.out.println("Si desea volver al menu presione 1. Por el contrario, presione 2.");
            continuar = sc.nextInt();
            sc.nextLine();
        }   while (continuar == 1);
                System.out.println("Programa finalizado. Hasta pronto.");
    } 
    public static void reservarEntradas(String[] asientos, String[] nombres, Scanner sc) {
        mostrarAsientos(asientos);

        System.out.print("Por favor, indique cuantos asientos desea reservar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese el numero de asiento que desea reservar (1-50): ");
            int asiento = sc.nextInt();
            sc.nextLine();

        if (asiento < 1 || asiento > 50) {
            System.out.println("Numero de asiento invalido.");
            i--; 
            continue;
        }

        int index = asiento - 1;

        if (asientos[index].equals("D")) {
            System.out.print("Ingrese su nombre para la reserva (todo en minusculas): ");
            String nombre = sc.nextLine();
            asientos[index] = "R";
            nombres[index] = nombre;
            System.out.println("Asiento " + asiento + " reservado con exito a nombre de " + nombre + ".");
        } else if (asientos[index].equals("R")) {
            System.out.println("El asiento ya esta reservado.");
            i--; 
        } else if (asientos[index].equals("C")) {
            System.out.println("El asiento ya esta comprado.");
            i--; 
        }
    }
}
    public static void comprarAsiento(String[] asientos, String[] nombres, Scanner sc) {
    final int PRECIO_ASIENTO = 5000;

    System.out.print("Por favor, indique cuantos asientos desea comprar: ");
    int cantidad = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < cantidad; i++) {
        mostrarAsientos(asientos);
        System.out.print("Ingrese el numero de asiento que desea comprar (1-50): ");
        int asiento = sc.nextInt();
        sc.nextLine();
        
        if (asiento < 1 || asiento > 50) {
            System.out.println("Numero de asiento invalido.");
            i--; 
            continue;
        }
        int index = asiento - 1;
        
        if (asientos[index].equals("R")) {
            System.out.print("Ingrese su nombre para confirmar la compra: ");
            String nombre = sc.nextLine();

            if (nombres[index] != null && nombres[index].equalsIgnoreCase(nombre)) {
                asientos[index] = "C";
                System.out.println("Valor de la entrada: $" + PRECIO_ASIENTO);
                System.out.println("Compra confirmada. Asiento " + asiento + " ahora esta comprado a nombre de "+nombre+ ".");
                totalAPagar += PRECIO_ASIENTO;
            } else {
                System.out.println("Nombre no coincide con la reserva. No se puede completar la compra.");
                i--; 
            }
        } else if (asientos[index].equals("D")) {
            System.out.print("Ingrese su nombre para la compra directa (todo con minusculas): ");
            String nombre = sc.nextLine();
            asientos[index] = "C";
            nombres[index] = nombre;
            System.out.println("Valor de la entrada: $" + PRECIO_ASIENTO);
            System.out.println("Asiento " + asiento + " comprado con exito a nombre de " + nombre + ".");
            totalAPagar += PRECIO_ASIENTO;

        } else if (asientos[index].equals("C")) {
            System.out.println("El asiento ya esta comprado.");
            i--;
        }
    }
        System.out.println("--------------------------------------------------");
        System.out.println("Compra finalizada. Total a pagar: $" + totalAPagar);
        System.out.println("--------------------------------------------------");
    }
    private static void mostrarAsientos(String[] asientos) {
        System.out.println("Estado de los asientos:");
    for (int i = 0; i < asientos.length; i++) {
        System.out.printf("%2d[%s]  ", (i + 1), asientos[i]);
        if ((i + 1) % 10 == 0) System.out.println();
    }
    System.out.println();
    }
    public static void modificarCompra(String[] asientos, String[] nombres, Scanner sc) {
        mostrarAsientos(asientos);
    
        System.out.print("Ingrese el numero de asiento que desea modificar (1-50): ");
        int asiento = sc.nextInt();
        sc.nextLine();

        if (asiento < 1 || asiento > 50) {
            System.out.println("Numero de asiento invalido.");
            return;
        }

        int index = asiento - 1;

        if (asientos[index].equals("D")) {
            System.out.println("El asiento está disponible. No hay nada que modificar.");
        } else {
            System.out.print("Ingrese su nombre para confirmar la modificacion (todo con minusculas): ");
            String nombre = sc.nextLine();

        if (nombres[index] != null && nombres[index].equalsIgnoreCase(nombre)) {
            System.out.print("Si esta seguro de querer cancelar la compra/reserva presione 1, de lo contrario presione 2: ");
    int confirmacion = sc.nextInt();
    sc.nextLine();

        if (confirmacion == 1) {
        if (asientos[index].equals("C")) {
            totalAPagar -= 5000;
        }
            asientos[index] = "D";
            nombres[index] = null;
            System.out.println("Asiento " + asiento + " ha sido liberado correctamente.");
            System.out.println("Nuevo total a pagar: $" + totalAPagar);
        } else {
            System.out.println("Modificación cancelada. El asiento se mantiene como está.");
        }
        }
        }
    }
}


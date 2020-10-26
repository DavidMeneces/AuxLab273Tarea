package Primera_Tarea;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class servidorUDP extends Thread {
	Scanner leer = new Scanner(System.in);
	// Declaracion de variables:
	int puerto;
	DatagramSocket socketUDP;
	DatagramPacket mensaje;
	DatagramPacket respuesta;

	// Constructor para nuestro server
	public servidorUDP() {
		puerto = 8880;
	}

	@Override
	public void run() {
		try {
			// Creamos el socket del server
			socketUDP = new DatagramSocket(puerto);
			while (true) {
				// Declaramos mensaje peticion y lo recibimos
				mensaje = new DatagramPacket(new byte[1024], 1024);
				socketUDP.receive(mensaje);
				String cadenarespuesta = "";
				//Convertimos la opcion de peticion enviada a un entero
				String opcion = new String(mensaje.getData());
				int i = Integer.parseInt(opcion.substring(0,1));
				switch (i) {
				case 1:
					System.out.println("Ingrese numero:");
					int num = leer.nextInt();
					int cont = 0;
					if(num>1) {
						for (int j = 2; j <= num-1; j++)
							if(num%j == 0) 
								cont++;
						if(cont==0)
							cadenarespuesta = "es primo";
						else 
							cadenarespuesta = "no es primo";
					}
					else
						cadenarespuesta = "no es primo";
					break;
				case 2:
					System.out.println("Ingrese la cadena");
					String cadena = leer.next();
					String cadaux = "";
					for (int j = cadena.length()-1; j >=0 ; j--)
						cadaux = cadaux+cadena.charAt(j);
					if(cadena.compareToIgnoreCase(cadaux)==0)
						cadenarespuesta = "Es palindromo";
					else 
						cadenarespuesta = "No es palindromo";
					break;
				case 3:
					System.out.println("Ingrese el primer numero");
					int a = leer.nextInt();
					System.out.println("Ingrese el segundo numero");
					int b = leer.nextInt();
					System.out.println("Ingrese el tercer numero");
					int c = leer.nextInt();
					int resp = Math.max(a, Math.max(b, c));
					cadenarespuesta = "El mayor de los 3 numeros es: "+resp;
					break;
				default:
					break;
				}
				byte mensajeEnviar[] = new byte[1024];
				// Convierte cadenarespuestaena a bytes y lo ponemos en el datagrama respuesta
				mensajeEnviar = cadenarespuesta.getBytes();
				respuesta = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, mensaje.getAddress(),mensaje.getPort());
				// Enviamos la respuesta a la peticion del cliente
				socketUDP.send(respuesta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Funcion para finalizar la conexion
	public void end() {
		try {
			socketUDP.close();
			System.out.println("Conexion finalizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
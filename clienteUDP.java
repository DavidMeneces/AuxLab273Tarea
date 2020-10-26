package Primera_Tarea;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clienteUDP extends Thread{
	Scanner leer = new Scanner(System.in);
	//Declaracion de variables:
	int puerto;
	InetAddress direccion;
	DatagramSocket socketUDP;
	DatagramPacket respuesta;
	DatagramPacket mensaje;
	//Constructor para nuestro clienteUDP:
	public clienteUDP() throws UnknownHostException {
		direccion = InetAddress.getByName("localhost");
		puerto = 8880;
	}
	@Override
	public void run() {
		//Creamos nuestro cliente y lo iniciamos
		try {
			clienteUDP c = new clienteUDP();
			c.iniciar();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Iniciamos nuestro cliente
	public void iniciar() throws IOException {
		// Creamos un socket bajo UDP
		socketUDP = new DatagramSocket();
		while(true) {
			//Escogemos una opcion del menu
			System.out.println("...........MENU..........."
					+"\n1. Es Primo"
					+"\n2. Es Palindromo"
					+"\n3. Maximo De Tres Numeros"
					+"\n4. Salir"
					+"\n..........................");
			int i = leer.nextInt();
			if(i!=4) {
				// Convertimos la opcion a Bytes
				byte[] opcion = (i+"").getBytes();
				// Declaramos al mensaje y enviamos
				mensaje = new DatagramPacket(opcion, opcion.length, direccion, puerto);
				socketUDP.send(mensaje);
				// Recibimos la respuesta
				respuesta = new DatagramPacket(new byte[1024], 1024);
				socketUDP.receive(respuesta);
				// Recibimos el mensaje respuesta del Data y mostramos
				String mensaje = new String(respuesta.getData());
				System.out.println(mensaje);
				System.out.println();
			}
			else {
				//En caso de que la opcion sea salir cerramos el socket
				socketUDP.close();
				System.out.println("Desconectado del Server!");
				return;
			}

		}
	}
}
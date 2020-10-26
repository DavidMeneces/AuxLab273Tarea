package Primera_Tarea;

import java.net.UnknownHostException;

public class main{

	public static void main(String[] args) throws UnknownHostException {
		servidorUDP ser = new servidorUDP();
		clienteUDP clienteudp = new clienteUDP();
		ser.start();
		clienteudp.start();
	}

}

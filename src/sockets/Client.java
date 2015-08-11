package sockets;

import java.io.*;
import java.net.*;

/**
 *
 * @author Juanjors
 */
public class Client {

	public static void main(String[] arg) throws IOException, ClassNotFoundException {

		int portNum = 1818;

		Socket socket = new Socket("192.168.1.135", portNum);

		String n_archivos = leerArchivos();

		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

		out.writeObject(n_archivos);

		String response = (String) in.readObject();

		System.out.println("Server message: " + response);

		 socket.close();
	}

	public static String leerArchivos() {
		String n_archivos = "";
		File path = new File(".");
		File[] archivos = path.listFiles();
		for (int i = 0; i < archivos.length; i++) {
			String nombreIesimo = archivos[i].getName();
			if(!nombreIesimo.equals("leer.jar")){
			n_archivos += nombreIesimo + "\n";
		}
		}
		return n_archivos;
	}

}
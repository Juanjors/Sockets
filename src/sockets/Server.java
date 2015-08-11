package sockets;

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		
		//PUERTO DONDE EL SERVIDOR ESTAR� ESCUCHANDO.
		int portNum = 1818;

		// SOCKET DONDE EL SERVIDOR ESTAR� ESCUCHANDO.
		ServerSocket listener = new ServerSocket(portNum);

		System.out.println("El servidor esta escuchando en el puerto  " + portNum);

		// Cone esto conseguimos que el servidor est� escuchando continuamente.
		while (true) {
			try {
			
				//Acepta la conexi�n del cliente
				Socket clientSocket = listener.accept();
				
				String address = listener.getInetAddress().toString();
				
				System.out.println("La direcci�n remota es:  " + address);

			
				//Cremaos outputstream e input 
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

				// Leemos en este caso un String.
				String nombreCanciones = (String) in.readObject();

				// Enviamos una respuesta indicando que se ha recibido el string correctamente.
				String response = "String recibido";
				out.writeObject(response);
				
				System.out.println(nombreCanciones);
				
				//Cerramos out,in, y el socket del cliente. (No cerramos el server socket si queremos que este siga escuchando despu�s de la primera conexion.
				out.close();
				in.close();
				clientSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Closing Server Socket now.
//				 listener.close();

			}

		}
	}
}

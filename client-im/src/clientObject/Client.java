package clientObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import messageObject.Message;

public class Client {
	private String username;
	public Client(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public static void main(String[] args) {
		Client client1 = new Client("arya");
		try (Socket socket = new Socket("localhost", 6666);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
			System.out.println("Client connected to server.");
						
			// Create a new Message
			Message msg = new Message();
			msg.setSender(client1.getUsername());
			msg.setReceiver("client2");
			msg.setMessageString("Hello!");
			
			// Serialize the objects and send them to the server
			oos.writeObject(msg);
			oos.writeObject(null);
			oos.flush();
			System.out.println("Sent objects to server.");
		} catch (IOException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);;
		}
	}
}

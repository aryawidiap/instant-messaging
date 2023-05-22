package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import messageObject.Message;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(6666);
				Socket socket = server.accept();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
			// Client array

			// Read serialized objects from the client
			Object obj;
			while ((obj = ois.readObject()) != null) {
				if (obj instanceof Message) {
					Message msg = (Message) obj;
					System.out.println("Received Person object from client:");
					System.out.println("Sender: " + msg.getSender() + ", Receiver: " + msg.getReceiver());
					System.out.println("Message: " + msg.getMessageString());
				} else {
					System.out.println("Received unknown object from client: " + obj);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error handling client request: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

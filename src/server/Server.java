package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int MYPORT = 4150;
	private static ServerSocket connection;
	private static int clientID = 1;

	public static void main(String[] args) throws IOException {

		try {
			// create the server socket
			connection = new ServerSocket(MYPORT);
		} catch (IOException e) {
			// print if there is an error
			System.out.println("Server can not run. Port is in use.");
			System.exit(1);
		}

		System.out.println("Server is running");

		while (true) {
			// make a new connection and start a new thread for every client
			Socket socket = connection.accept();
			System.out.println("Connection established Client ID: " + clientID);
			SetupStreams client = new SetupStreams(socket, clientID++);
			client.start();
		}
	}

}

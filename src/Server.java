import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {

	public static final int MYPORT = 4950;
	private static ServerSocket socket ;
	
	
	public static void main(String[] args) throws IOException {
		
		try {
			socket = new ServerSocket(MYPORT);
		} catch (IOException e) {
			System.out.println("Server can not run. Port is in use.");
			System.exit(1);
		}
		
		System.out.println("Server is running");

		int clientId = 0;
		while (true) {
			Socket clientSocket = socket.accept();
			//ServerThread client = new ServerThread(socket, ++clientId);
			//client.start();
		}
	}

}

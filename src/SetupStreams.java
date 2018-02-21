import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SetupStreams extends Thread{
	
	
	private final int BUFSIZE = 9000;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private int userId;
	
	public SetupStreams(Socket socket,int client) {
		this.socket = socket;
		this.userId = client;
		
		
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.flush();
			inputStream = new DataInputStream(socket.getInputStream());
			System.out.println(" Streams are set up Client:"+userId);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Override	
	public void run(){
		System.out.println("prr");
		
		//fixing
		StringBuilder builder = new StringBuilder(); 
		String line;
		
		try {
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(socket.getInputStream(), "UTF-8")
					);
			do
			{
			    line = in.readLine(); 
			    if (line.equals("")) break;
			    builder.append(line);
			}
			while (true);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(builder.toString());
	}

}

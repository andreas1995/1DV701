import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import com.sun.net.ssl.HttpsURLConnection;



public class SetupStreams extends Thread{
	
	private int contentlength = 0;
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
	
	//for POST
	private String readBody(BufferedReader reader) throws IOException {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < contentlength; i++) {
			data.append((char)reader.read());
		}
		
		return data.toString();
	}

	
	//for GET
	@Override	
	public void run(){
		

		//fixing
		StringBuilder builder = new StringBuilder(); 
		
		 
		try {
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(socket.getInputStream())
					);
		
			
			
			while(true) {
				String line = in.readLine();
				if (line == null) { //eof http://docs.oracle.com/javase/1.4.2/docs/api/java/io/BufferedReader.html
					throw new IOException("eof");
				}
				
				builder.append(line);
				builder.append("\r\n");
				if (line == null || line.equals("\r\n") || line.equals("")) {
					break;
				}
				if (line.startsWith("Content-Length:")) {
					String number = line.substring(16);
					contentlength  = Integer.parseInt(number); 
				}

				
				
				
			}
			
			System.out.println(builder.toString());	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}


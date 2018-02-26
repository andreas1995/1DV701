package server;
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

import response.ResolveResponse;

public class SetupStreams extends Thread {

	private int contentlength = 0;
	private final int BUFSIZE = 9000;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private int userId;
	ResolveRequest parser;

	
	/*Setting up the streams*/
	public SetupStreams(Socket socket, int client) {
		this.socket = socket;
		this.userId = client;

		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.flush();
			inputStream = new DataInputStream(socket.getInputStream());
			System.out.println(" Streams are set up Client:" + userId);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

   /*This method will tiger the program to get the request then resolve it and then 
    *send it for the corresponding response.*/
	@Override
	public void run() {
		try {
		    
			//first we get the request, then we resolve then respond
			parser = new ResolveRequest(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			Request request = parser.resolve();
			ResolveResponse re = new ResolveResponse(request,outputStream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResolveRequest {
	private BufferedReader reader;
	private int contentLength;
	private MethodType type;
	public enum MethodType {GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH};

	
   /* This class is responsible for resolving the request.*/
	public ResolveRequest(BufferedReader reader) {
		this.reader = reader;
		this.contentLength = 0;
	}

	
	/*in this method we get all the information about out request by calling the methods bellow,
	 * and then send the request object back to the SetupStreams where this method is called.*/
	public Request resolve() {

		//gathering information by calling the methods bellow.
		String[] info = resolveHttpHeader(readHttp());
        String body = readBody();
		Request request = new Request(resolveMethodType(info[0]), info[1], info[2], contentLength, body);
		return request;
	}

	
	
	/* get the request s method type*/
	public String resolveMethodType(String type) {
		for (MethodType m : MethodType.values()) {
			if (type.equals(m.name())) {
				return m.toString();
			}

		}
		//bad request...
		return null;
	}

	/*Read the http */
	public String readHttp() {

		StringBuilder builder = new StringBuilder();
		try {

			while (true) {
				String line = reader.readLine();

				if (line == null) {
					throw new IOException("eof");
				}

				builder.append(line);
				builder.append("\r\n");

				if (line == null || line.equals("\r\n") || line.isEmpty() || line.equals("")) {
					break;
				}

				if (line.startsWith("Content-Length")) {
					contentLength = Integer.parseInt(line.substring(16));
				}

			}
			

		} catch (IOException e) {
			// TODO: handle exception
		}

		return builder.toString();
	}

	
	/*read body*/
	public String readBody() {

		StringBuilder data = new StringBuilder();

		for (int i = 0; i < contentLength; i++) {
			try {
				data.append((char) reader.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return data.toString();
	}

	
	/*read header*/
	public String[] resolveHttpHeader(String header) {

		String[] lineByLine = header.split("\r\n");
		String[] requestLine = lineByLine[0].split(" ");

		return requestLine;
	}

}

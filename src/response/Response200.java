package response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import server.Request;

public class Response200 extends Response{

	public Response200(DataOutputStream out, File file) {
		super( out, file);
		super.InputResponse("200 OK", "");
		
	}
     
	
	
	public void write() throws IOException {
	       String[] parts = file.getName().split("\\.");
			writeHeader(file.length(), parts[parts.length - 1]);
			writeFile();
		}
		
		
		private void writeFile() throws IOException {

			FileInputStream in = new FileInputStream(file);
			byte[] buf = new byte[9000];
			int bytesRead = 0;
			while ((bytesRead = in.read(buf)) != -1) {
				out.write(buf, 0, bytesRead);
			}
			in.close();
            
			System.out.println("Client "  + " got " + file.getName());
		}

	

}

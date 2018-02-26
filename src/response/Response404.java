package response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import server.Request;

public class Response404 extends Response{

	public Response404( DataOutputStream out, File file) throws IOException {
		super( out, file);
		super.InputResponse("404 Not Found", "");
		write();
	}

	

}

package response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import server.Request;

public class Response500 extends Response{

	public Response500( DataOutputStream out, File file) throws IOException {
		super( out, file);
		super.InputResponse("500 Internal Server Error", "Server has internal problem");
		write();
	}

}

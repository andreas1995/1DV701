package response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import server.Request;

public class Response403 extends Response{

	public Response403( DataOutputStream out, File file) throws IOException {
		super( out, file);
		super.InputResponse("403 Forbiten", "Valid request, not authorized for accessing the resource.");
		write();
	}

}

package response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import server.Request;
import server.SetupStreams;

public class ResolveResponse {
   Request request;
   File file;
   ResolveMethod rm;
   DataOutputStream out;
   
	
   /*This class is responsible for finding the correct method type and calling the response*/
	public ResolveResponse(Request request,DataOutputStream out) {
		this.request = request;
		rm =  new ResolveMethod();
		this.out = out;
		//go check the method type
		try {
			checkMethodType();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
   /*Depending on the method type, give the corresponding response */
	public void checkMethodType() throws IOException {
		
		if(request.retrurnMethodType().equals("GET")) {
			file = rm.GET(request.retrurnPath(),out);
			Response200 re = new Response200( out, file);
			try {
				re.write();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	
}

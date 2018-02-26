package response;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


import server.Request;

public  class Response {
  protected Request request;
  protected DataOutputStream out;
  protected File file;
  protected String content;
  protected String response;
  
  
  //need to decide what kind of response 
	public Response(DataOutputStream out,File file) {
		this.out = out;
		this.file = file;
	}
	
	
	/*Give the header and the content for every response.
	 * This class is a super class that every response extends, depending on the response
	 * for example 404 the answer string see below and the content string see below will 
	 * change. For every response - class there is a different answer that would be written. */
	protected void InputResponse(String answer, String content) {
		this.response = "HTTP/1.1 " + answer + "\r\n";
		this.content = "<html><body><h1>" + answer + "</h1><p>" + content + "</p></body></html>";
	}
	
	
	
	/*This method calls the write header and write content methods, that are responsible for
	 * writing the responses.*/
	protected void write() throws IOException {
		writeHeader(content.getBytes().length, "html");
		writeContent();
	}
	
	
	
	private void writeContent() throws IOException {
		out.write(content.getBytes());
	}
	
	
	
	
	protected void writeHeader(long length, String fileExtension) throws IOException {

		response += "Date: " + new Date().toString() + "\r\n";
		response += "Content-Length: " + length + "\r\n";
		response += "Content-Type: " + getContentType(fileExtension) + "\r\n\r\n";
		
		PrintWriter printer = new PrintWriter(out, true);
		printer.write(response);
		printer.flush();
	}
	
	
	
	private String getContentType(String fileExtension) {

		for (ContentType type : ContentType.values()) {
			for (String extension : type.extensions) {
				if (fileExtension.equals(extension)) {
					return type.value;
				}
			}
		}

		return ContentType.applicationunknown.value;
	}

	
	
	private enum ContentType {

		texthtml("text/html", "html, htm"), 
		textcss("text/css", "css"), 
		textjavascript("text/javascript", "js"), 
		imagepng("image/png", "png"), 
		imagegif("image/gif", "gif"), 
		imagejpeg("image/jpeg", "jpg, jpeg"), 
		applicationunknown("application/unknown", "*");

		private String value;
		private String[] extensions;

		
		private ContentType(String value, String extensions) {
			this.value = value;
			this.extensions = extensions.split(", ");
		}

	}
	
	
	
	
}

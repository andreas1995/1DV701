package server;
public class Request {

	private String Methodtype;
	private String path;
	private final String HTTP_VERSION;
	private int contentLength = 0;
	private String body;

	
	/*This class represends our request,  it contains all the information of the request*/
	public Request(String type, String path, String HTTP_VERSION, int contentLength, String body) {
		this.path = path;
		this.contentLength = contentLength;
		this.HTTP_VERSION = HTTP_VERSION;
		this.body = body;
		this.Methodtype = type;
		System.out.println(type + "\n" + path + "\n" + HTTP_VERSION + "\n" + contentLength + "\n" + body);
	}

	// getters
	public String retrurnMethodType() {
		return this.Methodtype;
	}

	public String retrurnPath() {
		return this.path;
	}

	public String returnBody() {
		return this.body;
	}

	public int returnContentLength() {
		return this.contentLength;
	}

	public String returnHTTPversion() {
		return this.HTTP_VERSION;
	}

	public String getUploadedFileName() {
		// TODO Auto-generated method stub
		return null;
	}
}

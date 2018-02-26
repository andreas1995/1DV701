package response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import server.Request;

public class ResolveMethod {

	
	
	private final String PATH = "C:\\Users\\antreas\\Desktop\\java workspace\\1DV701\\src\\resources\\inner";
	private final File SHARED_FOLDER = new File(PATH);
	private final String ALLOWED_MEDIA_TYPE = "png/jpg/jpeg";

	
	public File GET(String path,DataOutputStream out) throws IOException
	 {

		if (path.equals("/")) {
			path += "index.htm";
		}

		if (path.endsWith("htm") && !new File(SHARED_FOLDER, path).exists()) {
			path += "l";
		}

		else if (path.endsWith("html") && !new File(SHARED_FOLDER, path).exists()) {
			path = path.substring(0, path.length() - 1);
		}

		else if (path.charAt(path.length() - 1) != '/' && path.split("\\.").length == 0) {
			path += "/";
		}

		File file = new File(SHARED_FOLDER, path);

		if (file.isDirectory()) {
			for (int i = 0; i < file.listFiles().length; i++) {
				if (file.listFiles()[i].getName().equals("index.htm")
						|| file.listFiles()[i].getName().equals("index.html")) {
					file = file.listFiles()[i];
					break;
				}
			}
		}

		if (!file.isDirectory() && file.exists()) {
			switch (file.getParent()) {
			case PATH + "/" + "secret":{
				//Response403 forbiten = new Response403( out, file);
			System.out.println("illegal");	
			}
			case PATH + "/" + "legal":
				//throw new UnavailableForLegalReasonsException();
			case PATH + "/" + "private":
				//throw new LockedException();
			default:
				return file;
			}
		}
		//throw new FileNotFoundException();
		return file;
	}


	public void POST(Request request) throws IOException, NullPointerException {

		//if (!ALLOWED_MEDIA_TYPE.contains(request.getUploadedFileExtension())) {
		//	throw new UnsupportedMediaTypeException();
		//}

		byte[] imageBytes = DatatypeConverter.parseBase64Binary(request.returnBody());

		File imageFile = new File(SHARED_FOLDER,
				"/" + "images" + "/" + request.getUploadedFileName());

		FileOutputStream fop = new FileOutputStream(imageFile, false);
		fop.write(imageBytes);
		fop.flush();
		fop.close();
	}

	
	public void PUT(Request request) throws IOException {

		File file = new File(SHARED_FOLDER,
				"/" + "images" + "/" + request.getUploadedFileName());

		if (!file.exists()) {
		//	throw new ConflictException();
		}

		file.delete();

		POST(request);
	}


	
	
	
}

package httpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HttpResponse {

	String response;
	String filePath = "C:/Users/Sevajper/Desktop/Java Workspace/Network-Lab-2-master/src/";
	HttpRequest hReq;

	public HttpResponse(HttpRequest hReq) {
		this.hReq = hReq;
		File file = new File(filePath + hReq.fileName);

		try {
			FileInputStream input = new FileInputStream(file);
			response = "HTTP/1.1 200 \r\n";
			response += "Server: Our Java Server \r\n";
			response += "Content-Type: text/html \r\n";
			response += "Content-Length: " + file.length() + " \r\n";
			response += "\r\n";

			int s;

			while ((s = input.read()) != -1) {
				response += (char) s;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response = response.replace("200", "404");
		} catch (Exception e) {
			e.printStackTrace();
			response = response.replace("200", "500");
		}
	}
}

package httpServer;

public class HttpRequest {
	
	String fileName;
	
	public HttpRequest(String text){
		
		String sep[] = text.split("\n");
		fileName = sep [0].split(" ")[1];
	}
	
	
}



package httpServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	protected Socket soc;
	private PrintWriter pw;
	private BufferedReader br;

	public ServerThread(Socket socket) throws Exception {
		soc = socket;
		br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		pw = new PrintWriter(soc.getOutputStream());
	}
	
	public ServerThread() {}

	@Override
	public void run() {
		try {
			String text = "";
			while (br.ready() || text.length() == 0)
				text = text + (char) br.read();
			
			System.out.println(text);
			HttpRequest hReq = new HttpRequest(text);
			HttpResponse hRes = new HttpResponse(hReq);
			pw.write(hRes.response.toCharArray());
			pw.close();
			br.close();
			soc.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}

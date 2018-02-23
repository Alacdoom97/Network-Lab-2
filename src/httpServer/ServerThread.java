
package httpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
	private static int ID;
	private Socket soc;
	PrintWriter pw;
	BufferedReader br;

	public ServerThread(Socket socket, int identity) throws Exception {
		soc = socket;
		ID = identity;
		br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		pw = new PrintWriter(soc.getOutputStream());
	}

	@Override
	public void run() {
		try {
			System.out.println("New Thread!");
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

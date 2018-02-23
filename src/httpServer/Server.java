package httpServer;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private static final int MYPORT = 8888;
    
	public static void main(String[] args) throws Exception {
		
            @SuppressWarnings("resource")
			ServerSocket sSocket = new ServerSocket(MYPORT);
            while (true) {
                Socket soc = sSocket.accept();
                ServerThread serverThread = new ServerThread(soc);
                serverThread.start();
            }
            
	}

}

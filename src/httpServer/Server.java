package httpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private static final int MYPORT = 8080;
    
	public static void main(String[] args) throws Exception {
		
            ServerSocket sSocket = new ServerSocket(MYPORT);
            int ID = 0;
            
            while (true) {
                Socket soc = sSocket.accept();
                ServerThread serverThread = new ServerThread(soc, ++ID);
                serverThread.run();
            }
            
	}

}

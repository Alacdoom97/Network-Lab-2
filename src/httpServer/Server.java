package httpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private static final int MYPORT = 420;
    
	public static void main(String[] args) throws IOException {
		
            ServerSocket sSocket = null;
            sSocket = new ServerSocket(MYPORT);
            System.out.println("Server has begun running!");
            
            int ID = 0;
            
            while (true) {
                Socket soc = sSocket.accept();
                ServerThread serverThread = new ServerThread(soc, ++ID);
                serverThread.runServer();
            }
	}

}

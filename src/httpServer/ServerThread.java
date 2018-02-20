/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpServer;

import java.net.Socket;


/**
 *
 * @author Pranav
 */
public class ServerThread {
    private static int ID;
    private Socket soc;
    
    public ServerThread(Socket socket, int identity) {
        soc = socket;
        ID = identity;
    }
}

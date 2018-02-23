/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

/**
 *
 * @author Pranav
 */
public class ServerThread extends Thread {

    private static int ID;
    private Socket soc;
    private byte[] buf;
    private int BUFSIZE = 1024;
    private InputStreamReader streamIn;
    private PrintWriter write;
    private BufferedReader read;

    public ServerThread(Socket socket, int identity) {
        soc = socket;
        ID = identity;
    }

    public void runServer() {
        String message = "";

        try {
            streamIn = new InputStreamReader(soc.getInputStream());
            read = new BufferedReader(streamIn);
            String line = read.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = read.readLine();
            }
            PrintWriter out = new PrintWriter(soc.getOutputStream());
            out.println("HTTP/1.1 200 OK");
            out.println("Content-type: text/html");
            out.println("\r\n");
            InputStream in = this.getClass().getClassLoader()
            .getResourceAsStream("test.html");
            String s = new BufferedReader(new InputStreamReader(in))
            .lines().collect(Collectors.joining("\n"));
            out.println(s);
            /*In this line I used out.println("full html code"); but I'd like a 
            simpler way where it can search for the html file in the directory and send it.*/
            out.flush();
            out.close();
            soc.close();
        } catch (IOException e) {
            System.out.println("Esteban wtf are you doing?!");
            e.printStackTrace();
        }

    }
}

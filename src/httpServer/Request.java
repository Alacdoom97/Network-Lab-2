/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pranav
 */
public class Request {

    private String filepath;
    private int contentNum;
    private String fullContent;
    private String Exstention;
    
    public Request() {
    }
         
    public Request request(BufferedReader reader) throws IOException {
        StringBuilder strB = new StringBuilder();

        while (true) {
            try {
                String line = reader.readLine();

                if (line == null || line.equals("\r\n") || line.isEmpty()) {
                    break;
                }
                strB.append(line + "\r\n");

                if (line.startsWith("Content-Length")) {
                    contentNum = Integer.parseInt(line.substring(16));
                }
            } catch (IOException e) {
                System.out.println("Esteban fucked up reading buffer!");
                e.printStackTrace();
            }
            String[] allLines = strB.toString().split("\r\n");

            String[] first = allLines[0].split(" ");

            filepath = first[1];

            if (contentNum > 0) {
                StringBuilder content = new StringBuilder();

                int i = 0;

                while (i < contentNum) {
                    char c;
                    c = (char) reader.read();
                    if (c == '%') {
                        c = (char) Integer.parseInt("" + (char) reader.read() + "" + (char) +reader.read(), 16);
                        i = i + 2;
                    }

                    content.append(c);
                    i++;
                }

                fullContent = content.toString().split("base64,")[1];
                Exstention = content.toString().split(":")[1].split(";")[0].split("/")[1];

            }
        }
        return new Request(filepath, contentNum, fullContent, Exstention);
    }
    
    public Request(String filePath, int conNum, String fullContent, String Exstention) {
        this.filepath = filePath;
        this.contentNum = conNum;
        this.fullContent = fullContent;
        this.Exstention = Exstention;
    }
}

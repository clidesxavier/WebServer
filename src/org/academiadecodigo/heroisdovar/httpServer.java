package org.academiadecodigo.heroisdovar;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class httpServer {


    private int portNumber = 8081;
    private ServerSocket serverSocket;
    private Socket socket;

    public httpServer() {


        try {
            serverSocket = new ServerSocket(portNumber);

            System.out.println("Server On");

            while (0 == 0) {
                socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                System.out.println("Connection from " + socket);
                String line = in.readLine();


                    System.out.println(line);
                    if(line.equals("GET / HTTP/1.1")) {

                        String path = "www/hello.html";
                        byte[] pathBuffer = Files.readAllBytes(Paths.get(path));
                        String header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html;\r\n" + "Content-Length: " + pathBuffer.length + " \r\n\r\n";
                        out.write(header.getBytes());
                        out.write(pathBuffer);
                        out.close();
                        socket.close();
                    }


                    if(line.equals("GET /www/404.html HTTP/1.1")) {
                        String path = "www/404.html";
                        byte[] pathBuffer = Files.readAllBytes(Paths.get(path));
                        String header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html;\r\n" + "Content-Length: " + pathBuffer.length + " \r\n\r\n";
                        out.write(header.getBytes());
                        out.write(pathBuffer);
                        out.close();
                        socket.close();
                    }


            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

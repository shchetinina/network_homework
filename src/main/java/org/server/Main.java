package org.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server started");
        int port = 8090;
        try (ServerSocket serverSocket = new ServerSocket(port);){
            while(true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println(String.format("New connection! Client port %s", clientSocket.getPort()));
                final String name = reader.readLine();
                out.println((String.format("Hi %s, your port is %d", name, clientSocket.getPort())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
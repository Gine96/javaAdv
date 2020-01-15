package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	public static void main(String[] args) {
		int port = 1234;
		
		System.out.println("Server: attesa connessione");
		try (
				ServerSocket server = new ServerSocket(port);
				Socket client = server.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream());
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				
				
		){
			System.out.println("Server: client connesso: " + client.getInetAddress() + ":" + client.getPort());
			
			String request;
			while((request = in.readLine())!=null) {
				System.out.println("Server: " + request);
				String response = request.toUpperCase();
				
				if("QUIT".equals(response)) {
				
					out.println("Arrivederci");
					out.flush();
					break;
				
				}
				
				out.println(request.toUpperCase());
				out.flush();//libera lo stream e manda risposta
			}
			System.out.println("Server: closed");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

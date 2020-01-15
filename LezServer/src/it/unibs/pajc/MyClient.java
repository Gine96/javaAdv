package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
	
	public static void main(String[] args) {
		
		String serverHost = "127.0.0.1";
		int port = 1234;
		
		try (
				Socket client = new Socket(serverHost, port);
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				
		){
			
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			
			String request, response;
			
			while((request=stdin.readLine())!=null) {
				out.println(request);
				response = in.readLine();
				System.out.println("Client: " + response);
				if("QUIT".equals(response)) {
					break;
				}
			}
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}

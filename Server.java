package aplicacao.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	public static void main(String[] args)  throws IOException{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(12028);
		while(true) {
			Socket s = ss.accept();
			ThreadServer server = new ThreadServer(s);
			server.start();
		}
	}
}
package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
	volatile boolean stop;

	public Server() {
		stop=false;
	}

	public interface ClientHandler{
		public void handleClient(InputStream inFromClient, OutputStream outToClient);
	}

	private void startServer(int port, ClientHandler ch) {
		try {
			ServerSocket server=new ServerSocket(port);
			server.setSoTimeout(1000);//if there is no client timeout

			while (!this.stop){
				try{
					Socket client =server.accept();
					try
					{
						InputStream inFromClient = client.getInputStream();
						OutputStream outToClient = client.getOutputStream();
						ch.handleClient(inFromClient,outToClient);
				        client.close();
					}catch(IOException e){e.printStackTrace();}

				}catch (SocketTimeoutException e){}
			}
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// runs the server in its own thread
	public void start(int port, ClientHandler ch) {
		new Thread(()->startServer(port,ch)).start();
	}

	public void stop() {
		stop=true;
	}
}
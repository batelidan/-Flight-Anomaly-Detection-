package test;

import test.Commands.DefaultIO;
import test.Server.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.IOException;

public class AnomalyDetectionHandler implements ClientHandler {
	CLI cli;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		SocketIO sockIO = new SocketIO(inFromClient,outToClient);
		CLI cli = new CLI(sockIO);
		cli.start();
		cli.dio.write("bye\n");

	}

	public class SocketIO implements DefaultIO {
		BufferedReader in;
		PrintWriter out;

		public SocketIO(InputStream inFromClient, OutputStream outToClient) {
			in = new BufferedReader(new InputStreamReader(inFromClient));
			out = new PrintWriter(new OutputStreamWriter(outToClient), true);

		}
		@Override
		public String readText() {
			try {
				return in.readLine();
			} catch (IOException e){
				e.printStackTrace();
				return null;
			}

		}

		@Override
		public void write(String text) {
			out.print(text);
			out.flush();
		}

		@Override
		public float readVal() {
			return 0;
		}

		@Override
		public void write(float val) {
			out.print(val);
			out.flush();
		}

	}
}
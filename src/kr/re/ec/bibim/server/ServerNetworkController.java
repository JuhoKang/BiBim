package kr.re.ec.bibim.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

/**
 * This class implements java Socket server
 * 
 * @author pankaj
 * 
 */

public class ServerNetworkController {
	
	// static ServerSocket variable
	private static ServerSocket server;
	// socket server port on which it will listen
	private static int port = 9876;


	public void Start() throws IOException, ClassNotFoundException {

	
		
		UserDataWrapper udw = new UserDataWrapper();
		// create the socket server object
		server = new ServerSocket(port);
		// keep listens indefinitely until receives 'exit' call or program
		// terminates
		
		while (true) {
			System.out.println("Waiting for client request");
			// creating socket and waiting for client connection
			Socket socket = server.accept();
			// read from socket to ObjectInputStream object
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			LogUtil.d("here");
			// convert ObjectInputStream object to String
			udw = (UserDataWrapper)ois.readObject();
			System.out.println("Message Received: " + udw.getName());
			LogUtil.d("here");
			// create ObjectOutputStream object
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			// write object to Socket	
			oos.writeObject(udw);
			LogUtil.d("here");
			System.out.println("Message Sent: "+udw.getId());
			// close resources
			ois.close();
			oos.close();
			socket.close();
			// terminate the server if client sends exit request
			if (udw.getId() == 10)
				break;
		}
		System.out.println("Shutting down Socket server!!");
		// close the ServerSocket object
		server.close();
	}
}

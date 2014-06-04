package kr.re.ec.bibim.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import sun.rmi.runtime.Log;
import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.UserData;
import kr.re.ec.bibim.vowrapper.FolderDataWrapper;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;
import kr.re.ec.bibim.vowrapper.WrappedClassOpener;

public class ClientNetworkController {

	// for singleton
	private static ClientNetworkController instance = null;

	// for singleton
	static {
		try {
			instance = new ClientNetworkController();
		} catch (Exception e) {
			throw new RuntimeException("singleton instance intialize error");
		}
	}

	// for singleton
	private ClientNetworkController() {
	}

	// for singleton
	public static ClientNetworkController getInstance() {
		return instance;
	}

	public void RequestServer(UserDataWrapper udw) throws UnknownHostException,
			IOException, ClassNotFoundException, InterruptedException {

		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.PORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(udw);
		System.out.println("Sending request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		udw = (UserDataWrapper) ois.readObject();
		System.out.println("Message got: " + udw.getPassword());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		Thread.sleep(100);
	}

	public UserData LoginRequest(UserDataWrapper udw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		
		notificateServer(Constants.NotificationConstantFrame.USER);
		
		UserData userdata = new UserData();
		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.PORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(udw);
		System.out.println("Sending Login request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		userdata = (UserData) ois.readObject();

		LogUtil.d("checking login");
		// close resources
		socket.close();
		ois.close();
		oos.close();
		Thread.sleep(100);

		return userdata;
	}

	public int SignupRequest(UserDataWrapper udw) throws UnknownHostException,
			IOException, ClassNotFoundException, InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.USER);
		
		UserData userdata = new UserData();

		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.PORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(udw);
		System.out.println("Sending Sign up request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		userdata = (UserData) ois.readObject();
		System.out.println("Message got: " + userdata.getUserid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		Thread.sleep(100);

		return userdata.getUserid();

	}

	public void AddFolderRequest(FolderDataWrapper fdw) throws UnknownHostException,
			IOException, ClassNotFoundException, InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.FOLDER);
		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.PORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(fdw);
		System.out.println("Sending Folder Add request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		fdw = (FolderDataWrapper) ois.readObject();
		System.out.println("Message got: " + fdw.getFolderid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		Thread.sleep(100);
	}
	
	public void notificateServer(String type) throws IOException, ClassNotFoundException, InterruptedException{
		
		// get the localhost IP address, if server is running on some other IP,
				// you need to use that
				InetAddress host = InetAddress.getLocalHost();
				Socket socket = null;
				ObjectOutputStream oos = null;
				ObjectInputStream ois = null;

				// establish socket connection to server
				socket = new Socket(host.getHostName(),
						Constants.NetworkConstantFrame.SUBPORT);

				// write to socket using ObjectOutputStream
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(type);
				System.out.println("Sending Notification :" +type);
				// read the server response message
				ois = new ObjectInputStream(socket.getInputStream());
				type = (String) ois.readObject();
				System.out.println("Checked this is : " + type);
				// close resources
				socket.close();
				ois.close();
				oos.flush();
				oos.close();
				Thread.sleep(100);
		
	}

}

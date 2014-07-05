package kr.re.ec.bibim.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.FolderData;
import kr.re.ec.bibim.vo.NoteData;
import kr.re.ec.bibim.vo.UserData;
import kr.re.ec.bibim.vowrapper.FolderDataWrapper;
import kr.re.ec.bibim.vowrapper.NoteDataWrapper;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

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
				Constants.NetworkConstantFrame.SUBPORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(udw);
		LogUtil.d("Sending request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		udw = (UserDataWrapper) ois.readObject();
		LogUtil.d("Message got: " + udw.getPassword());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);
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
				Constants.NetworkConstantFrame.SUBPORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(udw);
		LogUtil.d("Sending Login request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		userdata = (UserData) ois.readObject();

		LogUtil.d("checking login");
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);

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
				Constants.NetworkConstantFrame.SUBPORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(udw);
		LogUtil.d("Sending Sign up request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		userdata = (UserData) ois.readObject();
		LogUtil.d("Message got: " + userdata.getUserid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);

		return userdata.getUserid();

	}

	public void FolderDeleteRequest(FolderDataWrapper fdw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.FOLDER);

		FolderData folder = new FolderData();

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
		oos.writeObject(fdw);
		LogUtil.d("Sending Folder delete request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		folder = (FolderData) ois.readObject();
		LogUtil.d("Message got: " + folder.getUserid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);

	}

	@SuppressWarnings("unchecked")
	public ArrayList<NoteData> NoteSelectRequest(FolderDataWrapper fdw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.FOLDER);
		ArrayList<NoteData> resultnotes = new ArrayList<NoteData>();

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
		oos.writeObject(fdw);
		LogUtil.d("Sending note select request to Socket Server");
		LogUtil.d("Header is :"+fdw.getQueryHeader() + "Expression is :"+fdw.getExpression());
		LogUtil.d("Sending note select request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		resultnotes = (ArrayList<NoteData>) ois.readObject();
		LogUtil.d("getting notes for this folder");
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);
		return resultnotes;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<FolderData> FolderSelectRequest(UserDataWrapper udw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {

		ArrayList<FolderData> resultfolders = new ArrayList<FolderData>();
		notificateServer(Constants.NotificationConstantFrame.USER);

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
		oos.writeObject(udw);
		LogUtil.d("Sending Folder Select request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		resultfolders = (ArrayList<FolderData>) ois.readObject();

		LogUtil.d("getting all folders");
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);

		return resultfolders;
	}

	public void AddFolderRequest(FolderDataWrapper fdw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.FOLDER);
		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		FolderData folder = new FolderData();

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.SUBPORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(fdw);
		LogUtil.d("Sending Folder Add request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		folder = (FolderData) ois.readObject();
		LogUtil.d("Message got: " + folder.getFolderid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);
	}
	
	public NoteData AddNoteRequest(NoteDataWrapper ndw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.NOTE);
		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		NoteData note = new NoteData();

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.SUBPORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(ndw);
		LogUtil.d("Sending Note Add request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		note = (NoteData) ois.readObject();
		LogUtil.d("Message got: " + note.getNoteid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);
		return note;
	}
	
	public void getANoteRequest(NoteDataWrapper ndw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.NOTE);
		// get the localhost IP address, if server is running on some other IP,
		// you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		NoteData note = new NoteData();

		// establish socket connection to server
		socket = new Socket(host.getHostName(),
				Constants.NetworkConstantFrame.SUBPORT);

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(ndw);
		LogUtil.d("Sending Note get request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		note = (NoteData) ois.readObject();
		LogUtil.d("Message got: " + note.getNoteid());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);
	}
	
	public void NoteDeleteRequest(NoteDataWrapper ndw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.NOTE);

		NoteData note = new NoteData();

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
		oos.writeObject(ndw);
		LogUtil.d("Sending Delete note request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		note = (NoteData) ois.readObject();
		LogUtil.d("deleted: "+ note.getTitle());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);

	}
	
	public void NoteUpdateRequest(NoteDataWrapper ndw)
			throws UnknownHostException, IOException, ClassNotFoundException,
			InterruptedException {
		notificateServer(Constants.NotificationConstantFrame.NOTE);

		NoteData note = new NoteData();

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
		oos.writeObject(ndw);
		LogUtil.d("Sending Update note request to Socket Server");
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		note = (NoteData) ois.readObject();
		LogUtil.d("updated: "+note.getDate());
		// close resources
		socket.close();
		ois.close();
		oos.close();
		// Thread.sleep(20);

	}

	public void notificateServer(String type) throws IOException,
			ClassNotFoundException, InterruptedException {

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
		oos.writeObject(type);
		LogUtil.d("Sending Notification :" + type);
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		type = (String) ois.readObject();
		LogUtil.d("Checked this is : " + type);
		// close resources
		socket.close();
		ois.close();
		oos.flush();
		oos.close();
		// Thread.sleep(20);

	}

}

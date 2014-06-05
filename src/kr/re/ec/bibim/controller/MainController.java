package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import kr.re.ec.bibim.ThisUser;
import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.ui.MainFrame;
import kr.re.ec.bibim.ui.NoteFrame;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.FolderData;
import kr.re.ec.bibim.vowrapper.FolderDataWrapper;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

public class MainController extends MainFrame{
	
	// for singleton
			private static MainController instance = null;

			// for singleton
			static {
				try {
					instance = new MainController();
				} catch (Exception e) {
					throw new RuntimeException("singleton instance intialize error");
				}
			}

			// for singleton
			private MainController() {
			
			}

			// for singleton
			public static MainController getInstance() {
				return instance;
			}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init(){
		super.init();
		getFolderList();
	}
	
	public void getFolderList() {
		ArrayList<FolderData> resultfolders = new ArrayList<FolderData>();
		UserDataWrapper udw = new UserDataWrapper();
		udw = wrapUserDataToSelectFolder();
		try {
			resultfolders = ClientNetworkController.getInstance().FolderSelectRequest(udw);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		foldermodel.removeAllElements();
		changetoModel(resultfolders);
		folderlist.removeAll();
		
		folderlist.setModel(foldermodel);
	}
	public void changetoModel(ArrayList<FolderData> folders){
		
		FolderData fd = new FolderData();
		
		for(int i = 0; i < folders.size(); i++){
			fd = folders.get(i);
			foldermodel.addElement(fd.getName());
		}
	}
	
	public UserDataWrapper wrapUserDataToSelectFolder(){
		
		UserDataWrapper udw = new UserDataWrapper();
		
		udw.setQueryHeader(Constants.QueryHeaderConstantFrame.SELECT);
		udw.setExpression("all");
		//-1 means this is new
		udw.setUserid(ThisUser.getUserid());
		LogUtil.d("This User is "+ThisUser.getUserid());
		udw.setName(null);
		udw.setPassword(null);
		
		return udw;
	}
	public void removeFolder(){
		
		FolderDataWrapper fdw = new FolderDataWrapper();
		String foldername;
		foldername = folderlist.getSelectedValue();
		fdw = wrapFolderDataToDelete(foldername);
		try {
			ClientNetworkController.getInstance().FolderDeleteRequest(fdw);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FolderDataWrapper wrapFolderDataToDelete(String name){
		
		FolderDataWrapper fdw = new FolderDataWrapper();
		fdw.setQueryHeader(Constants.QueryHeaderConstantFrame.DELETE);
		fdw.setExpression(null);
		fdw.setUserid(ThisUser.getUserid());
		fdw.setName(name);
		
		return fdw;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == noteaddbt) {
			
			new NoteFrame().init();
			
		} else if (arg0.getSource() == notermbt) {
			
		} else if (arg0.getSource() == folderaddbt){
			
			new AddFolderController().init();
			
		} else if (arg0.getSource() == folderrmbt) {
			
			removeFolder();
			getFolderList();
			
		}
	}

}

package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import kr.re.ec.bibim.ThisUser;
import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.ui.AddFolderFrame;
import kr.re.ec.bibim.vo.FolderData;
import kr.re.ec.bibim.vo.UserData;
import kr.re.ec.bibim.vowrapper.FolderDataWrapper;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

public class AddFolderController extends AddFolderFrame{
	
	private FolderDataWrapper wrapFolderDataToInsert(FolderData folderdata){
		
		FolderDataWrapper fdw = new FolderDataWrapper();
		
		fdw.setQueryHeader(Constants.QueryHeaderConstantFrame.INSERT);
		fdw.setExpression(null);
		//-1 means this is new
		fdw.setUserid(ThisUser.getUserid());
		fdw.setName(folderdata.getName());
		fdw.setFolderid(-1);
		return fdw;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == okbt) {
			FolderData folder = new FolderData();
			FolderDataWrapper fdw = new FolderDataWrapper();
			folder.setName(foldernametextfield.getText());
			folder.setUserid(ThisUser.getUserid());
			fdw = wrapFolderDataToInsert(folder);
			
			try {
				ClientNetworkController.getInstance().AddFolderRequest(fdw);
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
			
			
		} else if (arg0.getSource() == cancelbt) {
			
		}
	}

}

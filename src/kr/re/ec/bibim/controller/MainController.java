package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JList;

import sun.rmi.runtime.Log;
import kr.re.ec.bibim.ThisUser;
import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.ui.MainFrame;
import kr.re.ec.bibim.ui.PopupFrame;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.FolderData;
import kr.re.ec.bibim.vo.NoteData;
import kr.re.ec.bibim.vowrapper.FolderDataWrapper;
import kr.re.ec.bibim.vowrapper.NoteDataWrapper;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

public class MainController extends MainFrame implements ActionListener{

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

	public void init() {
		super.init();
		getFolderList();

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						selectednote = (NoteData) theList.getModel()
								.getElementAt(index);
						LogUtil.d("Double-clicked on: "
								+ selectednote.toString());
						NoteController note = new NoteController();
						LogUtil.d("selected note :"+ selectednote.getTitle()+"\n"+selectednote.getContent()+selectednote.getNoteid());
						note.setNote(selectednote);
						note.init(selectedfolder);
					}
				}
			}
		};
		MouseListener folderMouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 1 ) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						selectedfolder = (FolderData) theList.getModel()
								.getElementAt(index);
						LogUtil.d("Double-clicked on: "
								+ selectedfolder.toString());
						MainController.getInstance().getNoteList();
						LogUtil.d("fetching notelist");
					}
				}
			}
		};
		folderlist.addMouseListener(folderMouseListener);
		notelist.addMouseListener(mouseListener);
	//	folderlist.addMouseListener(new FolderListMouseListener());
	//	notelist.addMouseListener(new NoteListMouseListener());
		noteaddbt.addActionListener(this);
		notermbt.addActionListener(this);
		folderaddbt.addActionListener(this);
		folderrmbt.addActionListener(this);
	}
	

	/* deprecated
	public class FolderListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getClickCount() == 2){
				int index = folderlist.locationToIndex(e.getPoint());
				LogUtil.d("DoubleClicked on item at index" + index);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class NoteListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getClickCount() == 2){
				int index = folderlist.locationToIndex(e.getPoint());
				LogUtil.d("DoubleClicked on item at index" + index);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	*/

	public void getFolderList() {
		ArrayList<FolderData> resultfolders = new ArrayList<FolderData>();
		UserDataWrapper udw = new UserDataWrapper();
		udw = wrapUserDataToSelectFolder();
		try {
			resultfolders = ClientNetworkController.getInstance()
					.FolderSelectRequest(udw);
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
		changetoModelFolder(resultfolders);
		folderlist.removeAll();

		folderlist.setModel(foldermodel);
	}
	
	public NoteDataWrapper wrapNoteToDelete(NoteData note) {
		NoteDataWrapper ndw = new NoteDataWrapper();

		ndw.setQueryHeader(Constants.QueryHeaderConstantFrame.DELETE);
		ndw.setNoteid(note.getNoteid());

		return ndw;
	}
	
	public void deleteNote(){
		NoteDataWrapper ndw = new NoteDataWrapper();
		NoteData note = new NoteData();
		note = notelist.getSelectedValue();
		ndw = wrapNoteToDelete(note);
		try {
			ClientNetworkController.getInstance().NoteDeleteRequest(ndw);
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
		LogUtil.d("note deleted");
	}

	public void getNoteList() {
		ArrayList<NoteData> resultnotes = new ArrayList<NoteData>();
		FolderDataWrapper fdw = new FolderDataWrapper();
		fdw = wrapFolderDataToGetNotes(selectedfolder);
		LogUtil.d("selectedfolder FID is :" + selectedfolder.getFolderid());
		try {
			resultnotes = ClientNetworkController.getInstance()
					.NoteSelectRequest(fdw);
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
		notemodel.removeAllElements();

		changetoModelNote(resultnotes);

		notelist.removeAll();

		notelist.setModel(notemodel);

		LogUtil.d("get note list called");

	}

	public void changetoModelFolder(ArrayList<FolderData> folders) {

		FolderData fd = new FolderData();

		for (int i = 0; i < folders.size(); i++) {
			fd = folders.get(i);
			foldermodel.addElement(fd);
		}
	}

	public void changetoModelNote(ArrayList<NoteData> notes) {

		NoteData nd = new NoteData();

		for (int i = 0; i < notes.size(); i++) {
			nd = notes.get(i);
			notemodel.addElement(nd);
		}
	}

	public UserDataWrapper wrapUserDataToSelectFolder() {

		UserDataWrapper udw = new UserDataWrapper();

		udw.setQueryHeader(Constants.QueryHeaderConstantFrame.SELECT);
		udw.setExpression(Constants.ExpressionConstantFrame.ALL);
		// -1 means this is new
		udw.setUserid(ThisUser.getUserid());
		LogUtil.d("This User is " + ThisUser.getUserid());
		udw.setName(null);
		udw.setPassword(null);

		return udw;
	}

	public void removeFolder() {

		FolderDataWrapper fdw = new FolderDataWrapper();
		FolderData folder;
		folder = folderlist.getSelectedValue();
		fdw = wrapFolderDataToDelete(folder);
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

	public FolderDataWrapper wrapFolderDataToGetNotes(FolderData folder) {
		FolderDataWrapper fdw = new FolderDataWrapper();
		fdw.setQueryHeader(Constants.QueryHeaderConstantFrame.SELECT);
		fdw.setExpression(Constants.ExpressionConstantFrame.FID);
		fdw.setFolderid(folder.getFolderid());
		fdw.setUserid(ThisUser.getUserid());
		fdw.setName(folder.getName());
		return fdw;
	}

	public FolderDataWrapper wrapFolderDataToDelete(FolderData folder) {

		FolderDataWrapper fdw = new FolderDataWrapper();
		fdw.setQueryHeader(Constants.QueryHeaderConstantFrame.DELETE);
		fdw.setExpression(null);
		fdw.setUserid(ThisUser.getUserid());
		fdw.setName(folder.getName());
		fdw.setFolderid(folder.getFolderid());

		return fdw;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == noteaddbt) {
			if(selectedfolder == null){
				new PopupController("폴더가 선택되지 않았습니다");
				LogUtil.d("popup go");
			} else{
				new NoteController().init(selectedfolder);
			}
			

		} else if (arg0.getSource() == notermbt) {
			
			deleteNote();
			getNoteList();

		} else if (arg0.getSource() == folderaddbt) {

			new AddFolderController().init();

		} else if (arg0.getSource() == folderrmbt) {

			removeFolder();
			getFolderList();

		}
	}

}

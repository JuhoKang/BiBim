package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.ui.NoteFrame;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.FolderData;
import kr.re.ec.bibim.vo.NoteData;
import kr.re.ec.bibim.vowrapper.NoteDataWrapper;

public class NoteController extends NoteFrame implements WindowFocusListener,
		WindowListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	boolean isupdate = false;
	NoteData note = new NoteData();

	public void init(FolderData selectedfolder) {
		super.init();
		String ctrlSave = "Ctrl Save";
		contentarea.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK),
				ctrlSave);
		contentarea.getActionMap().put(ctrlSave, new CtrlsAction());
		titlefield.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK),
				ctrlSave);
		titlefield.getActionMap().put(ctrlSave, new CtrlsAction());
		contentarea.setLineWrap(true);
		contentarea.setWrapStyleWord(true);
		contentarea.requestFocusInWindow();
		this.addWindowFocusListener(this);
		
		LogUtil.d("note title is :"+note.getTitle());
		// default
		if( isupdate == false){
			note.setTitle("제목을 입력하세요");
			note.setContent("내용을 입력하세요");
			note.setFolderid(selectedfolder.getFolderid());
		}
		

		// to do
		// note.setFolderid();

		titlefield.setText(note.getTitle());
		contentarea.setText(note.getContent());
		note.getTitle();
		LogUtil.d("is update is :"+isupdate);

	}
	public void setNote(NoteData note){
		LogUtil.d("income note:"+note.getTitle()+"\t"+note.getContent());
		this.note = note;
		isupdate = true;
	}
	
	public NoteDataWrapper wrapNoteToInsert(NoteData note) {

		NoteDataWrapper ndw = new NoteDataWrapper();

		ndw.setQueryHeader(Constants.QueryHeaderConstantFrame.INSERT);
		ndw.setDate(note.getDate());
		ndw.setFolderid(note.getFolderid());
		ndw.setExpression(null);
		ndw.setTitle(note.getTitle());
		ndw.setNoteid(-1);
		ndw.setContent(note.getContent());

		return ndw;
	}
	public NoteDataWrapper wrapNoteToUpdate(NoteData note){
		NoteDataWrapper ndw = new NoteDataWrapper();

		ndw.setQueryHeader(Constants.QueryHeaderConstantFrame.UPDATE);
		ndw.setDate(note.getDate());
		ndw.setFolderid(note.getFolderid());
		ndw.setContent(note.getContent());
		ndw.setExpression(null);
		ndw.setTitle(note.getTitle());
		ndw.setNoteid(note.getNoteid());
		ndw.setUserid(note.getUserid());

		return ndw;
	}

	

	public NoteDataWrapper wrapNoteToSelect(NoteData note) {
		NoteDataWrapper ndw = new NoteDataWrapper();

		ndw.setQueryHeader(Constants.QueryHeaderConstantFrame.SELECT);
		ndw.setNoteid(note.getNoteid());

		return ndw;
	}

	public Date getCurrentDate() {
		Date curdate = new Date();
		curdate = Calendar.getInstance().getTime();
		return curdate;
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		updateorsave();
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		// save
		updateorsave();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		LogUtil.d("made a note window");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// save
		updateorsave();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		updateorsave();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void saveNote() {
		NoteDataWrapper ndw = new NoteDataWrapper();
		note.setTitle(titlefield.getText());
		note.setContent(contentarea.getText());
		LogUtil.d("contentarea: "+contentarea.getText() );
		note.setDate(getCurrentDate().toString());
		LogUtil.d("note date is :" + note.getDate());
		ndw = wrapNoteToInsert(note);
		try {
			ClientNetworkController.getInstance().AddNoteRequest(ndw);
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
		
		LogUtil.d("saved note");
		
		
	}
	public void updateNote(){
		
		NoteDataWrapper ndw = new NoteDataWrapper();
		note.setTitle(titlefield.getText());
		note.setContent(contentarea.getText());
		note.setDate(getCurrentDate().toString());
		LogUtil.d("note date is :" + note.getDate());
		ndw = wrapNoteToUpdate(note);
		
		try {
			ClientNetworkController.getInstance().NoteUpdateRequest(ndw);
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
		LogUtil.d("updated note");
	
	}

	protected class CtrlsAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			updateorsave();
			LogUtil.d("Ctrl S pressed!");
		}
	}
	public void updateorsave(){
		LogUtil.d("is update is :"+isupdate);
		if(isupdate == false){
			saveNote();
			MainController.getInstance().getNoteList();
		} else {
			updateNote();
			MainController.getInstance().getNoteList();
		}
		if(isupdate == false){
			setNote(MainController.getInstance().notemodel.lastElement());
			isupdate = true;
		}
		
	}
}

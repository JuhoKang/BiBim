package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;

import kr.re.ec.bibim.ui.MainFrame;

public class MainController extends MainFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == noteaddbt) {
			
		} else if (arg0.getSource() == notermbt) {
			
		} else if (arg0.getSource() == folderaddbt){
			
			new AddFolderController().init();
			
		} else if (arg0.getSource() == folderrmbt) {
			
		}
	}

}

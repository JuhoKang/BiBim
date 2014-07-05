package kr.re.ec.bibim.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import kr.re.ec.bibim.ui.PopupFrame;

public class PopupController extends PopupFrame implements WindowFocusListener,MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4455161939334951939L;
	public PopupController(String str){
		super(str);
		this.addWindowFocusListener(this);
		this.addMouseListener(this);
	}

	
	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
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

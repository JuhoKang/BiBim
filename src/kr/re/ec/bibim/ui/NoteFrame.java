package kr.re.ec.bibim.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NoteFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6075502418735923921L;
	protected JTextField titlefield = new JTextField(10);
	protected JTextArea contentarea = new JTextArea(10,50);
	private boolean topview = true;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	public void init(){
		
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		this.setLayout(gbl);
		//gbc.fill = GridBagConstraints.NONE;
		//gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridwidth=1;
		gbc.insets.right = 10;
		gbc.insets.top = 10;
		this.add(new JLabel("제목"),gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		gbc.gridwidth=3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(titlefield,gbc);
		gbc.gridwidth=1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.1;	
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new JLabel("내용"),gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(contentarea,gbc);
		setSize(400, 400);
		setVisible(true);
		setAlwaysOnTop(topview);
	}
	
}

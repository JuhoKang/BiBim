package kr.re.ec.bibim.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;


class PopupFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel jlb = new JLabel("");
	//JFrame jlb = new JFrame();

	public PopupFrame(String str) {

		getContentPane().add(jlb);

		jlb.setText(str.toString());

		// 전체 창 사이즈 가져오는거
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// 컴퓨터 화면 크기가 다르지만 모든 컴퓨터의 화면에 -- 창을 중앙에 위치시킬 수 있다.
		int xPos = screenSize.width / 2 - this.getSize().width / 2;
		int yPos = screenSize.height / 2 - this.getSize().height / 2;

		this.setLocation(xPos-50, yPos-50);

		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setSize(150, 150);
		this.setVisible(true);
		

	}
}

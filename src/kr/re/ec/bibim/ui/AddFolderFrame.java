package kr.re.ec.bibim.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFolderFrame implements ActionListener {
	private boolean topview = true;
	private Container con;
	protected JFrame jf= new JFrame();
	protected JTextField foldernametextfield = new JTextField(20); // 아이디를 쓸 텍스트 필드
	protected JButton okbt = new JButton("확인");
	protected JButton cancelbt = new JButton("취소");

	public void init() {
		
		jf.setSize(350, 100);
		jf.setAlwaysOnTop(topview);
		jf.setTitle("폴더제목");
		jf.setVisible(true);
		
		// 전체 창 사이즈 가져오는거네
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// 컴퓨터 화면 크기가 다르지만 모든 컴퓨터의 화면에 -- 창을 중앙에 위치시킬 수 있다.
		int xPos = screenSize.width / 2 - jf.getSize().width / 2;
		int yPos = screenSize.height / 2 - jf.getSize().height / 2;

		jf.setLocation(xPos, yPos);
		jf.setResizable(false);

		/*
		 * jf.addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) { System.exit(1); } });
		 */

		// 화면구성
		con = jf.getContentPane();
		JPanel pn1 = new JPanel();
		pn1.add(new JLabel("제목"));
		pn1.add(foldernametextfield);
		JPanel pn2 = new JPanel();
		pn2.add(okbt);
		pn2.add(cancelbt);
		con.setLayout(new GridLayout(2, 1));
		con.add(pn1);
		con.add(pn2);		
		
		okbt.addActionListener(this);
		cancelbt.addActionListener(this);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == okbt) {
			
		} else if (arg0.getSource() == cancelbt) {
			
		}
	}

}
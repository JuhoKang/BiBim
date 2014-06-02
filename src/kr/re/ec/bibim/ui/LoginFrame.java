package kr.re.ec.bibim.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends AbstractFrame {
	private boolean topview = true;
	 private Container con;
	 private JTextField tf1 = new JTextField(10);  //아이디를 쓸 텍스트 필드
	 private JPasswordField tf2 = new JPasswordField (10);  //비밀번호를 쓸 패스워드 필드
	 private JButton bt1 = new JButton("등록");
	 private JButton bt2 = new JButton("확인");
	
	public void init(){
		JFrame jf = new JFrame();	
		jf.setSize(500,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫으면 프로그램 종료
		jf.setAlwaysOnTop(topview); 
		jf.setTitle("로그인");
		jf.setVisible(true);
		
		//전체 창 사이즈 가져오는거네
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize.width+","+screenSize.height);

		//컴퓨터 화면 크기가 다르지만 모든 컴퓨터의 화면에 -- 창을 중앙에 위치시킬 수 있다.
		int xPos = screenSize.width/2 - jf.getSize().width/2;
		int yPos = screenSize.height/2 - jf.getSize().height/2;
		
		jf.setLocation(xPos,yPos);
		jf.setResizable(false);
		
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});	
		
		// 화면구성
		con = jf.getContentPane();
		JPanel pn1 = new JPanel(new FlowLayout());
		pn1.add(new JLabel("ID"));
		pn1.add(tf1);
		JPanel pn2 = new JPanel(new FlowLayout());
		pn2.add(new JLabel("Password"));
		pn2.add(tf2);
		JPanel pn3 = new JPanel(new FlowLayout());
		pn3.add(bt1);
		pn3.add(bt2);
		con.setLayout(new GridLayout(3, 1));
		con.add(pn1);
		con.add(pn2);
		con.add(pn3);
		
	}
}
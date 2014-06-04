package kr.re.ec.bibim.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupFrame implements ActionListener {
	private boolean topview = true;
	private Container con;
	protected JTextField idtextfield = new JTextField(10); // 아이디를 쓸 텍스트 필드
	protected JPasswordField pwdtextfield = new JPasswordField(10); // 비밀번호를 쓸 패스워드 필드
	protected JPasswordField pwdchecktextfield = new JPasswordField(10); // 비밀번호 확인 패스워드 필드
	protected JButton signupbt = new JButton("가입");
	protected JButton loginbt = new JButton("취소");

	public void init() {
		JFrame jf = new JFrame();
		jf.setSize(500, 300);
		jf.setAlwaysOnTop(topview);
		jf.setTitle("회원가입");
		jf.setVisible(true);
		
		SwingUtilities.updateComponentTreeUI(signupbt);

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
		JPanel pn1 = new JPanel(new GridLayout(1,2));
		pn1.add(new JLabel("아이디"));
		pn1.add(idtextfield);
		JPanel pn2 = new JPanel(new GridLayout(1,2));
		pn2.add(new JLabel("비밀번호"));
		pn2.add(pwdtextfield);
		JPanel pn3 = new JPanel(new GridLayout(1,2));
		pn3.add(new JLabel("비밀번호 확인"));
		pn3.add(pwdchecktextfield);
		JPanel pn4 = new JPanel(new GridLayout(1,2));
		pn4.add(signupbt);
		pn4.add(loginbt);
		con.setLayout(new GridLayout(4, 1));
		con.add(pn1);
		con.add(pn2);
		con.add(pn3);
		con.add(pn4);
		
		//signupbt.putClientProperty("JComponent.sizeVariant","mini");

		signupbt.addActionListener(this);
		loginbt.addActionListener(this);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == signupbt) {
			
		} else if (arg0.getSource() == loginbt) {
			
		}
	}

}
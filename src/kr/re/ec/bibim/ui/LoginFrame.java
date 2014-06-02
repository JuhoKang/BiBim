package kr.re.ec.bibim.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame implements ActionListener {
	private boolean topview = true;
	private Container con;
	private JTextField idtextfield = new JTextField(10); // 아이디를 쓸 텍스트 필드
	private JPasswordField pwdtextfield = new JPasswordField(10); // 비밀번호를 쓸
																	// 패스워드 필드
	private JButton signupbt = new JButton("가입");
	private JButton loginbt = new JButton("확인");

	public void init(){
		JFrame jf = new JFrame();	
		jf.setSize(500,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫으면 프로그램 종료
		jf.setAlwaysOnTop(topview); 
		jf.setTitle("로그인");
		jf.setVisible(true);
		
		//전체 창 사이즈 가져오는거네
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		//컴퓨터 화면 크기가 다르지만 모든 컴퓨터의 화면에 -- 창을 중앙에 위치시킬 수 있다.
		int xPos = screenSize.width/2 - jf.getSize().width/2;
		int yPos = screenSize.height/2 - jf.getSize().height/2;
		
		jf.setLocation(xPos,yPos);
		jf.setResizable(false);
		
	/*	jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});	 */
		
		// 화면구성
		con = jf.getContentPane();
		JPanel pn1 = new JPanel(new FlowLayout());
		pn1.add(new JLabel("ID"));
		pn1.add(idtextfield);
		JPanel pn2 = new JPanel(new FlowLayout());
		pn2.add(new JLabel("Passward"));
		pn2.add(pwdtextfield);
		JPanel pn3 = new JPanel(new FlowLayout());
		pn3.add(signupbt);
		pn3.add(loginbt);
		con.setLayout(new GridLayout(3, 1));
		con.add(pn1);
		con.add(pn2);
		con.add(pn3);
		
		
		signupbt.addActionListener(this);
		loginbt.addActionListener(this);
	
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    if(arg0.getSource() == signupbt || arg0.getSource() == loginbt){
            new PopupFrame(arg0.getActionCommand() + " 버튼을 누르셨군요!");
    }
}

}
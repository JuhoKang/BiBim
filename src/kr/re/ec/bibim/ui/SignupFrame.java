package kr.re.ec.bibim.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupFrame implements ActionListener {
	private boolean topview = true;
	protected JTextField idtextfield = new JTextField(10); // 아이디를 쓸 텍스트 필드
	protected JPasswordField pwdtextfield = new JPasswordField(10); // 비밀번호를 쓸 패스워드 필드
	protected JPasswordField pwdchecktextfield = new JPasswordField(10); // 비밀번호 확인 패스워드 필드
	protected JButton signupbt = new JButton("가입");
	protected JButton cancelbt = new JButton("취소");
	GridBagLayout gbl;
	GridBagConstraints gbc;

	public void init() {
		JFrame jf = new JFrame();
		jf.setSize(400, 200);
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
		gbl = new GridBagLayout();
		jf.setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.2;
		gbc.insets.right = 10;
		jf.add(new JLabel("아이디"),gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		jf.add(idtextfield,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		gbc.fill = GridBagConstraints.NONE;
		jf.add(new JLabel("비밀번호"),gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		jf.add(pwdtextfield,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		gbc.fill = GridBagConstraints.NONE;
		jf.add(new JLabel("비밀번호 확인"),gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		jf.add(pwdchecktextfield,gbc);
		JPanel pn1 = new JPanel();
		pn1.add(signupbt);
		pn1.add(cancelbt);
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		jf.add(pn1,gbc);
		
		//signupbt.putClientProperty("JComponent.sizeVariant","mini");

		signupbt.addActionListener(this);
		cancelbt.addActionListener(this);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == signupbt) {
			
		} else if (arg0.getSource() == cancelbt) {
			
		}
	}

}
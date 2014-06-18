package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import kr.re.ec.bibim.constants.Constants;
import kr.re.ec.bibim.ui.PopupFrame;
import kr.re.ec.bibim.ui.SignupFrame;
import kr.re.ec.bibim.vo.UserData;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

public class SignupController extends SignupFrame{
	
	public UserDataWrapper wrapUserDataToSignup (UserData userdata){
		
		UserDataWrapper udw = new UserDataWrapper();
		
		udw.setQueryHeader(Constants.QueryHeaderConstantFrame.INSERT);
		udw.setExpression(null);
		udw.setName(userdata.getName());
		udw.setPassword(userdata.getPassword());
		udw.setUserid(-1);
		return udw;
	}
	
	public boolean checkEquals (String pwd, String checkpwd){
		
		boolean isequal = false;
		isequal = pwd.equals(checkpwd);
		return isequal;
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == signupbt) {
			
			int checkservice = 0;
			boolean isequal = false;
			//getText() is a deprecated method should change
			if(checkEquals(pwdtextfield.getText(), pwdchecktextfield.getText()) == true) {
				isequal = true;
			}
			
			UserData signupuser = new UserData();
			UserDataWrapper udw = new UserDataWrapper();
			signupuser.setName(idtextfield.getText());
			//getText() is a deprecated method should change
			signupuser.setPassword(pwdtextfield.getText());
			
			udw = wrapUserDataToSignup(signupuser);
			if( isequal == false ){
				new PopupFrame(arg0.getActionCommand() + " 비밀번호와 비밀번호확인이 같지 않습니다 ");
			}
			else {
				try {
					checkservice = ClientNetworkController.getInstance().SignupRequest(udw);
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
				
				switch(checkservice) {
				case -1:
					new PopupFrame(arg0.getActionCommand() + " 이미 있는 아이디 ");
					break;
				case 0:
					new PopupFrame(arg0.getActionCommand() + " 서버가 꺼져 있습니다 ");
					break;
				default :
					new PopupFrame(arg0.getActionCommand() + " 회원가입 성공 ");
					break;
				}
				
				jf.dispose();
				
			}
			
			
								
			
			
		} else if (arg0.getSource() == cancelbt) {
			jf.dispose();
		}
	}

}

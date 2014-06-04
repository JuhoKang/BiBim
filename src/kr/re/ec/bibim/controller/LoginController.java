package kr.re.ec.bibim.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import kr.re.ec.bibim.ui.LoginFrame;
import kr.re.ec.bibim.ui.MainFrame;
import kr.re.ec.bibim.ui.PopupFrame;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.UserData;
import kr.re.ec.bibim.vowrapper.UserDataWrapper;

public class LoginController extends LoginFrame{
	
	public void login(){
		
	}
	
	private UserDataWrapper wrapUserDataToLogin(UserData userdata){
		
		UserDataWrapper udw = new UserDataWrapper();
		
		udw.setQueryHeader("Login");
		udw.setExpression(null);
		//-1 means this is new
		udw.setUserid(-1);
		udw.setName(userdata.getName());
		udw.setPassword(userdata.getPassword());
		
		return udw;
	}
	
	public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
	    if(arg0.getSource() == loginbt){
	    		UserData loginuser = new UserData();
	    		UserDataWrapper udw = new UserDataWrapper();
	    		
	    		loginuser.setName(idtextfield.getText());
	    		loginuser.setPassword(pwdtextfield.getText());
	    		
	    		udw = wrapUserDataToLogin(loginuser);
	    		
	    		try {
					loginuser = ClientNetworkController.getInstance().LoginRequest(udw);
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
	    		LogUtil.d("userid : "+loginuser.getUserid());
	    		if(loginuser.getUserid() < 0){
	    			new PopupFrame(arg0.getActionCommand() + " 회원이 아닙니다 회원가입을 해주세요 ");
	    		}
	    		else {
	    			//new PopupFrame(arg0.getActionCommand() + " 로그인 완료 ");
	    			new MainFrame();
	    		}
	    		
	            
	    }
	}

}

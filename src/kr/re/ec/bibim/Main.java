package kr.re.ec.bibim;

import kr.re.ec.bibim.controller.LoginController;
import kr.re.ec.bibim.ui.LoginFrame;
import kr.re.ec.bibim.ui.SignupFrame;

public class Main {
	public static void main(String[] args) {
//changed something new LoginFrame().init();
		new LoginController().init();
		//new SignupFrame().init();

	}
}

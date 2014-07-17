package kr.re.ec.bibim;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.re.ec.bibim.controller.LoginController;


public class Main {
	public static void main(String[] args) {
//changed something new LoginFrame().init();
		Logger logger = LoggerFactory.getLogger(Main.class);
		logger.info("hello!");
		logger.info(""+logger.isDebugEnabled());
		//assas
		logger.debug("shit");
		new LoginController().init();
		//new SignupFrame().init();

	}
}

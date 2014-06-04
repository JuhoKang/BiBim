package kr.re.ec.bibim;

public class ThisUser {
	
	private static int userid;

	public static int getUserid() {
		return userid;
	}

	public static void setUserid(int userid) {
		ThisUser.userid = userid;
	}

}

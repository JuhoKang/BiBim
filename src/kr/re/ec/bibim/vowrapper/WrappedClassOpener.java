package kr.re.ec.bibim.vowrapper;


import kr.re.ec.bibim.vo.UserData;

public class WrappedClassOpener {
	
	//for singleton
		private static WrappedClassOpener instance = null;
		
		//for singleton
		static{
			try{
				instance = new WrappedClassOpener();
			} catch(Exception e){
				throw new RuntimeException("singleton instance intialize error");
			}
		}
		
		//for singleton
		private WrappedClassOpener(){
		}
		
		//for singleton
		public static WrappedClassOpener getInstance(){
			return instance;
		}
	
	public UserData OpenUserDataWrapper(UserDataWrapper udw)
	{
		UserData ud = new UserData();
		
		ud.setUserid(udw.getUserid());
		ud.setName(udw.getName());
		ud.setPassword(udw.getPassword());
		
		return ud;
	}

}

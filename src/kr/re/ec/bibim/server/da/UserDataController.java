package kr.re.ec.bibim.server.da;

import kr.re.ec.bibim.constants.Constants;

/**
 * DAO(Data Access Object) for controlling DB<br>
 * UserData
 * 
 * @author Kang Juho
 * @version 1.0
 */

public class UserDataController extends DataAccess{
	
	public UserDataController()
	{
		super(Constants.UserListDataBaseConstantFrame.DB_NAME);
	}

}

package kr.re.ec.bibim.constants;

/**
 * 
 * @author Kang Juho
 *
 */

public class Constants {
	
	public static abstract class DataBaseConstantFrame{
		
		// DB name,version
				public static final String DB_NAME = "BiBimDB.db";
				public static final int DB_VERSION = 1;

				
	
	}
	
	public static abstract class UserConstantFrame{
		
		// table name
		public static final String TABLE_NAME = "user";
		// column name
		public static final String COLUMN_NAME_USERID = "userid";
		public static final String COLUMN_NAME_USERNAME = "username";
		public static final String COLUMN_NAME_USERPWD = "userpwd";
		
	}
	
	public static abstract class MessageNoteConstantFrame{
		
		// table name
		public static final String TABLE_NAME = "messagenote";
		// column name
		public static final String COLUMN_NAME_MESSAGENOTEID = "id";
		public static final String COLUMN_NAME_MESSAGENOTENAME = "name";
		public static final String COLUMN_NAME_USERPWD = "userpwd";
		
	}

	public static abstract class NoteConstantFrame{
	
	}

}

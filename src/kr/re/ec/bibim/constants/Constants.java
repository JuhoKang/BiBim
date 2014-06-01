package kr.re.ec.bibim.constants;

/**
 * 
 * Constants for DB connecting
 * 
 * @author Kang Juho
 * @version 1.0
 * 
 */

public class Constants {

	public static abstract class DataBaseConstantFrame {

		// DB name,version
		public static final String DB_NAME = "BiBimDB.db";
		public static final int DB_VERSION = 1;

	}
	
	public static abstract class UserConstantFrame {

		// table name
		public static final String TABLE_NAME = "user";
		// column name
		public static final String COLUMN_NAME_USERID = "userid";
		public static final String COLUMN_NAME_USERNAME = "username";
		public static final String COLUMN_NAME_USERPWD = "userpwd";

	}

	public static abstract class FolderConstantFrame {

		// table name
		public static final String TABLE_NAME = "messagenote";
		// column name
		public static final String COLUMN_NAME_USERID = "userid";
		public static final String COLUMN_NAME_FOLDERID = "folderid";
		public static final String COLUMN_NAME_FOLDERNAME = "foldername";

	}

	public static abstract class MessageNoteConstantFrame {

		// table name
		public static final String TABLE_NAME = "messagenote";
		// column name
		public static final String COLUMN_NAME_USERID = "userid";
		public static final String COLUMN_NAME_MESSAGENOTEID = "messagenoteid";
		public static final String COLUMN_NAME_MESSAGENOTETITLE = "messagenotetitle";
		public static final String COLUMN_NAME_FOLDERID = "folderid";
		public static final String COLUMN_NAME_MESSAGENOTECONTENT = "messagenotecontent";
		public static final String COLUMN_NAME_MESSAGESENDTIME = "messagesendtime";
		public static final String COLUMN_NAME_MESSAGESENDER = "messagesender";

	}

	public static abstract class NoteConstantFrame {

		// table name
		public static final String TABLE_NAME = "note";
		// column name
		public static final String COLUMN_NAME_USERID = "userid";
		public static final String COLUMN_NAME_NOTEID = "noteid";
		public static final String COLUMN_NAME_FOLDERID = "folderid";
		public static final String COLUMN_NAME_NOTETITLE = "notetitle";
		public static final String COLUMN_NAME_NOTECONTENT = "notecontent";
		public static final String COLUMN_NAME_NOTEDATE = "notedate";

		
	}

}

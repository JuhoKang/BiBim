package kr.re.ec.bibim.vo;

public class NoteData {
	
	private int noteid;
	private int userid;
	private int folderid;
	private String title;
	private String content;
	private String date;
	public int getNoteid() {
		return noteid;
	}
	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFolderid() {
		return folderid;
	}
	public void setFolderid(int folderid) {
		this.folderid = folderid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}

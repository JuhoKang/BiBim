package kr.re.ec.bibim.vo;

public class MessageNoteData {
	
	private int messagenoteid;
	private String title;
	private int userid;
	private int folderid;
	private String content;
	private String sendtime;
	private String sender;
	
	public int getMessagenoteid() {
		return messagenoteid;
	}
	public void setMessagenoteid(int messagenoteid) {
		this.messagenoteid = messagenoteid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	

}

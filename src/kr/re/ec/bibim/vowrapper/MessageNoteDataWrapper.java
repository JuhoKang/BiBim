package kr.re.ec.bibim.vowrapper;

import java.io.Serializable;

import kr.re.ec.bibim.vo.MessageNoteData;

/**
 * Wrapper Classes are for wrapping VO(Value Object)Classes for object input socket<br>
 * It wraps the classes by adding a String QueryHeader, String expression
 * 
 * @author Kang Juho
 * @version 1.0
 */

public class MessageNoteDataWrapper extends MessageNoteData implements Serializable{
	
	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String QueryHeader;
	private String expression;
	
	public String getQueryHeader() {
		return QueryHeader;
	}
	public void setQueryHeader(String queryHeader) {
		QueryHeader = queryHeader;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}

}

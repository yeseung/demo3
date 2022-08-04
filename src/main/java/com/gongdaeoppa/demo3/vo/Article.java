package com.gongdaeoppa.demo3.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	
	private String regDate;
	private String updateDate;	

	private int memberId;
	
	private String title;
	private String body;
	
	private int hitCount;
	
	
	
	

	private int goodReactionPoint;
	private int badReactionPoint;
	
	
	
	
	
	private String extra__writerName;
	private boolean extra__actorCanDelete;
	
	private boolean extra__actorCanModify;
	
	
	
	//private int extra__sumReactionPoint;
	//private int extra__goodReactionPoint;
	//private int extra__badReactionPoint;
	
	
	
	
	
	
	
	/*public String getRegDateForPrint() {
		return regDate.substring(2, 16);
	}

	public String getUpdateDateForPrint() {
		return updateDate.substring(2, 16);
	}*/
	
	
	public String getForPrintType1RegDate() {
		return regDate.substring(2, 16).replace(" ", "<br>");
	}

	public String getForPrintType1UpdateDate() {
		return updateDate.substring(2, 16).replace(" ", "<br>");
	}

	public String getForPrintType2RegDate() {
		return regDate.substring(2, 16);
	}

	public String getForPrintType2UpdateDate() {
		return updateDate.substring(2, 16);
	}
	
	
	
	
	public String getForPrintBody() {
		return body.replaceAll("\n", "<br>");
	}
	
} 
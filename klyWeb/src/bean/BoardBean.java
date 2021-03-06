package bean;

import java.util.Date;

public class BoardBean {
	private String MEMBER_ID;
	private int BOARD_NUM;	//글 번호
	private String BOARD_SUBJECT;	//글제목
	private Date BOARD_DATE;	//작성 시간
	private String BOARD_VIDEO_FILE;	//동영상 파일
	private String BOARD_VIDEO_URL;	//동영상 URL
	private int BOARD_READCOUNT;	//게시물 조회수
	private int BOARD_LIKECOUNT;	//좋아요 수
	private int BOARD_BLIND;	//게시글 차단 여부
	private String BOARD_TAG;	//태그
	private String BOARD_CATEGORY;	//카테고리
	private int BOARD_REPORT_NUM;	//신고 횟수
	private Date BOARD_REPORT_DATE;	//신고 날짜
	
	
	public int getBOARD_REPORT_NUM() {
		return BOARD_REPORT_NUM;
	}
	public void setBOARD_REPORT_NUM(int bOARD_REPORT_NUM) {
		BOARD_REPORT_NUM = bOARD_REPORT_NUM;
	}
	public Date getBOARD_REPORT_DATE() {
		return BOARD_REPORT_DATE;
	}
	public void setBOARD_REPORT_DATE(Date bOARD_REPORT_DATE) {
		BOARD_REPORT_DATE = bOARD_REPORT_DATE;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getBOARD_CATEGORY() {
		return BOARD_CATEGORY;
	}
	public void setBOARD_CATEGORY(String bOARD_CATEGORY) {
		BOARD_CATEGORY = bOARD_CATEGORY;
	}
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(Date bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}
	public String getBOARD_VIDEO_FILE() {
		return BOARD_VIDEO_FILE;
	}
	public void setBOARD_VIDEO_FILE(String bOARD_VIDEO_FILE) {
		BOARD_VIDEO_FILE = bOARD_VIDEO_FILE;
	}
	public String getBOARD_VIDEO_URL() {
		return BOARD_VIDEO_URL;
	}
	public void setBOARD_VIDEO_URL(String bOARD_VIDEO_URL) {
		BOARD_VIDEO_URL = bOARD_VIDEO_URL;
	}
	public int getBOARD_READCOUNT() {
		return BOARD_READCOUNT;
	}
	public void setBOARD_READCOUNT(int bOARD_READCOUNT) {
		BOARD_READCOUNT = bOARD_READCOUNT;
	}
	public int getBOARD_LIKECOUNT() {
		return BOARD_LIKECOUNT;
	}
	public void setBOARD_LIKECOUNT(int bOARD_LIKECOUNT) {
		BOARD_LIKECOUNT = bOARD_LIKECOUNT;
	}
	public int getBOARD_BLIND() {
		return BOARD_BLIND;
	}
	public void setBOARD_BLIND(int bOARD_BLIND) {
		BOARD_BLIND = bOARD_BLIND;
	}
	public String getBOARD_TAG() {
		return BOARD_TAG;
	}
	public void setBOARD_TAG(String bOARD_TAG) {
		BOARD_TAG = bOARD_TAG;
	}
	
}

package dao;

import static db.JDBCUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.org.apache.regexp.internal.RE;

import bean.BoardBean;
import bean.CommentBean;
import bean.LikeBean;
import bean.ReportBean;

public class BoardDAO {
	DataSource ds;
	ResultSet rs = null;
	Connection con;
	PreparedStatement pstmt = null;
	
	private static BoardDAO BoardDAO;

	public static BoardDAO getInstance() {
		if(BoardDAO == null) {
			BoardDAO = new BoardDAO();
		}
		return BoardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	

	public int updatecount(int me) {
		int updatecount = 0;
		String sql1 ="UPDATE board SET BOARD_READCOUNT=(BOARD_READCOUNT+1) WHERE BOARD_NUM=?";
		
		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, me);
			updatecount = pstmt.executeUpdate();
			System.out.println("조회수올려짐");
		} catch(Exception e) {
			System.out.println("조회수 올리기 오류");
		} finally {
			close(pstmt);
		}
		return updatecount;
	}

	public  BoardBean getboard(int me) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_NUM = ?";
		BoardBean Boardbean = null;
		try {
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,me);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Boardbean = new BoardBean();
				Boardbean.setMEMBER_ID(rs.getString(1));
				Boardbean.setBOARD_NUM(rs.getInt(2));
				Boardbean.setBOARD_SUBJECT(rs.getString(3));
				Boardbean.setBOARD_DATE(rs.getDate(4));
				Boardbean.setBOARD_VIDEO_FILE(rs.getString(5));
				Boardbean.setBOARD_VIDEO_URL(rs.getString(6));
				Boardbean.setBOARD_READCOUNT(rs.getInt(7));
				Boardbean.setBOARD_LIKECOUNT(rs.getInt(8));
				Boardbean.setBOARD_BLIND(rs.getInt(9));
				Boardbean.setBOARD_TAG(rs.getString(10));
				Boardbean.setBOARD_CATEGORY(rs.getString(11));
			}
		} catch(Exception e) {
			System.out.println("오류");
		} finally {
			close(rs);
			close(pstmt);
		}
		return Boardbean;
	}
	
	public  ArrayList<LikeBean> getLikeList(){
		String sql = "SELECT * FROM LIKED";
		ArrayList<LikeBean> likeList = new ArrayList<LikeBean>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LikeBean like = new LikeBean();
				like.setLIKE_NUM(rs.getInt(1));
				like.setMEMBER_ID(rs.getString(2));
				like.setBOARD_NUM(rs.getInt(3));
				likeList.add(like);
			}
		}catch(Exception e) {
			System.out.println("likeList 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return likeList;
		
	}
	
	
	
	public int registArticle(BoardBean boardBean) {
		int num = 0;
		int insertResult = 0;
		String sql1 = "SELECT MAX(BOARD_NUM) FROM BOARD";
		String sql2 = "INSERT INTO BOARD ("
				+ "MEMBER_ID, BOARD_NUM, BOARD_SUBJECT, BOARD_DATE, BOARD_URL, BOARD_READCOUNT,"
				+ "BOARD_LIKECOUNT, BOARD_BLIND, BOARD_TAG, BOARD_CATEGORY) "
				+ "VALUES (?,?,?,SYSDATE,?,?,?,?,?,?)";
		
		try {
			//글번호를 1씩 증가시키기위한 쿼리문
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num=1;
			}
			//글등록을 하는 부분,물음표 부분 채우기
			pstmt = con.prepareStatement(sql2);
			
			pstmt.setString(1, boardBean.getMEMBER_ID());
			pstmt.setInt(2, num); // 글 번호
			pstmt.setString(3, boardBean.getBOARD_SUBJECT());
			pstmt.setString(4, boardBean.getBOARD_VIDEO_URL());
			pstmt.setInt(5, 0); // 조회 수
			pstmt.setInt(6, 0); // 추천수
			pstmt.setInt(7, 0); // 신고 수
			pstmt.setString(8, boardBean.getBOARD_TAG());
			pstmt.setString(9, boardBean.getBOARD_CATEGORY());
			
			//쿼리문 실행
			insertResult = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("registArticle 오류 !! : "+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return insertResult;

	}
	
	public ArrayList<BoardBean> getMemberArticle(String memberID) {
		String sql = "SELECT * FROM BOARD WHERE MEMBER_ID = ?";
		
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		System.out.println("BoardDAO 로 넘어온 boardID의 값 : "+memberID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean boardBean = new BoardBean();
				boardBean.setMEMBER_ID(rs.getString("MEMBER_ID"));
				boardBean.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				boardBean.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				boardBean.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				boardBean.setBOARD_VIDEO_URL(rs.getString("BOARD_URL"));
				boardBean.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				boardBean.setBOARD_BLIND(rs.getInt("BOARD_BLIND"));
				boardBean.setBOARD_TAG(rs.getString("BOARD_TAG"));
				boardBean.setBOARD_CATEGORY(rs.getString("BOARD_CATEGORY"));
				articleList.add(boardBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return articleList;
	}

	public ArrayList<BoardBean> getBoardList(int page, int limit) {
		String sql = "SELECT * FROM (SELECT ROWNUM RN2, V1. * FROM "
					+ "(SELECT * FROM BOARD ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC) V1) V2 WHERE V2.RN2 BETWEEN ? AND ?";
		
		int startrow = (page-1)*limit+1;
		int endrow = page*limit;
		
		ArrayList<BoardBean> boardList = 
				new ArrayList<BoardBean>();
		BoardBean boardBean = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				do {
					boardBean = new BoardBean();
					boardBean.setMEMBER_ID(rs.getString(1));
					boardBean.setBOARD_NUM(rs.getInt(2));
					boardBean.setBOARD_SUBJECT(rs.getString(3));
					boardBean.setBOARD_DATE(rs.getDate(4));
					boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
					boardBean.setBOARD_VIDEO_URL(rs.getString(6));
					boardBean.setBOARD_READCOUNT(rs.getInt(7));
					boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
					boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
					boardBean.setBOARD_TAG(rs.getString(10));
					boardBean.setBOARD_CATEGORY(rs.getString(11));
					boardBean.setBOARD_BLIND(rs.getInt(12));
					boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
					boardList.add(boardBean);		
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
	
		
		return boardList;
		
	}

	public int getListCount() {
		int listCount = 0;
		String sql = "SELECT COUNT(*) FROM BOARD";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	/** 1.전체목록을 볼때 쓰는 리스트 메소드 */
	public ArrayList<BoardBean> getboardlist() {	
		String sql = "SELECT * FROM BOARD";
		ArrayList<BoardBean> boardList =new ArrayList<BoardBean>();			
		BoardBean boardBean = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();					
			while(rs.next()) {
				boardBean = new BoardBean();
				boardBean.setMEMBER_ID(rs.getString(1));
				boardBean.setBOARD_NUM(rs.getInt(2));
				boardBean.setBOARD_SUBJECT(rs.getString(3));
				boardBean.setBOARD_DATE(rs.getDate(4));
				boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
				boardBean.setBOARD_VIDEO_URL(rs.getString(6));
				boardBean.setBOARD_READCOUNT(rs.getInt(7));
				boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
				boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
				boardBean.setBOARD_TAG(rs.getString(10));
				boardBean.setBOARD_CATEGORY(rs.getString(11));
				boardBean.setBOARD_BLIND(rs.getInt(12));
				boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
				boardList.add(boardBean);					
			}		
		}catch(Exception e) {
			System.out.println("list 오류"+e);
		}finally {
			close(rs);
			close(pstmt);	
		}
		return boardList;
	}

	/**2.추천수 정렬에 사용하는 메소드 카테고리 값이 있으면 카테고리도 함께 저장
	 * 없으면 추천수 정렬만 저장*/
	public ArrayList<BoardBean> getLikeList(BoardBean category) {	
		String sql1 = "SELECT * FROM BOARD WHERE BOARD_CATEGORY=? ORDER BY BOARD_LIKECOUNT DESC"; //카테고리 값을 가진 보드 테이블을 추천 순서로 조회
		String sql2 = "SELECT * FROM BOARD ORDER BY BOARD_LIKECOUNT DESC";    //보드 테이블을 추천 순서로 조회
		ArrayList<BoardBean> boardList =new ArrayList<BoardBean>();			
				try {
					if(category.getBOARD_CATEGORY().equals("")) {
						pstmt = con.prepareStatement(sql2); //조회만함
						System.out.println("test11");
					}else {
						pstmt = con.prepareStatement(sql1);
						System.out.println(category.getBOARD_CATEGORY());//보드테이블에 카테고리값을 넘김
						pstmt.setString(1, category.getBOARD_CATEGORY());
						}
					rs = pstmt.executeQuery();
					while(rs.next()) {
						BoardBean boardBean = new BoardBean();
						boardBean.setMEMBER_ID(rs.getString(1));
						boardBean.setBOARD_NUM(rs.getInt(2));
						boardBean.setBOARD_SUBJECT(rs.getString(3));
						boardBean.setBOARD_DATE(rs.getDate(4));
						boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
						boardBean.setBOARD_VIDEO_URL(rs.getString(6));
						boardBean.setBOARD_READCOUNT(rs.getInt(7));
						boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
						boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
						boardBean.setBOARD_TAG(rs.getString(10));
						boardBean.setBOARD_CATEGORY(rs.getString(11));
						boardBean.setBOARD_BLIND(rs.getInt(12));
						boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
						boardList.add(boardBean);						
					}		
			} catch(Exception e) {
				System.out.println("likelist 오류"+e);
			} finally {
				close(rs);
				close(pstmt);
			}
			return boardList;
	}
	/**3.조회수 정렬에 사용하는 메소드 카테고리 값이 있으면 카테고리도 함께 저장
	 * 없으면 조회수 정렬만 저장*/
	public ArrayList<BoardBean> getReadList(BoardBean category) {	
		String sql1 = "SELECT * FROM BOARD WHERE BOARD_CATEGORY=? ORDER BY BOARD_READCOUNT DESC"; //카테고리 값을 가진 보드 테이블을 조회 순서로 조회
		String sql2 = "SELECT * FROM BOARD ORDER BY BOARD_READCOUNT DESC";//보드 테이블을 조회 순서로 조회
		ArrayList<BoardBean> boardList =new ArrayList<BoardBean>();			
			try {
				if(category.getBOARD_CATEGORY().equals("")) {
					pstmt = con.prepareStatement(sql2); //조회만함
					System.out.println("test11");
				}else {
					pstmt = con.prepareStatement(sql1);
					System.out.println(category.getBOARD_CATEGORY());//보드테이블에 카테고리값을 넘김
					pstmt.setString(1, category.getBOARD_CATEGORY());
					}
				rs = pstmt.executeQuery();					
				while(rs.next()) {
					BoardBean boardBean = new BoardBean();
					boardBean.setMEMBER_ID(rs.getString(1));
					boardBean.setBOARD_NUM(rs.getInt(2));
					boardBean.setBOARD_SUBJECT(rs.getString(3));
					boardBean.setBOARD_DATE(rs.getDate(4));
					boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
					boardBean.setBOARD_VIDEO_URL(rs.getString(6));
					boardBean.setBOARD_READCOUNT(rs.getInt(7));
					boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
					boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
					boardBean.setBOARD_TAG(rs.getString(10));
					boardBean.setBOARD_CATEGORY(rs.getString(11));
					boardBean.setBOARD_BLIND(rs.getInt(12));
					boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
					boardList.add(boardBean);					
				}			
		} catch(Exception e) {
			System.out.println("readlist 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	/** 4.댓글을 등록하는 메소드 댓글번호를 조회해서 가장큰 번호에서 +1을 해서 저장*/
	public int commentRegister(CommentBean commentBean) {
		int commentResult=0;
		int num = 0;
		String sql1 = "SELECT MAX(COMMENT_NUM) FROM BOARD_COMMENT"; // 댓글 테이블 조회 
		String sql2 = "INSERT INTO BOARD_COMMENT VALUES(?,?,?,?,SYSDATE,?)"; // 댓글 테이블에 행 삽입
		try {
			//댓글번호를 1씩 증가시키기위한 쿼리문
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1; // 이미 등록된 댓글의 번호를 1씩 증가 시킴
			}else {
				num=1;
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, commentBean.getBOARD_NUM());  // 받아온 값을 각각의 컬럼에 입력
			pstmt.setString(2, commentBean.getMEMBER_ID());
			pstmt.setInt(3, num);
			pstmt.setString(4, commentBean.getCOMMENT_CON());
			pstmt.setInt(5, 0);
			commentResult=pstmt.executeUpdate();
	}catch(Exception e){
		System.out.println("댓글 달기 오류 !! : "+e);
	}finally {
		close(pstmt);
		close(rs);
	}
	return commentResult;
}
	/**
	 * 5. 댓글목록을 보여주기위한 메소드 */
	public ArrayList<CommentBean> getCommentlist() {
		String sql = "SELECT * FROM BOARD_COMMENT";
		ArrayList<CommentBean> commentList =new ArrayList<CommentBean>();			
		CommentBean commentBean = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();					
			while(rs.next()) {
				commentBean = new CommentBean();
				commentBean.setBOARD_NUM(rs.getInt(1));
				commentBean.setMEMBER_ID(rs.getString(2));
				commentBean.setCOMMENT_NUM(rs.getInt(3));
				commentBean.setCOMMENT_CON(rs.getString(4));
				commentBean.setCOMMENT_DATE(rs.getDate(5));
				commentBean.setCOMMENT_BLIND(rs.getInt(6));
				commentList.add(commentBean);
				}
			}catch(Exception e){
				System.out.println("댓글 목록 오류 !! : "+e);
			}finally {
				close(pstmt);
				close(rs);
			}
		return commentList;
	}
	/** 6.카테고리로 보드를 조회해서 저장한후 카테고리별로 목록을 볼수 있게 해주는 메소드*/
	public ArrayList<BoardBean> getCategory(BoardBean category) {	
		String sql = "SELECT * FROM BOARD WHERE BOARD_CATEGORY=?"; //해당 카테고리의 보드 테이블 조회
		ArrayList<BoardBean> boardList =new ArrayList<BoardBean>();
				try {
					pstmt = con.prepareStatement(sql);
					System.out.println(category.getBOARD_CATEGORY()); //객체에서 받아온 카테고리값 저장
					pstmt.setString(1, category.getBOARD_CATEGORY());
					rs = pstmt.executeQuery();				
					while(rs.next()) {
						BoardBean boardBean = new BoardBean();
						boardBean.setMEMBER_ID(rs.getString(1));
						boardBean.setBOARD_NUM(rs.getInt(2));
						boardBean.setBOARD_SUBJECT(rs.getString(3));
						boardBean.setBOARD_DATE(rs.getDate(4));
						boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
						boardBean.setBOARD_VIDEO_URL(rs.getString(6));
						boardBean.setBOARD_READCOUNT(rs.getInt(7));
						boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
						boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
						boardBean.setBOARD_TAG(rs.getString(10));
						boardBean.setBOARD_CATEGORY(rs.getString(11));
						boardBean.setBOARD_BLIND(rs.getInt(12));
						boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
						boardList.add(boardBean);					
					}			
			} catch(Exception e) {
				System.out.println("categorylist 오류"+e);
			} finally {
				close(rs);
				close(pstmt);
			}
				return boardList;
		}
	/**7.신고가 추천테이블이 생성되는 메소드 신고번호를 조회해서 가장큰 번호에서 +1을 해서 저장.*/
	public int reportAdd (ReportBean reportBean) {
		int reportResult = 0;
		int num = 0;
		String sql1 = "SELECT MAX(REPORT_NUM) FROM REPORT"; // 신고 테이블 조회
		String sql2 = "INSERT INTO REPORT VALUES(?,?,?,SYSDATE)"; //신고테이블에 행추가
		try {
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {               //기존 신고번호의 값을 1씩 증가
				num = rs.getInt(1)+1;
			}else {
				num=1;
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2, reportBean.getMEMBER_ID()); //객체로부터 받아온값 저장
			pstmt.setInt(3, reportBean.getBOARD_NUM());
			reportResult = pstmt.executeUpdate();
			System.out.println("신고>>>");
		}catch(Exception e) {
			System.out.println("reportAdd 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return reportResult;
	}
	/**8.신고가 저장되면 보드의 신고수를 올려준다*/
	public int reportUpdate (ReportBean reportBean) {
		int updateResult = 0;
		String sql = "UPDATE BOARD SET BOARD_REPORTCOUNT=(BOARD_REPORTCOUNT+1) WHERE BOARD_NUM=?"; //보드테이블의 신고 카운트 번호를 해당게시글만 증가
		try {
			pstmt = con.prepareStatement(sql);              //reportAdd에서  신고를 받아  보드테이블의 신고 카운트를 증가시킴
			pstmt.setInt(1, reportBean.getBOARD_NUM());
			updateResult = pstmt.executeUpdate();
			System.out.println(">>>완료");
		}catch(Exception e) {
			System.out.println("reportUpdate 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateResult;
	}
	/** 9.신고테이블을 조회해서 저장한후 아이디와 글번호 비교해서 같은게시물에 같은아이디로 신고를 한번만 할 수 있게 해주는 메소드*/
	public  ArrayList<ReportBean> getReportList(){
		String sql = "SELECT * FROM REPORT";
		ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReportBean report = new ReportBean();
				report.setREPORT_NUM(rs.getInt(1));
				report.setMEMBER_ID(rs.getString(2));
				report.setBOARD_NUM(rs.getInt(3));
				report.setREPORT_DATE(rs.getDate(4));
				reportList.add(report);
			}
		}catch(Exception e) {
			System.out.println("reportList 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return reportList;
		
	}
	/**10.추천을 누르면 추천테이블이 생성되는 메소드. 추천번호를 조회해서 가장큰 번호에서 +1을 해서 저장 */
	public int likeAdd (LikeBean likeBean) {
		int likeResult = 0;
		int num = 0;
		String sql1 = "SELECT MAX(LIKE_NUM) FROM LIKED";
		String sql2 = "INSERT INTO LIKED VALUES(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num=1;
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2, likeBean.getMEMBER_ID());
			pstmt.setInt(3, likeBean.getBOARD_NUM());
			likeResult = pstmt.executeUpdate();
			System.out.println("추천>>>");
		}catch(Exception e) {
			System.out.println("likeAdd 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return likeResult;
	}
	/**11.추천테이블이 만들어지면 보드테이블의 추천수가 증가함.*/
	public int likeUpdate (LikeBean likeBean) {
		int updateResult = 0;
		String sql = "UPDATE BOARD SET BOARD_LIKECOUNT=(BOARD_LIKECOUNT+1) WHERE BOARD_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, likeBean.getBOARD_NUM());
			updateResult = pstmt.executeUpdate();
			System.out.println(">>>완료");
		}catch(Exception e) {
			System.out.println("likeUpdate 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateResult;
	}
	/**12. 게시물을 삭제하는 메소드 게시번호를 찾아서 삭제*/
	public int boardDelete(BoardBean boardBean) {

		String sql = "DELETE BOARD WHERE BOARD_NUM=?";
		System.out.println(boardBean.getBOARD_NUM());
		int deleteReuslt = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardBean.getBOARD_NUM());
			deleteReuslt = pstmt.executeUpdate();
			System.out.println("dao 삭제완료");
		}catch(Exception e){
			System.out.println("삭제 오류"+e);
		}finally {
			close(pstmt);
		}
		return deleteReuslt;
	}
	/** 13.태그나 제목으로 like를 이용해 검색어를 포함하는 게시물을 검색하고 결과물을 저장한다.*/
	public ArrayList<BoardBean> listSearch(BoardBean search) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_SUBJECT LIKE ? OR BOARD_TAG LIKE ?";
		ArrayList<BoardBean> boardList =new ArrayList<BoardBean>();
		try { 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getBOARD_SUBJECT()+"%");
			pstmt.setString(2, "%"+search.getBOARD_TAG()+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardBean boardBean = new BoardBean();
				boardBean.setMEMBER_ID(rs.getString(1));
				boardBean.setBOARD_NUM(rs.getInt(2));
				boardBean.setBOARD_SUBJECT(rs.getString(3));
				boardBean.setBOARD_DATE(rs.getDate(4));
				boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
				boardBean.setBOARD_VIDEO_URL(rs.getString(6));
				boardBean.setBOARD_READCOUNT(rs.getInt(7));
				boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
				boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
				boardBean.setBOARD_TAG(rs.getString(10));
				boardBean.setBOARD_CATEGORY(rs.getString(11));
				boardBean.setBOARD_BLIND(rs.getInt(12));
				boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
				boardList.add(boardBean);				
			}
		}catch(Exception e){
			System.out.println("검색 오류"+e);
		}finally {
			close(pstmt);
		}
		return boardList;
	}
	
	public ArrayList<BoardBean> getTopReadcountList() {
		String sql = "SELECT * FROM (SELECT * FROM BOARD ORDER BY BOARD_READCOUNT DESC)"
				+ " WHERE ROWNUM >=1 AND ROWNUM <= 3";
		
		ArrayList<BoardBean> topReadcountList = new ArrayList<BoardBean>();
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("쿼리문 준비");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("쿼리문 완료");
				BoardBean boardBean = new BoardBean();
				boardBean.setMEMBER_ID(rs.getString(1));
				boardBean.setBOARD_NUM(rs.getInt(2));
				boardBean.setBOARD_SUBJECT(rs.getString(3));
				boardBean.setBOARD_DATE(rs.getDate(4));
				boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
				boardBean.setBOARD_VIDEO_URL(rs.getString(6));
				boardBean.setBOARD_READCOUNT(rs.getInt(7));
				boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
				boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
				boardBean.setBOARD_TAG(rs.getString(10));
				boardBean.setBOARD_CATEGORY(rs.getString(11));
				boardBean.setBOARD_BLIND(rs.getInt(12));
				boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
				topReadcountList.add(boardBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return topReadcountList; // 데이터베이스 오류
	}

	public ArrayList<BoardBean> getTopLikeList() {
		String sql = "SELECT * FROM (SELECT * FROM BOARD ORDER BY BOARD_LIKECOUNT DESC)"
				+ " WHERE ROWNUM >=1 AND ROWNUM <= 3";
		
		ArrayList<BoardBean> topLikeList = new ArrayList<BoardBean>();
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("쿼리문 준비");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean boardBean = new BoardBean();
				boardBean.setMEMBER_ID(rs.getString(1));
				boardBean.setBOARD_NUM(rs.getInt(2));
				boardBean.setBOARD_SUBJECT(rs.getString(3));
				boardBean.setBOARD_DATE(rs.getDate(4));
				boardBean.setBOARD_VIDEO_FILE(rs.getString(5));
				boardBean.setBOARD_VIDEO_URL(rs.getString(6));
				boardBean.setBOARD_READCOUNT(rs.getInt(7));
				boardBean.setBOARD_LIKECOUNT(rs.getInt(8));
				boardBean.setBOARD_REPORTCOUNT(rs.getInt(9));
				boardBean.setBOARD_TAG(rs.getString(10));
				boardBean.setBOARD_CATEGORY(rs.getString(11));
				boardBean.setBOARD_BLIND(rs.getInt(12));
				boardBean.setBOARD_YOUTUBE_ID(rs.getString(13));
				topLikeList.add(boardBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return topLikeList; // 데이터베이스 오류
	}
}
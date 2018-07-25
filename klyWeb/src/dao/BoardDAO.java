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
	
	public ArrayList<BoardBean> getboardlist() {	
		String sql = "SELECT * FROM BOARD";
		ArrayList<BoardBean> boardlist =new ArrayList<BoardBean>();			
		BoardBean Boardbean = null;
		try {
			pstmt = con.prepareStatement(sql);
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
				boardlist.add(Boardbean);				
			} while(rs.next()) ;			
		}catch(Exception e) {
			System.out.println("list 오류"+e);
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return boardlist;
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
	
	public ArrayList<BoardBean> getLikeList() {	
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_LIKECOUNT DESC";
		ArrayList<BoardBean> boardlist =new ArrayList<BoardBean>();			
				BoardBean Boardbean = null;
				try {
					pstmt = con.prepareStatement(sql);
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
						boardlist.add(Boardbean);				
					}while(rs.next()) ;			
			} catch(Exception e) {
				System.out.println("list 오류"+e);
			} finally {
				close(rs);
				close(pstmt);
			}
				return boardlist;
		}
	public ArrayList<BoardBean> getReadList() {	
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_READCOUNT DESC";
		ArrayList<BoardBean> boardlist =new ArrayList<BoardBean>();			
			BoardBean Boardbean = null;
			try {
				pstmt = con.prepareStatement(sql);
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
					boardlist.add(Boardbean);				
				}while(rs.next()) ;			
		} catch(Exception e) {
			System.out.println("list 오류"+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardlist;
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

	}/*
	//전체 글 갯수 가져오기 메소스
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
	
	

	public ArrayList<boardBean> getBoardList(int page, int limit) {
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC";
		//페이징 처리를 위한 쿼리문
		String sql = "SELECT * FROM (SELECT ROWNUM RN2, V1. * FROM (SELECT * FROM BOARD ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC) V1) V2 WHERE V2.RN2 BETWEEN ? AND ?";
		
		int startrow = (page-1)*limit+1;
		int endrow = page*limit;
		
		ArrayList<boardBean> boardList = 
				new ArrayList<boardBean>();
		boardBean BoardBean = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				BoardBean = new boardBean();
				BoardBean.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				BoardBean.setBOARD_NAME(rs.getString("BOARD_NAME"));
				BoardBean.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				BoardBean.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				BoardBean.setBOARD_FILE(rs.getString("BOARD_FILE"));
				BoardBean.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				BoardBean.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				BoardBean.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				BoardBean.setBOARD_COUNT(rs.getInt("BOARD_READCOUNT"));
				BoardBean.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				boardList.add(BoardBean);
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

	public boardBean BoardView(int me) {
		System.out.println("VIEWDAO");
		String sql = "SELECT * FROM BOARD WHERE BOARD_NUM = ?";
		boardBean sub = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, me);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sub = new boardBean();
				sub.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				sub.setBOARD_NAME(rs.getString("BOARD_NAME"));
				sub.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				sub.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				sub.setBOARD_FILE(rs.getString("BOARD_FILE"));
				sub.setBOARD_COUNT(rs.getInt("BOARD_READCOUNT"));
				sub.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			System.out.println(sub.getBOARD_CONTENT());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			}
		return sub;
	}

	public int updateCount(int me) {
		int updateCount = 0;
		String sql1 = "UPDATE BOARD SET BOARD_READCOUNT=(BOARD_READCOUNT+1) WHERE BOARD_NUM=?";
		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, me);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public boolean BoardInsert(int my, String pw) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_NUM=?";
		boolean bb = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, my);
			rs = pstmt.executeQuery();
			rs.next();
			if(pw.equals(rs.getString("BOARD_PASS"))) {
				bb = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return bb;
	}
	//글 수정 메소드
	public int modify(boardBean bb) {
		System.out.println("modifyDAO");
		int upDateCount = 0;
		String sql = "UPDATE BOARD SET BOARD_NAME=?, BOARD_SUBJECT=?, BOARD_CONTENT=? WHERE BOARD_NUM=?";
		try {
			System.out.println("modifyDAO안의 try");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb.getBOARD_NAME());
			pstmt.setString(2, bb.getBOARD_SUBJECT());
			pstmt.setString(3, bb.getBOARD_CONTENT());
			pstmt.setInt(4, bb.getBOARD_NUM());
			upDateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return upDateCount;
	}

	public int deleteBoard(int delNum) {
		String sql = "DELETE BOARD WHERE BOARD_NUM=?";
		int deleteResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, delNum);
			deleteResult = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("deleteMember 오류"+e);
		}finally {
			close(pstmt);
		}
		
		return deleteResult;
	}

	public int comment(boardBean boardBean) {
		
		 * 1. BOARD_NUM을 위한 MAX(BOARD_NUM)을 얻어오기
		 * 2. BOARD_RE_SEQ을 재조정
		 * 3. 답글등록
		 
		System.out.println("코맨트 DAO");
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC";
		String sql = "SELECT MAX(BOARD_NUM) FROM BOARD";
		//답글을 달았을떄 정렬해주기위해
		String sql1 = "UPDATE BOARD SET BOARD_RE_SEQ = BOARD_RE_SEQ+1 WHERE BOARD_RE_REF=? AND BOARD_RE_SEQ>?";
		String sql2 = "INSERT INTO BOARD VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE)";
		
		int num = 0;
		int insertCount = 0;
		int re_ref = boardBean.getBOARD_RE_REF();
		int re_lev = boardBean.getBOARD_RE_LEV();
		int re_seq = boardBean.getBOARD_RE_SEQ();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num=1;
			}
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			int updateCount = pstmt.executeUpdate();
			if(updateCount > 0) {
				commit(con);
			}
			
			re_seq = re_seq+1;
			re_lev = re_lev+1;
			
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2, boardBean.getBOARD_NAME());
			pstmt.setString(3, boardBean.getBOARD_PASS());
			pstmt.setString(4, boardBean.getBOARD_SUBJECT());
			pstmt.setString(5, boardBean.getBOARD_CONTENT());
			pstmt.setString(6, "");
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("답글 작성 오류"+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}
*/
	
}







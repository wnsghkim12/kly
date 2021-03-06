package dao;

import static db.JDBCUtil.*;

import java.sql.*;
import java.util.ArrayList;

import bean.CommentBean;
import bean.LikeBean;
import bean.MemberBean;

public class CommentDAO {
	// singleton
	private static CommentDAO memberDAO;
	private CommentDAO() {
		
	}
	
	// MemberDAO 인스턴스 생성 메소드
	public static CommentDAO getInstance() {
		if(memberDAO==null) {
			memberDAO = new CommentDAO();
		}
		
		return memberDAO;
	}
	
	// db 설정용 필드
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// db 연결 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<CommentBean> getCommentList() {
		return null;
	}
	
	// 해당 아이디의 댓글 전체 가져오기
	public CommentBean getMemberComment(String memberID) {
		String sql = "SELECT * FROM COMMENT WHERE MEMBER_ID = ?";
		
		CommentBean comment = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comment = new CommentBean();
				comment.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				comment.setMEMBER_ID(rs.getString("MEMBER_ID"));
				comment.setCOMMENT_NUM(rs.getInt("COMMENT_NUM"));
				comment.setCOMMENT_CON(rs.getString("COMMENT_CON"));
				comment.setCOMMENT_DATE(rs.getDate("COMMENT_DATE"));
				comment.setCOMMENT_BLIND(rs.getInt("COMMENT_BLIND"));
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
		return comment; // 데이터베이스 오류
	}

	public ArrayList<CommentBean> getSuependCommentList() {
		String sql = "SELECT * FROM REPLY WHERE COMMENT_BLIND > 3";
		
		ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				comment.setMEMBER_ID(rs.getString("MEMBER_ID"));
				comment.setCOMMENT_NUM(rs.getInt("COMMENT_NUM"));
				comment.setCOMMENT_CON(rs.getString("COMMENT_CON"));
				comment.setCOMMENT_DATE(rs.getDate("COMMENT_DATE"));
				comment.setCOMMENT_BLIND(rs.getInt("COMMENT_BLIND"));
				commentList.add(comment);
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
		return commentList;
	}

	public LikeBean getMemberLike(String memberID) {
		// TODO Auto-generated method stub
		return null;
	}


}

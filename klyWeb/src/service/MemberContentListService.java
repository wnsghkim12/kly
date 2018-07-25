package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.BoardBean;
import bean.CommentBean;
import bean.LikeBean;
import bean.MemberBean;
import dao.CommentDAO;
import dao.MemberDAO;

public class MemberContentListService {

	/** 멤버 한명의 댓글 가져오기 */
	public BoardBean getMemberArticleList(String memberID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		BoardBean ariticle = memberDAO.getMemberArticle(memberID);
			
		close(con);
		return ariticle;
	}
	
	/** 멤버 한명의 게시글 가져오기 */
	public CommentBean getMemberCommentList(String memberID) {
		CommentDAO commentDAO = CommentDAO.getInstance();
		Connection con = getConnection();
		commentDAO.setConnection(con);

		CommentBean comment = commentDAO.getMemberComment(memberID);
		close(con);
		
		return comment;
	}

	public LikeBean getMemberLikedList(String memberID) {
		CommentDAO commentDAO = CommentDAO.getInstance();
		Connection con = getConnection();
		commentDAO.setConnection(con);

		LikeBean likeArticle = commentDAO.getMemberLike(memberID);
		close(con);
		
		return likeArticle;
	}

}

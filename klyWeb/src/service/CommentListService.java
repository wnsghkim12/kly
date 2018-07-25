package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.CommentBean;
import dao.CommentDAO;

public class CommentListService {
	
	public ArrayList<CommentBean> getCommentList(){
		CommentDAO commentDAO = CommentDAO.getInstance();
		Connection con = getConnection();
		commentDAO.setConnection(con);

		ArrayList<CommentBean> commentList = commentDAO.getCommentList();
		close(con);
		
		return commentList;
	}
	

	public ArrayList<CommentBean> getSuspendCommentList() {
		CommentDAO commentDAO = CommentDAO.getInstance();
		Connection con = getConnection();
		commentDAO.setConnection(con);

		ArrayList<CommentBean> suspendCommentList = commentDAO.getSuependCommentList();
		close(con);
		
		return suspendCommentList;
	}
}

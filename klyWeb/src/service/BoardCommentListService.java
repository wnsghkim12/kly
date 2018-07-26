package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import bean.CommentBean;
import dao.BoardDAO;

public class BoardCommentListService {
	public ArrayList<CommentBean> BoardCommentListService(){
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		ArrayList<CommentBean> commentList = boardDAO.getCommentlist();
		close(con);
		return commentList;
	}
}

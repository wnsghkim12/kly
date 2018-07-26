package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import bean.CommentBean;
import dao.BoardDAO;

public class BoardCommentService {
	public boolean BoardCommentService(CommentBean commentBean){
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
			
		boolean CommentResult = false;
		
		int result = boardDAO.commentRegister(commentBean);
		System.out.println("boardComment서비스");
	
		if(result > 0) { 
			CommentResult = true;
			commit(con);
		 }else {
			CommentResult = false;
			rollback(con);
		}
		close(con);
		return CommentResult;
	}
}

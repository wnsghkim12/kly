package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import bean.BoardBean;
import dao.BoardDAO;
import dao.MemberDAO;

public class BoardWriteService {

	public boolean BoardService(BoardBean boardBean){
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
			
		boolean boardResult = false;
		
		int result = boardDAO.registArticle(boardBean);
		System.out.println("board서비스부분");
	
		if(result > 0) { 
			boardResult = true;
			commit(con);
		 }else {
			boardResult = false;
			rollback(con);
		}
		close(con);
		return boardResult;
	}
}

package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import bean.BoardBean;

public class BoardSuspendListService {

	/*public ArrayList<BoardBean> getboardSuspendList(int limit) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		System.out.println("55");
		ArrayList<BoardBean> boardSuspendList = 
				boardDAO.getboardSuspendList(limit);
		close(con);
		System.out.println("66");
		return boardSuspendList;
	}*/

	public int getListCount() {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		int listCount = 0;
		listCount = boardDAO.getListCount();
		close(con);
		return listCount;
	}

	public ArrayList<BoardBean> getBoardList(int page, int limit) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> boardList = 
				boardDAO.getBoardList(page, limit);
		close(con);
		
		return boardList;
	}

}

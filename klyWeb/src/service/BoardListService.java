package service;

import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.close;


import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardListService {
	public ArrayList<BoardBean> getboardList() {
			
			Connection con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			
			ArrayList<BoardBean> boardlist = boardDAO.getboardlist();
			close(con);
			return boardlist;
		}
	
	public ArrayList<BoardBean> getReadlist() {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> boardlist = boardDAO.getReadList();
		close(con);
		return boardlist;
	}
	public ArrayList<BoardBean> getLikelist() {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		ArrayList<BoardBean> boardlist = boardDAO.getLikeList();
		close(con);
		return boardlist;
	}
	
	public ArrayList<BoardBean> getCategory() {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		ArrayList<BoardBean> boardList = boardDAO.getCategory();
		close(con);
		return boardList;
	}

}

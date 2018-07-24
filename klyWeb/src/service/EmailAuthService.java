package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class EmailAuthService {

	public boolean getUserEmailChecked(String memberID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean result = memberDAO.getUserEmailChecked(memberID);

		close(con);
		return result;
	}

	public String getUserEmail(String memberID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		String userEmail = memberDAO.getUserEmail(memberID);

		close(con);
		return userEmail;
	}

}

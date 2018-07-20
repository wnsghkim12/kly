package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class MemberLoginService {

	public MemberBean loginMember(MemberBean mb) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		MemberBean loginInfo = memberDAO.loginMember(mb);

		close(con);
		
		return loginInfo;
	}

}

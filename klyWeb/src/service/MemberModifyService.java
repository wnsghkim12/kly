package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class MemberModifyService {

	public boolean modifyMember(MemberBean mb, String currentPassword, String changePassword) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		boolean modifyResult = false;
		
		int result = memberDAO.modifyMember(mb, currentPassword, changePassword);
		
		System.out.println(":: modiyfyService ::");
		System.out.println("modifyMember result : "+result);
		System.out.println();
		
		if(result != 0) {
			modifyResult = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return modifyResult;
	}
}

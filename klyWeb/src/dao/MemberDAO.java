package dao;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;

public class MemberDAO {
	// singleton
	private static MemberDAO memberDAO;
	private MemberDAO() {
		
	}
	
	// MemberDAO 인스턴스 생성 메소드
	public static MemberDAO getInstance() {
		if(memberDAO==null) {
			memberDAO = new MemberDAO();
		}
		
		return memberDAO;
	}
	
	// db 설정용 필드
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// db 연결 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int joinMember(MemberBean mb) {
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_EMAIL) VALUES(?,?,?)";
		int result = 0;
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			pstmt.setString(2, mb.getMEMBER_PW());
			pstmt.setString(3, mb.getMEMBER_EMAIL());
			
			result = pstmt.executeUpdate();
						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}

	public MemberBean loginMember(MemberBean mb) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("MEMBER_PW").equals(mb.getMEMBER_PW())) {
					System.out.println("로그인 성공");
					mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
					mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
					mb.setMEMBER_EMAIL(rs.getString("MEMBER_ID"));
					// mb.setMEMBER_CHECKED((rs.getCharacterStream("MEMBER_CHECKED"));
					mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
				}
			} else {
				mb = null;
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return mb;
	}

}

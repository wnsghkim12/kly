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
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_EMAIL, MEMBER_DATE) VALUES(?,?,?, SYSDATE)";
		int result = 0;
		
		System.out.println(":: MemberDAO ::");
		System.out.println("id :" + mb.getMEMBER_ID());
		System.out.println("pass :" + mb.getMEMBER_PW());
		System.out.println("email :" + mb.getMEMBER_EMAIL());
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			pstmt.setString(2, mb.getMEMBER_PW());
			pstmt.setString(3, mb.getMEMBER_EMAIL());
			
			result = pstmt.executeUpdate();
						
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO에서의 에러 메세지"+e.getMessage());
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

	public int dropMember(MemberBean mb, String inputPassword) {
		String confirm = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(confirm);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();

			// 해당 아이디가 존재
			if(rs.next()) {
				System.out.println("원래의 비밀번호는 ?"+rs.getString(1));
				System.out.println("원래의 비밀번호는 ?"+rs.getString("MEMBER_PW"));
				System.out.println("입력한 비밀번호는 ?"+inputPassword);
				// 입력 비밀번호가 일치
				if(rs.getString(1).equals(inputPassword)) {
					System.out.println("일단 true로 삭제 시도 까진 온다.");
					System.out.println("삭제할 아이디는 : "+mb.getMEMBER_ID());
					
					pstmt = con.prepareStatement(sql);
					System.out.println("pstmt = con.prepareStatement(sql); 시도");
					pstmt.setString(1, mb.getMEMBER_ID());
					System.out.println("pstmt.setString(1, mb.getMEMBER_ID());");
					
					pstmt.executeUpdate();
					System.out.println("result = pstmt.executeUpdate();");
					System.out.println(":: 삭제 결과");
					System.out.println("result : "+result);
				}
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
		return result;
	}

}

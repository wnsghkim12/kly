package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberLoginService;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//request 객체의 정보 가져오기(가입정보)
		String id = request.getParameter("loginId");
		String pwd = request.getParameter("loginPwd");
		
		System.out.println("-- MemberJoinAction");
		System.out.println("id :"+id);
		System.out.println("pwd :"+pwd);
		System.out.println();
		
		// 가입정보를 하나의 객체로 합침 
		MemberBean mb = new MemberBean();
		
		mb.setMEMBER_ID(id);
		mb.setMEMBER_PW(pwd);

		MemberLoginService mls = new MemberLoginService();
		
		MemberBean loginInfo = mls.loginMember(mb);
		
		ActionForward af = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		
		if (loginInfo == null) {
			out.println("<script>");
			out.println("alert('로그인에 실패했습니다.');");
			out.println("location.href='./index.jsp';");
			out.println("</script>");
			out.close();
		} else if (loginInfo.getMEMBER_SETTEMP() == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			af = new ActionForward();
			af.setPath("./changeFromTemp.jsp");
			af.setRedirect(true);
		} else if(loginInfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			af = new ActionForward();
			// 성공 했으면 다시 메인 페이지로
			af.setPath("./index.jsp");
		}
		return af;
	}

}


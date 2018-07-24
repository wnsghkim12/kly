package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//session 객체의 정보 가져오기(가입정보)
		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean) session.getAttribute("loginInfo");
		
		ActionForward af = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		if(mb==null) {
			out.println("<script>");
			out.println("</script>");
		} else if(mb!=null) {
			af = new ActionForward();
			af.setPath("./myPage.jsp");
		}
		
		return af;
	}

}


package ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentBean;

public class BoardCommentAjax implements Ajax{

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String comment = request.getParameter("comment"); 
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String member_id = request.getParameter("member_id");  //받아온 값 저장
		CommentBean commentBean = new CommentBean();
		PrintWriter out = response.getWriter();
		if(member_id.equals("")) {
			out.println("<script>");
			out.println("alert('로그인을 해주세요.');"); // 비로그인시 
			out.println("location.href='./login.jsp';");	
			out.println("</script>");
		}
		commentBean.setBOARD_NUM(board_num); 
		commentBean.setCOMMENT_CON(comment);
		commentBean.setMEMBER_ID(member_id);
		
		return null;
	}

}

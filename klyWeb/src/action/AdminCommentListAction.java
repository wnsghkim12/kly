package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.CommentBean;
import service.CommentListService;

public class AdminCommentListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String memberID = request.getParameter("memberID");
		
		CommentListService commentListService = new CommentListService();
		/*CommentBean memberCommentList = commentListService.getMemberCommentList(memberID);*/
		ArrayList<CommentBean> suspendCommentList = commentListService.getSuspendCommentList();
		
		ActionForward forward = null;
		if(suspendCommentList == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('board comment action 오류!!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			request.setAttribute("suspendCommentList", suspendCommentList);
			forward = new ActionForward();
			forward.setPath("./adminComment.jsp");
			System.out.println(suspendCommentList.get(1).getCOMMENT_CON()+" 댓글 리스트 출력 완료");
		}
		return forward;
		
	}

}

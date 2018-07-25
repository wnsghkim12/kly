package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardWriteService;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardBean boardBean = new BoardBean();
		
		//boardBean.setMEMBER_ID(request.getParameter("ID"));
		boardBean.setMEMBER_ID("tempId");
		boardBean.setBOARD_SUBJECT(request.getParameter("subject"));
		boardBean.setBOARD_VIDEO_URL(request.getParameter("videoURL"));
		boardBean.setBOARD_TAG(request.getParameter("tag"));
		boardBean.setBOARD_CATEGORY(request.getParameter("category"));
		
		boolean boardResult = false;
		
		BoardWriteService boardWriteService = new BoardWriteService();
		boardResult = boardWriteService.boardWrite(boardBean);
		
		System.out.println("board액션부분\n");
		
		ActionForward forward = null;
		if(boardResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('board action 오류!!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./index.jsp"); // 리스트 페이지로 이동해야
		}
		return forward;
	}

}

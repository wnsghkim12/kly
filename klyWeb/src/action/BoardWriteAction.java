package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.ActionForward;
import bean.BoardBean;
import bean.MemberBean;
import service.BoardWriteService;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String realPath = "";
		String savePath = "/videoUpload";
		
		int size = 1*1024*1024*1024;	//최대 용량 1GB
		ServletContext context = request.getServletContext();
		realPath = context.getRealPath(savePath);
		System.out.println("3");
		/*MultipartRequest multi = new MultipartRequest(
				request,
				realPath,
				size,
				"UTF-8",
				new DefaultFileRenamePolicy());*/
		
		BoardBean boardBean = new BoardBean();
		MemberBean memberBean = new MemberBean();
		
//		memberBean.setMEMBER_ID(request.getParameter("ID"));
		boardBean.setBOARD_CATEGORY(request.getParameter("category"));
		boardBean.setBOARD_SUBJECT(request.getParameter("subject"));
		/*boardBean.setBOARD_VIDEO_FILE(multi.getParameter("select_video"));
		boardBean.setBOARD_VIDEO_URL(multi.getParameter("select_video"));*/
		boardBean.setBOARD_TAG(request.getParameter("tag"));
		
		boolean boardResult = false;
		BoardWriteService boardWriteService = new BoardWriteService();
		boardResult = boardWriteService.BoardService(boardBean);
		System.out.println("board액션부분\n");
		ActionForward forward = null;
		if(boardResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('board action 오류!!')");
			out.println("history.back();");
			out.println("</ script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.jsp");
		}
		return forward;
	}

}

package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.CommentBean;
import service.BoardCommentService;

public class BoardCommentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		commentBean.setMEMBER_ID(member_id); //쿼리문 전송을 위한 세팅
		
		boolean CommentResult = false;
		BoardCommentService boardCommentService = new BoardCommentService();
		CommentResult = boardCommentService.BoardCommentService(commentBean);//  객체선언후 성공여부 판단을 위한 불린 선언
		
		
		System.out.println("boardComment액션부분\n");
		ActionForward actionForward = null;
		if(CommentResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>"); 
			out.println("alert('board comment action 오류!!');"); // 실패시 경고창과 함께 되돌아감
			out.println("history.back();");
			out.println("</script>");
		}else {
			actionForward = new ActionForward();
			actionForward.setRedirect(true); 
			actionForward.setPath("./List.jsp");
		}
		return actionForward;
		
	}

}

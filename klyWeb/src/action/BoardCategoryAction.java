package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.CommentBean;
import service.BoardCommentListService;
import service.BoardListService;

public class BoardCategoryAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		BoardBean boardBean = new BoardBean();
		String category = request.getParameter("category");
		boardBean.setBOARD_CATEGORY(category);
		
		System.out.println(boardBean.getBOARD_CATEGORY());
		BoardListService boardlistservice = new BoardListService();
		BoardCommentListService boardCommentListService = new BoardCommentListService();
		ArrayList<CommentBean> commentList = boardCommentListService.BoardCommentListService();
		ArrayList<BoardBean> CategoryList = boardlistservice.getCategory();
		ActionForward actionForward = null;
		request.setAttribute("boardlist", CategoryList);
		request.setAttribute("commentlist", commentList);
		actionForward = new ActionForward();
		actionForward.setPath("List.jsp");
		
		return actionForward;
	}
}

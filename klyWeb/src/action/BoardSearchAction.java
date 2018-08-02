package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.CommentBean;
import service.BoardCommentListService;
import service.BoardSearchService;

public class BoardSearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardBean search = new BoardBean();
		response.setContentType("text/html;charset=UTF-8");
		String searchValue = request.getParameter("search");
		search.setBOARD_SUBJECT(searchValue); // 쿼리문 전송 위한 검색값 저장
		search.setBOARD_TAG(searchValue);
		BoardSearchService boardSearchService = new BoardSearchService(); //객체선언
		ArrayList<BoardBean> searchList = boardSearchService.listSearch(search); //검색목록 객체배열선언 
		BoardCommentListService boardCommentListService = new BoardCommentListService(); //객체 선언
		ArrayList<CommentBean> commentList = boardCommentListService.BoardCommentListService(); //댓글목록 객체배열선언
		ActionForward actionForward = null;
		request.setAttribute("boardlist", searchList);
		request.setAttribute("commentlist", commentList);
		actionForward = new ActionForward();
		actionForward.setPath("List.jsp");
		return actionForward;
		
	}

}

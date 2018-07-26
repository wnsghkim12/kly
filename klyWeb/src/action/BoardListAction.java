package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.CommentBean;
import service.BoardCommentListService;
import service.BoardListService;


public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String category = request.getParameter("category");
		String array = request.getParameter("array");
		
		ActionForward actionForward = null;
		BoardListService boardlistservice = new BoardListService();
		BoardCommentListService boardCommentListService = new BoardCommentListService();
		ArrayList<CommentBean> commentList = boardCommentListService.BoardCommentListService();
		if(array!=null) {
			if(array.equals("추천")) {
				ArrayList<BoardBean> boardlist = boardlistservice.getLikelist();
				if(category!=null) {
					for(int i = 0; i<boardlist.size(); i++) {
						if(category.equals(boardlist.get(i).getBOARD_CATEGORY())) {
							request.setAttribute("boardlist", boardlist.get(i));
							request.setAttribute("commentlist", commentList);
							actionForward = new ActionForward();
							actionForward.setPath("List.jsp");
						}
					}
				}else {
					request.setAttribute("boardlist", boardlist);
					request.setAttribute("commentlist", commentList);
					actionForward = new ActionForward();
					actionForward.setPath("List.jsp");
				}
				return actionForward;
			}else{
				ArrayList<BoardBean> boardlist = boardlistservice.getReadlist();
				
				if(category!=null) {
					for(int i = 0; i<boardlist.size(); i++) {
						if(category.equals(boardlist.get(i).getBOARD_CATEGORY())) {
							request.setAttribute("boardlist", boardlist.get(i));
							request.setAttribute("commentlist", commentList);
							actionForward = new ActionForward();
							actionForward.setPath("List.jsp");
						}
					}
				}else {
					request.setAttribute("boardlist", boardlist);
					request.setAttribute("commentlist", commentList);
					actionForward = new ActionForward();
					actionForward.setPath("List.jsp");
				}
				return actionForward;
			}
		}else {
		ArrayList<BoardBean> boardlist = boardlistservice.getboardList();
		System.out.println(boardlist.size());
		if(category!=null) {
			for(int i = 0; i<boardlist.size(); i++) {
				if(category.equals(boardlist.get(i).getBOARD_CATEGORY())) {
					request.setAttribute("boardlist", boardlist.get(i));
					request.setAttribute("commentlist", commentList);
					actionForward = new ActionForward();
					actionForward.setPath("List.jsp");
					System.out.println(boardlist.size());
				}
			}
		}else {
			request.setAttribute("boardlist", boardlist);
			request.setAttribute("commentlist", commentList);
			actionForward = new ActionForward();
			actionForward.setPath("List.jsp");
		}
		return actionForward;
	}
	}
}

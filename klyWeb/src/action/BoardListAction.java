package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardListService;


public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String category = request.getParameter("category");
		String array = request.getParameter("array");
		
		ActionForward forward = null;
		BoardListService boardlistservice = new BoardListService();
		if(array!=null) {
			if(array.equals("추천")) {
				ArrayList<BoardBean> boardlist = boardlistservice.getLikelist();
				if(category!=null) {
					for(int i = 0; i<boardlist.size(); i++) {
						if(category.equals(boardlist.get(i).getBOARD_CATEGORY())) {
							request.setAttribute("boardlist", boardlist);
							forward = new ActionForward();
							forward.setPath("List.jsp");
						}
					}
				}else {
					request.setAttribute("boardlist", boardlist);
					forward = new ActionForward();
					forward.setPath("List.jsp");
				}
				return forward;
			}else{
				ArrayList<BoardBean> boardlist = boardlistservice.getReadlist();
				
				if(category!=null) {
					for(int i = 0; i<boardlist.size(); i++) {
						if(category.equals(boardlist.get(i).getBOARD_CATEGORY())) {
							request.setAttribute("boardlist", boardlist);
							forward = new ActionForward();
							forward.setPath("List.jsp");
						}
					}
				}else {
					request.setAttribute("boardlist", boardlist);
					forward = new ActionForward();
					forward.setPath("List.jsp");
				}
				return forward;
			}
		}else {
		ArrayList<BoardBean> boardlist = boardlistservice.getboardlist();
		
		if(category!=null) {
			for(int i = 0; i<boardlist.size(); i++) {
				if(category.equals(boardlist.get(i).getBOARD_CATEGORY())) {
					request.setAttribute("boardlist", boardlist);
					forward = new ActionForward();
					forward.setPath("List.jsp");
				}
			}
		}else {
			request.setAttribute("boardlist", boardlist);
			forward = new ActionForward();
			forward.setPath("List.jsp");
		}
		return forward;
	}
	}
}

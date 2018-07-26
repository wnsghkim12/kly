package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.PageInfo;
import service.BoardSuspendListService;

public class BoardSuspendListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ActionForward forward = null;
		BoardSuspendListService boardSuspendListService = new BoardSuspendListService();
		System.out.println("33");
		//한 페이지에 보여줄 글 갯수를 정하기 위해 사용하는 변수
		int limit = 20;
		
		//limit 값을 걸어놓은 만큼 범위에서 해당하는 글만 가져오는 방법
		ArrayList<BoardBean> boardSuspendList = boardSuspendListService.getboardSuspendList(limit);

		request.setAttribute("boardSuspendList", boardSuspendList);
		forward = new ActionForward();
		forward.setPath("boardSuspendList.kly");
		forward.setRedirect(true);
		return forward;*/
		
		
		ActionForward forward = null;
		BoardSuspendListService boardSuspendListService = new BoardSuspendListService();
		
		//페이징 처리
		//페이지 번호를 알기위해 사용하는 변수
		int page = 1;
		//한 페이지에 보여줄 글 갯수를 정하기 위해 사용하는 변수
		int limit = 10;
		//page파라미터값 검사
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//전체 글 갯수를 가져오기위한 listservice 클래스의 메소드 호출
		int listCount = boardSuspendListService.getListCount();
		
		/*ArrayList<boardBean> boardList = boardListService.getBoardList();*/
		//limit 값을 걸어놓은 만큼 범위에서 해당하는 글만 가져오는 방법
		ArrayList<BoardBean> boardList = boardSuspendListService.getBoardList(page, limit);
		
		//페이지 계산을 위한 부분
		int maxPage = (int)((double)listCount/limit + 1);
		//현제 페이지에서 보여줄 시작페이지 번호
		//1,2,3,4,5,6,7,8,9,10[다음]
		//[이전]11,12,13 ...[다음]
		int startPage = (((int)((double)page/10 + 0.9))-1)*10+1;
		
		int endPage = startPage +10 -1 ;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setListCount(listCount);
		
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfo", pageInfo);
		forward = new ActionForward();
		forward.setPath("adminBoard.jsp");
		//forward.setPath("boardSuspendList.kly");
		forward.setRedirect(true);
		return forward;


	}

}

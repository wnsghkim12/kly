package cotroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardWriteAction;
import action.MemberDetailAction;
import action.MemberDropAction;
import action.MemberJoinAction;
import action.MemberLoginAction;
import action.MemberLogoutAction;
import action.MemberModifyAction;
import bean.ActionForward;

@WebServlet("*.kly")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청받은 request의 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 요청 url에 대한 처리(command로 변환)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		/***상시 검사용***
		***/
		System.out.println("-- now At FrontController");
		System.out.println("RequestURI : "+RequestURI);
		System.out.println("RequestURI : "+contextPath);
		System.out.println("command : "+command);
		System.out.println();
		
		Action action = null;
		ActionForward forward = null;
		
		// command에 따른 ActionForward 인스턴스 생성
		if(command.equals("/memberJoin.kly")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/memberLogin.kly")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberLogout.kly")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberDetail.kly")) {
			action = new MemberDetailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberDrop.kly")) {
			action = new MemberDropAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberInfoRivision.kly")) {
			action = new MemberModifyAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardWrite.kly")) {
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// ActionForward 인스턴스에 따른 forwarding
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}

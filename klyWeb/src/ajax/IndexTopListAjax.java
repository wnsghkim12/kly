package ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BoardBean;
import service.BoardTopListService;

public class IndexTopListAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = new StringBuffer("");
		
		/*
		 * var obj = 0{ "BOARD_SUBJECT" : "값"};
		 * obj.BOARD_SUBJECT // 값
		 * 
		 * */
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		BoardTopListService boardTopListService = new BoardTopListService();
		
		ArrayList<BoardBean> topReadcountList = boardTopListService.getTopReadcountList();
		//ArrayList<BoardBean> topLikedList = boardTopListService.getTopLikeList();
		
		/** JSON syntax
		 * {
		 *	"employees":[ "John", "Anna", "Peter" ]
		 * }
		 * */

		result.append("{");
		// topReadcountList를 JSON 형태로 변환
		for(int i=0; i<topReadcountList.size(); i++) {
			if(i == topReadcountList.size()-1) {
				result.append("\"value"+ i +"\":"
						+ "[\"" + topReadcountList.get(i).getBOARD_NUM() +"\","
						+ "\"https://img.youtube.com/vi/5WCzoESZLTo/3.jpg\"," // https://img.youtube.com/vi/5WCzoESZLTo/3.jpg []링크 추가, [] 관련 DB 변경
						+ "\"" + topReadcountList.get(i).getBOARD_SUBJECT() +"\","
						+ "\"" + topReadcountList.get(i).getBOARD_READCOUNT() +"\"]"
						);
			} else {
				result.append("\"value"+ i +"\":"
						+ "[\"" + topReadcountList.get(i).getBOARD_NUM() +"\","
						+ "\"https://img.youtube.com/vi/5WCzoESZLTo/3.jpg\"," // https://img.youtube.com/vi/5WCzoESZLTo/3.jpg
						+ "\"" + topReadcountList.get(i).getBOARD_SUBJECT() +"\","
						+ "\"" + topReadcountList.get(i).getBOARD_READCOUNT() +"\"], "
						);
			}
		}
		result.append("}");
		request.setAttribute("topReadcountList", topReadcountList);
		System.out.println(result.toString());
		return result.toString();
	}

}

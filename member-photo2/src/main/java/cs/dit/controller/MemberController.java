package cs.dit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.service.MemberDeleteService;
import cs.dit.service.MemberGetService;
import cs.dit.service.MemberInsertService;
import cs.dit.service.MemberListService;
import cs.dit.service.MemberService;
import cs.dit.service.MemberUpdateService;

@WebServlet("*.do")
@MultipartConfig(
	location = "C:/tmp",
	maxFileSize = -1,
	maxRequestSize = -1
)
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberListService();
//	private MemberService service = new MemberServiceService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));
		System.out.println(com);
		String viewPage = null;
		
		if(com != null && com.equals("list")) {
			MemberService service = new MemberListService();
			viewPage = "/WEB-INF/views/list.jsp";
			service.excute(request, response);
		}else if(com != null && com.equals("insertForm")) {
			viewPage = "/WEB-INF/views/insertForm.jsp";
			service.excute(request, response);
		}else if(com != null && com.equals("insert")) {
			MemberService service = new MemberInsertService();
			viewPage = "list.do";
			service.excute(request, response);
		}else if(com != null && com.equals("updateForm")){
			MemberService service = new MemberGetService();
			viewPage = "/WEB-INF/views/updateForm.jsp";
			service.excute(request, response);
		}else if(com != null && com.equals("update")){
			MemberService service = new MemberUpdateService();
			viewPage = "list.do";
			service.excute(request, response);
		}else if(com != null && com.equals("delete")){
			MemberService service = new MemberDeleteService();
			viewPage = "list.do";
			service.excute(request, response);
		}else if(com != null && com.equals("index")){
			viewPage = "/WEB-INF/views/index.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

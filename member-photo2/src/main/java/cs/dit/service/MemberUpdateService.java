package cs.dit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.member.MemberDAO;
import cs.dit.member.MemberDTO;

public class MemberUpdateService implements MemberService {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String photo = request.getParameter("photo");

		MemberDTO dto = new MemberDTO(id, name, pwd, photo);
		MemberDAO dao = new MemberDAO();
		
		dao.update(dto);

	}

}

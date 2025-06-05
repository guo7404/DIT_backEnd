package cs.dit.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cs.dit.member.MemberDAO;
import cs.dit.member.MemberDTO;

public class MemberInsertService implements MemberService {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String photo = "";
		
		// 파일 업로드 처리
		String dir = request.getServletContext().getRealPath("/photos");
		File f = new File(dir);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		Collection<Part> parts = request.getParts();
		for(Part p : parts) {
			if(p.getHeader("Content-Disposition").contains("filename=")) {
				if(p.getSize()>0) {
					photo = p.getSubmittedFileName();
					String filePath = dir + File.separator + photo;
					p.write(filePath);
					p.delete();
				}
			}
		}

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO(id, name, pwd, photo);
		dao.insert(dto);
	}

}

package android.com.mb.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.AndroidImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import android.com.mb.model.MemberDAO;
import com.mb.model.MemberVO;



public class MemberServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		
		MemberDAO memberDAO = new MemberDAO();
		
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MemberDAO memberDao = new MemberDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		
		if (action.equals("isMember")) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			String mb_pwd = jsonObject.get("mb_pwd").getAsString();
			writeText(res, String.valueOf(memberDao.isMember(mb_id, mb_pwd)));
		} 
		else if (action.equals("isUserIdExist")) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			writeText(res, String.valueOf(memberDao.isUserIdExist(mb_id)));
		}
		else if ("getAll".equals(action)) {
			List<MemberVO> memberList = memberDAO.getAll();
			writeText(res, gson.toJson(memberList));
		} else if("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String path_no = jsonObject.get("mb_id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = memberDao.getImage(path_no);
			
			if(image != null) {
				image = AndroidImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
				os.write(image);
			} 
		} else if (action.equals("findById")) {
			String userId = jsonObject.get("userId").getAsString();
			MemberVO member = memberDao.findByPrimaryKey(userId);
			writeText(res, member == null ? "" : gson.toJson(member));
		}
		
		
		
		
//		else if (action.equals("add")) {
//			Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//			writeText(res, String.valueOf(memberDao.add(member)));
//		} 
//		else if (action.equals("findById")) {
//			String userId = jsonObject.get("userId").getAsString();
//			Member member = memberDao.findById(userId);
//			writeText(res, member == null ? "" : gson.toJson(member));
//		} 
//		else if (action.equals("update")) {
//			Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//			writeText(res, String.valueOf(memberDao.update(member)));
//		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		List<MemberVO> memberList = memberDAO.getAll();
		Gson gson = new Gson();
		writeText(res, gson.toJson(memberList));
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
}

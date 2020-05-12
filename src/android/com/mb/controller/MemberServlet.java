package android.com.mb.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
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
		} else if ("registerSucceedOrNot".equals(action)) {
			MemberVO memberVO = new MemberVO();
//			String mb_name = jsonObject.get("mb_name").getAsString();
//			String mb_id = jsonObject.get("mb_id").getAsString();
//			String mb_pwd = jsonObject.get("mb_pwd").getAsString();
//			String mb_email = jsonObject.get("mb_email").getAsString();
//			String mb_line = jsonObject.get("mb_line").getAsString();
//			Boolean boy_then_true = jsonObject.get("boy_then_true").getAsBoolean();
			
			
			memberVO.setMb_name(jsonObject.get("mb_name").getAsString());
			memberVO.setMb_id(jsonObject.get("mb_id").getAsString());
			memberVO.setMb_pwd(jsonObject.get("mb_pwd").getAsString());
			memberVO.setMb_email(jsonObject.get("mb_email").getAsString());
			memberVO.setMb_line_id(jsonObject.get("mb_line_id").getAsString());
			memberVO.setMb_gender(jsonObject.get("boy_then_true").getAsBoolean()? 1:2);

			
			String mb_birthday = jsonObject.get("mb_birthday").getAsString();
			SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");
			
			java.util.Date mb_birthday_date = new java.util.Date();
			
			try {
				mb_birthday_date=myFmt.parse(mb_birthday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			java.sql.Date mb_birthday_sqlDate = new java.sql.Date(mb_birthday_date.getTime());
			memberVO.setMb_birthday(mb_birthday_sqlDate);
			
			
			
			
			
			
			
			
			
			
			
			JsonObject jsonObject1 = gson.fromJson(jsonIn.toString(), JsonObject.class);
			String imageBase64 = jsonObject1.get("mb_pic").getAsString();
			byte[] image = Base64.getMimeDecoder().decode(imageBase64);
			memberVO.setMb_pic(image);
			
			writeText(res, String.valueOf(memberDao.insertFromAndroid(memberVO)));
			
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

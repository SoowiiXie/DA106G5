package android.com.grouper.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.location.model.LocationDAO;
import com.location.model.LocationVO;

import android.com.grouper.model.*;



public class GrouperServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		GrouperDAO grouperDAO = new GrouperDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		
		
		if ("getAll".equals(action)) {
			List<GrouperVO> grouperList = grouperDAO.getAll();
			writeText(res, gson.toJson(grouperList));
		}
		else if("getHosted".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			List<GrouperVO> grouperList=grouperDAO.getHosted(mb_id);
			writeText(res, gson.toJson(grouperList));
		}
		else if("getFollow".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			List<GrouperVO> grouperList=grouperDAO.getFollow(mb_id);
			writeText(res, gson.toJson(grouperList));
		}
		else if("getJoined".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			List<GrouperVO> grouperList=grouperDAO.getJoined(mb_id);
			writeText(res, gson.toJson(grouperList));
		}
		else if("joinGroup".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			String grp_no = jsonObject.get("grp_no").getAsString();
			writeText(res, String.valueOf(grouperDAO.joinGroup(mb_id,grp_no)));
		}
		else if("cancelGroup".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			String grp_no = jsonObject.get("grp_no").getAsString();
			writeText(res, String.valueOf(grouperDAO.cancelGroup(mb_id,grp_no)));
		}
		else if("addFollowedGroup".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			String grp_no = jsonObject.get("grp_no").getAsString();
			writeText(res, String.valueOf(grouperDAO.addFollowedGroup(mb_id,grp_no)));
		}
		else if("cancelFollowedGroup".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			String grp_no = jsonObject.get("grp_no").getAsString();
			writeText(res, String.valueOf(grouperDAO.cancelFollowedGroup(mb_id,grp_no)));
		}
		else if("updateGrp_DetailStatus".equals(action)) {
			String mb_id = jsonObject.get("mb_id").getAsString();
			String grp_no = jsonObject.get("grp_no").getAsString();
			writeText(res, String.valueOf(grouperDAO.updateGrp_DetailStatus(mb_id,grp_no)));
		}else if("getLocation".equals(action)) {
			LocationDAO locationDAO = new LocationDAO();
			List<LocationVO> locationList = locationDAO.getAll();
			writeText(res, gson.toJson(locationList));
		}
		else if("insert".equals(action)) {
			GrouperVO groupvo = new GrouperVO();
			groupvo.setMb_id(jsonObject.get("mb_id").getAsString());
			groupvo.setLoc_no(jsonObject.get("loc_no").getAsString());
			groupvo.setGrp_name(jsonObject.get("grp_name").getAsString());
			groupvo.setGrp_content(jsonObject.get("grp_content").getAsString());
			groupvo.setGrp_personmax(jsonObject.get("grp_personmax").getAsInt());
			groupvo.setGrp_personmin(jsonObject.get("grp_personmin").getAsInt());
			
			
			String grp_applystart = jsonObject.get("grp_applystart").getAsString();
			String grp_applyend = jsonObject.get("grp_applyend").getAsString();
			String grp_start = jsonObject.get("grp_start").getAsString();
			String grp_end = jsonObject.get("grp_end").getAsString();
			
			
			
			groupvo.setGrp_applystart(dateFormatChange(grp_applystart));
			groupvo.setGrp_applyend(dateFormatChange(grp_applyend));
			groupvo.setGrp_start(dateFormatChange(grp_start));
			groupvo.setGrp_end(dateFormatChange(grp_end));
			

			writeText(res, String.valueOf(grouperDAO.insert(groupvo)));
		}
		else if("getGrp_register".equals(action)) {
			String grp_no = jsonObject.get("grp_no").getAsString();
			List<String> applyList = grouperDAO.getGrp_registerFromGrp_detail(grp_no);
			writeText(res, gson.toJson(applyList));
		}
		else if("getAllGrp_register".equals(action)) {
			String grp_no = jsonObject.get("grp_no").getAsString();
			List<String> shouldApplyList = grouperDAO.getAllGrp_registerFromGrp_detail(grp_no);
			writeText(res, gson.toJson(shouldApplyList));
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		GrouperDAO grouperDAO = new GrouperDAO();
		List<GrouperVO> grouperList = grouperDAO.getAll();
		Gson gson = new Gson();
		writeText(res, gson.toJson(grouperList));
//		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
	
	
private java.sql.Timestamp dateFormatChange(String string_Date) {
		
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		java.util.Date utilDate_Date = new java.util.Date();
		
		try {
			utilDate_Date=myFmt.parse(string_Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date sqlDate_Date = new java.sql.Date(utilDate_Date.getTime());
		
		java.sql.Timestamp timestamp = new java.sql.Timestamp(sqlDate_Date.getTime());
		
		
		return timestamp;
		
	}
	
	
	
}

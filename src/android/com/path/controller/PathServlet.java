package android.com.path.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.path.model.*;
import com.record.model.RecordService;
import com.record.model.RecordVO;
import com.path.model.*;

/**
 * Servlet implementation class PathServlet
 */

public class PathServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
System.out.println("input: " + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		PathService pathSvc = new PathService();
		String action = jsonObject.get("action").getAsString();
//String userId = jsonObject.get("userId").getAsString();
		
		if("add".equals(action) /*&& userId != null*/) {
			PathVO pathVO = new PathVO();
			String path_no = pathSvc.addPath();
			req.setAttribute("path_no", path_no);
		}else if("getall".equals(action)) {
			List<PathVO> pathList = new ArrayList<>();;
			pathList = pathSvc.getAll();
			String jsonStr = gson.toJson(pathList);
//System.out.println(jsonStr);
			if(pathList!=null) {
				writeText(res, jsonStr);
			}
		}else if("getOnePath".equals(action)) {
			
		}else if("update".equals(action)) {
			String path_name = jsonObject.get("path_name").getAsString();
//System.out.println(path_name);			
			Integer path_difficulty = jsonObject.get("path_difficulty").getAsInt();
			
			String path_end = jsonObject.get("path_end").getAsString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date = new java.util.Date();
			try {
				date = sdf.parse(path_end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Timestamp path_end_db = new java.sql.Timestamp(date.getTime());
//System.out.println(path_end_db);			
			Double path_distance = jsonObject.get("path_distance").getAsDouble();
			
			String path_kml = jsonObject.get("path_kml").getAsString();
			
			Double path_lat = jsonObject.get("path_lat").getAsDouble();
//System.out.println(path_lat);		
			Double path_lng = jsonObject.get("path_lng").getAsDouble();
//System.out.println(path_lng);			
			String rcd_content = jsonObject.get("rcd_content").getAsString();
//System.out.println(rcd_content);			
			String mb_id = jsonObject.get("mb_id").getAsString();
//System.out.println(mb_id);			
			String path_no = req.getParameter("path_no");
			
			PathVO pathVO = new PathVO();
			pathVO = pathSvc.updatePath(path_name, path_difficulty, path_end_db, path_distance, path_kml, path_lat, path_lng, path_no);

			java.sql.Date rcd_uploadtime = new java.sql.Date(date.getTime()); 
//System.out.println(rcd_uploadtime);			
				
			RecordVO recordVO = new RecordVO();
			recordVO = new RecordService().addRecord(rcd_uploadtime, rcd_content, path_no, mb_id);
			if(pathVO!=null && recordVO != null) {
				writeText(res, "修改成功&新增記錄成功");
			}else {
				writeText(res, "失敗");
			}
		}

	}
	
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
System.out.println("outText: " + outText);
	}
	
	

}

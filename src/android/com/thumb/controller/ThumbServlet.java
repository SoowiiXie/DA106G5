package android.com.thumb.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mb_follow.model.Mb_followService;
import com.metoo.model.*;
import com.thumb.model.ThumbService;
import com.thumb.model.ThumbVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class ThumbServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
System.out.println("input: "+ jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		ThumbService ThumbSvc = new ThumbService();
		String action = jsonObject.get("action").getAsString();
		
		if("insert".equals(action)) {
			String rcd_no = jsonObject.get("rcd_no").getAsString();
			String mb_id = jsonObject.get("mb_id").getAsString();
			ThumbVO thumbVO = new ThumbVO();
			thumbVO.setMb_id(mb_id);
			thumbVO.setRcd_no(rcd_no);
			ThumbSvc.addThumb(mb_id, rcd_no);
		}if("delete".equals(action)) {
			String rcd_no = jsonObject.get("rcd_no").getAsString();
			String mb_id = jsonObject.get("mb_id").getAsString();
			ThumbVO thumbVO = new ThumbVO();
			thumbVO.setMb_id(mb_id);
			thumbVO.setRcd_no(rcd_no);
			ThumbSvc.deleteThumb(mb_id, rcd_no);
		}

	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
System.out.println("outText: "+ outText);
	}
}

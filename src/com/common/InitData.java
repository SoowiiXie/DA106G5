package com.common;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitData extends HttpServlet {
	
    public InitData() {
    	
    }
    
    @Override
    public void init() throws ServletException {
    	
    	ServletContext context = getServletContext();
    	
    	/** 名軒區域開始 **/
    	
    	// 會員狀態
    	Map<String,String> memberStatus = new HashMap<String,String>();
    	memberStatus.put("1", "未驗證");
    	memberStatus.put("2", "已驗證");
    	memberStatus.put("3", "停權");
    	context.setAttribute("memberStatus", memberStatus);
    	
    	// 訊息狀態
    	Map<String,String> messageStatus = new HashMap<String,String>();
    	messageStatus.put("1", "未讀");
    	messageStatus.put("2", "已讀");
    	context.setAttribute("messageStatus", messageStatus);
    	
    	// 通知狀態
    	Map<String,String> notifyStatus = new HashMap<String,String>();
    	notifyStatus.put("1", "未讀");
    	notifyStatus.put("2", "已讀");
    	context.setAttribute("notifyStatus", notifyStatus);
    	
    	// 管理員狀態
    	Map<String,String> staffStatus = new HashMap<String,String>();
    	staffStatus.put("1", "在職");
    	staffStatus.put("2", "已離職");
    	context.setAttribute("staffStatus", staffStatus);
    	
    	// 回報問題狀態
    	Map<String,String> questionRptStatus = new HashMap<String,String>();
    	questionRptStatus.put("1", "未處理");
    	questionRptStatus.put("2", "已處理");
    	context.setAttribute("questionRptStatus", questionRptStatus);
    	
    	// 會員檢舉處理狀態
    	Map<String,String> mbRptStatus = new HashMap<String,String>();
    	mbRptStatus.put("1", "未處理");
    	mbRptStatus.put("2", "檢舉成功");
    	mbRptStatus.put("3", "檢舉失敗");
    	context.setAttribute("mbRptStatus", mbRptStatus);
    	
    	/** 名軒區域結束 **/
    }
}

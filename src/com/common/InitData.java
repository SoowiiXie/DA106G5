package com.common;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitData extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InitData() {
    	
    }
    
    @Override
    public void init() throws ServletException {
    	
    	ServletContext context = getServletContext();
    	
    	/** 名軒區域開始 **/
    	
    	// 會員性別 LinkedHashMap
    	Map<Integer,String> memberGender = new LinkedHashMap<Integer,String>();
    	memberGender.put(1, "男");
    	memberGender.put(2, "女");
    	context.setAttribute("memberGender", memberGender);
    	
    	// 會員狀態 LinkedHashMap
    	Map<Integer,String> memberStatus = new LinkedHashMap<Integer,String>();
    	memberStatus.put(1, "未驗證");
    	memberStatus.put(2, "已驗證");
    	memberStatus.put(3, "停權");
    	context.setAttribute("memberStatus", memberStatus);
    	
    	// 訊息狀態
    	Map<Integer,String> messageStatus = new LinkedHashMap<Integer,String>();
    	messageStatus.put(1, "未讀");
    	messageStatus.put(2, "已讀");
    	context.setAttribute("messageStatus", messageStatus);
    	
    	// 通知狀態
    	Map<Integer,String> notifyStatus = new LinkedHashMap<Integer,String>();
    	notifyStatus.put(1, "未讀");
    	notifyStatus.put(2, "已讀");
    	context.setAttribute("notifyStatus", notifyStatus);
    	
    	// 管理員狀態
    	Map<Integer,String> staffStatus = new LinkedHashMap<Integer,String>();
    	staffStatus.put(1, "在職");
    	staffStatus.put(2, "離職");
    	context.setAttribute("staffStatus", staffStatus);
    	
    	// 回報問題狀態
    	Map<Integer,String> questionRptStatus = new LinkedHashMap<Integer,String>();
    	questionRptStatus.put(1, "未處理");
    	questionRptStatus.put(2, "已處理");
    	context.setAttribute("questionRptStatus", questionRptStatus);
    	
    	// 會員檢舉處理狀態
    	Map<Integer,String> mbRptStatus = new LinkedHashMap<Integer,String>();
    	mbRptStatus.put(1, "未處理");
    	mbRptStatus.put(2, "檢舉成功");
    	mbRptStatus.put(3, "檢舉失敗");
    	context.setAttribute("mbRptStatus", mbRptStatus);
    	
    	/** 名軒區域結束 **/
    	
    	/** 麥克區域開始**/
    	// 路徑狀態
    	Map<Integer,String> rcd_status = new LinkedHashMap<Integer,String>();
    	rcd_status.put(1, "上架");
    	rcd_status.put(2, "下架");
    	
    	//紀錄檢舉狀態
    	Map<Integer,String> rcdRpt_status = new LinkedHashMap<Integer,String>();
    	rcdRpt_status.put(1, "未處理");
    	rcdRpt_status.put(2,"檢舉成功");
    	rcdRpt_status.put(3, "檢舉失敗");
    	
    	//路徑難度狀態
    	Map<Integer,String> path_difficulty = new LinkedHashMap<Integer,String>();
    	path_difficulty.put(1, "簡單");
    	path_difficulty.put(2, "中等");
    	path_difficulty.put(3, "困難");
    	
    	//路徑困難度狀態
    	Map<Integer,String> path_popular = new LinkedHashMap<Integer,String>();
    	path_popular.put(1, "一般");
    	path_popular.put(2, "熱門");
    	
    	//路徑狀態
    	Map<Integer,String> path_status = new LinkedHashMap<Integer,String>();
    	path_status.put(1, "上架");
    	path_status.put(2, "下架");
    	/** 麥克區域結束**/
    	
    	/** 戍乂區域開始 **/
    		//GROUP的EL練習
	    	Map<Integer,String> grp_statusInit = new LinkedHashMap<Integer,String>();
	    	grp_statusInit.put(1, "上架");
	    	grp_statusInit.put(2, "下架");
	    	grp_statusInit.put(3, "中架");
	    	context.setAttribute("grp_statusInit", grp_statusInit);
    	/** 戍乂區域結束 **/
    }
}

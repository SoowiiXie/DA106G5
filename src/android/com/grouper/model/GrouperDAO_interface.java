	package android.com.grouper.model;

import java.util.*;

public interface GrouperDAO_interface {
	
    public boolean insert(GrouperVO grouperVO);
    public void update(GrouperVO grouperVO);
    
    
    
    
    //updatePlusOne及updateMinusOne只更動Grouper中人數+1及-1，"必須"搭配addToGrp_Detail及deleteFromGrp_Detail以刪除該會員的已參加揪團
    public boolean updatePlusOne(GrouperVO grouperVO);
    public boolean updateMinusOne(GrouperVO grouperVO);
    
    public boolean addToGrp_Detail(String mb_id,String grp_no);
    public boolean deleteFromGrp_Detail(String mb_id,String grp_no);
    
    
    //joinGroup此方法裡搭配了getJoined,updatePlusOne,addToGrp_Detail三個方法使用@@
    public String joinGroup(String mb_id,String grp_no);
    public boolean cancelGroup(String mb_id,String grp_no);
    
    //增加/取消關注揪團,裡面有updateGrp_FollowInGrouperPlusOne和updateGrp_FollowInGrouperMinusOne使GROUPER中人數+1或-1
    public boolean addFollowedGroup(String mb_id,String grp_no);
    public boolean cancelFollowedGroup(String mb_id,String grp_no);
    
    public boolean updateGrp_FollowInGrouperPlusOne(String grp_no);
    public boolean updateGrp_FollowInGrouperMinusOne(String grp_no);
    
    //QRCode掃描後完成報到
    public boolean updateGrp_DetailStatus(String mb_id,String grp_no);
    
    
    
    
    public List<GrouperVO> getHosted(String mb_id);
    public List<GrouperVO> getFollow(String mb_id);
    public List<GrouperVO> getJoined(String mb_id);
    public List<GrouperVO> getAll();
    
    
    
    public void delete(String grp_no);
    public GrouperVO findByPrimaryKey(String grp_no);
    
    public List<String> getAllGrp_registerFromGrp_detail(String grp_no);
    public List<String> getGrp_registerFromGrp_detail(String grp_no);
    
    
    
    
    
    
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    }

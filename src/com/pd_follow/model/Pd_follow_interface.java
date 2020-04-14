package com.pd_follow.model;
import java.util.*;

public interface Pd_follow_interface {
          public int insertMemberOneProduct (Pd_followVO pd_followVO); //增加一個會員的商品收藏
//          public int update(Pd_followVO pd_followVO); //不應該修改商品收藏，不符商業邏輯。
          public int deleteMemberOneProduct(Pd_followVO pd_followVO); //刪除會員一個商品收藏
          public List<Pd_followVO> searchMemberAllPdFollow(String mb_id); //列出單一會員商品收藏
          public List<Pd_followVO> getAll();//列出所有會員的商品收藏
         
}
	
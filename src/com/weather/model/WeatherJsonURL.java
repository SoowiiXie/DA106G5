package com.weather.model;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
//import net.sf.json.JSONObject;
import org.json.*;

public class WeatherJsonURL {
    public static void main(String[] args) throws Exception {
        String url = "https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/F-C0032-001?Authorization=CWB-1D147D77-107C-4005-8F8E-94CE678C3779&downloadType=WEB&format=JSON";
//      String url2 = "http://opendata.cwb.gov.tw/opendataapi?dataid=F-C0032-001&authorizationkey=CWB-77235BD2-2020-4346-A37E-DEE22EB1D2D8"; //doc_name,user_key
//      String url3 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=24.9670306,121.1921616&radius=1000&types=food&name=&language=zh-TW&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI";
//      String url4 = "https://v2-api.sheety.co/af46c17763293c918b7674dc2134a95d/da106/food";

//      URL myURL = new URL(url2);
//    	HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
//    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		InputStream in = conn.getInputStream();
//		BufferedInputStream bf = new BufferedInputStream(in);
        
        InputStream is = new URL(url).openStream();
        try {
             BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8")); //避免中文亂碼問題
             StringBuilder sb = new StringBuilder();
             int cp;
             while ((cp = rd.read()) != -1) {
                 sb.append((char) cp);
             }
//           System.out.println(sb);
//           JSONObject json = JSONObject.fromObject(sb.toString());
             //整個JSON檔
             JSONObject jsonObj = new JSONObject(sb.toString());
             //氣象局公開資源
             JSONObject jsonObjCwbopendata = new JSONObject(jsonObj.get("cwbopendata").toString());
             //資料設定
             JSONObject jsonObjDataset = new JSONObject(jsonObjCwbopendata.get("dataset").toString());
             
             //地點
             JSONArray jsonArrayLocation = jsonObjDataset.getJSONArray("location");
             for (int i = 0; i < jsonArrayLocation.length(); i++) {
                 JSONObject jsonObjLocation = new JSONObject(jsonArrayLocation.get(i).toString());
                 //地點名稱
                 System.out.println("第"+(i+1)+"個城市："+jsonObjLocation.get("locationName"));
                 
                 //天氣元素
                 JSONArray jsonArrayWeatherElement = jsonObjLocation.getJSONArray("weatherElement");
                 for (int j = 0; j < jsonArrayWeatherElement.length(); j++) {
                	 JSONObject jsonObjWeatherElement = new JSONObject(jsonArrayWeatherElement.get(j).toString());
                	 //元素名稱
                	 System.out.println(jsonObjWeatherElement.get("elementName")+":");
                	 
                	 //時間
                	 JSONArray jsonArrayTime = jsonObjWeatherElement.getJSONArray("time");
                	 for (int k = 0; k < jsonArrayTime.length(); k++) {
                		 JSONObject jsonObjTime = new JSONObject(jsonArrayTime.get(k).toString());
                		 //開始到結束
                		 System.out.print(jsonObjTime.get("startTime").toString()+" 到 "+jsonObjTime.get("endTime").toString() + "時 : ");
                		 
                		 //參數及單位
                		 JSONObject jsonObjParameter = new JSONObject(jsonObjTime.get("parameter").toString());
                		 System.out.println(jsonObjParameter.get("parameterName").toString());
//                		 System.out.println(jsonObjParameter.get("parameterValue").toString());
					 }
				 }
             }
        } finally {
             is.close();
        }
    }
}

<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="com.mb.model.*"%>
<%
MemberService memberSvc = new MemberService();
MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
//用memberVO先取得會常使用到的mb_id
pageContext.setAttribute("mb_id", memberVO.getMb_id());

//取出所有地標
LocationService locationSvc = new LocationService();
// List<LocationVO> locationList = locationSvc.getAll();
// pageContext.setAttribute("locationList", locationList);
// JSONArray locationJSON = locationSvc.getAllJSON();
// pageContext.setAttribute("locationJSON", locationJSON);
// JSONArray locationJSON = locationSvc.getAllJSON();
// pageContext.setAttribute("locationJSON", locationJSON);
JSONArray sportFieldJSON = locationSvc.getAllJsonByType(1);
pageContext.setAttribute("sportFieldJSON", sportFieldJSON);
JSONArray toiletJSON = locationSvc.getAllJsonByType(2);
pageContext.setAttribute("toiletJSON", toiletJSON);
JSONArray waterJSON = locationSvc.getAllJsonByType(3);
pageContext.setAttribute("waterJSON", waterJSON);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Google Map Demo</title>
<style>
/* Always set the map height explicitly to define the size of the div
 * element that contains the map. */
#map {
  height: 30rem;
  width: 75%;
  float: left;
}
#tableOutput{
  height: 100%;
  width: 40%;
  float: left;
}
/* Optional: Makes the sample page fill the window. */
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}
#coords {
        background-color: black;
        color: white;
        padding: 5px;
}
</style>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>

</head>
<body>
	<!-- <div class="col-3">&nbsp&nbsp個人頁面</div> -->
	<nav aria-label="breadcrumb" class="col-12 my_breadcrumb">
		<ol class="breadcrumb m-0">
			<li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/front_end/member/login.jsp">登入畫面</a></li>
			<li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/front_end/member/login.jsp">準備</a></li>
			<li class="breadcrumb-item active" aria-current="page">地標</li>
		</ol>
	</nav>

	<div class="w-100"></div>
<!-- 	<div id="coords"></div> -->
	<!-- 當頁內容 -->
	
    <div id="map" class="ml-4 mr-2" data-aos="fade-right" data-aos-duration="3000"></div>
    <div id="tableOutput m-0"  data-aos="fade-right" data-aos-offset="600" data-aos-easing="ease-in-sine">
    
    				<div class="form-check form-check-inline ml-5 my-5 inlineCheckbox" id="inlineCheckbox1_div">
    					<label for="inlineCheckbox1"  class="form-check-label mr-2">補水點</label>
					    <input id="inlineCheckbox1" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-style="mr-1" checked value=1>
					</div></br>
    
     				<div class="form-check form-check-inline ml-5 my-5 inlineCheckbox" class="inlineCheckbox" id="inlineCheckbox2_div">
    					<label for="inlineCheckbox2" class="form-check-label mr-2">運動場</label>
					    <input id="inlineCheckbox2" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-onstyle="success" data-style="mr-1" checked value=2>
					</div></br>
    	
    				<div class="form-check form-check-inline ml-5 my-5 inlineCheckbox" class="inlineCheckbox" id="inlineCheckbox3_div">
    					<label for="inlineCheckbox3" class="form-check-label mr-2">御手洗</label>
					    <input id="inlineCheckbox3" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-onstyle="secondary" data-style="mr-1" checked value=3>
					</div>
<!--     		<tbody id="place"> -->
<!--     		</tbody> -->
    </div>
    	
	<!-- 地標檢舉新增的燈箱 -->
	<div id="fblightbox" class="loc_rptInsertBox">
	  <div class="fblightbox-wrap">
	    <div class="fblightbox-header">
	      	這個地標有問題
	    </div>
	    <div class="fblightbox-content p-0">
	    	<jsp:include page="../../back_end/loc_rpt/addLoc_rpt.jsp" />
	    </div>
	  </div>
	</div>
	<div class="overlay"></div>
	
    <script>
    var map;
    var markers = [];
    var searchResult;
    var iconBase = "<%= request.getContextPath() %>/img/";
    function initMap() {
      var myLatlng = {lat: 24.9677686, lng: 121.1916822};

      map = new google.maps.Map(document.getElementById('map'), {
        center: myLatlng,
        zoom: 17
      });

      var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: 'Click to zoom'
      });
      
//       map.addListener('click', function(event) {
//           console.log('lat: ' + event.latLng.lat() + ', ' +
//                   'lng: ' + event.latLng.lng());
//       });
      
      var locInsertBox = $('.locInsertBox');
      map.addListener('dblclick', function(event) {
    	  $('#lng_input').val(event.latLng.lat());
    	  $('#lat_input').val(event.latLng.lng());
    	  $('.lng_td').text(event.latLng.lat());
    	  $('.lat_td').text(event.latLng.lng());
    	  
    	  locInsertBox.fadeIn();
    	  $('.overlay').fadeIn();
      });

      // 擷取及時公車資訊
      callback(null, ${waterJSON}, ${sportFieldJSON}, ${toiletJSON});
    
      map.addListener('center_changed', function() {
  		deleteMarkers();
		var data1 = {};
		var data2 = {};
		var data3 = {};
		
		if($('#inlineCheckbox1').is(":checked")){
			data1 = ${waterJSON};
		}
		if($('#inlineCheckbox2').is(":checked")){
			data2 = ${sportFieldJSON};
		}
		if($('#inlineCheckbox3').is(":checked")){
			data3 = ${toiletJSON};
		}
		
		callback(null , data1, data2, data3);
// 	        	getJSON(${locationJSON}, callback);
// 	        	callback(null, ${sportFieldJSON});
      });
      
    }
        
    function getJSON(url, callback) {
//       var xhr = new XMLHttpRequest();
//       xhr.open('GET', url, true);
//       xhr.responseType = 'json';
//       xhr.onload = function() {
//         var status = xhr.status;
//         if (status === 200) {
//           callback(null, xhr.response);
//         } else {
//           callback(status, xhr.response);
//         }
//       };
//       xhr.send();
    }

    function callback(err, data1, data2, data3) {
      if (err !== null) {
        alert('Something went wrong: ' + err);
      } else {
// 		searchResult = data;
// 		data = null;
// 		var position1 = ${sportFieldJSON};
//         console.log(data[0]);
//         $("#place").empty();
//         var index = 1;
        for(var i = 0; i < data1.length; i++){
//           var lat = data[i].lat;
//           var lat = position1.lat;
//           var lng = data[i].lng;
//           var lng = position1.lng;
//           var icon = data[i].icon
//           var icon = position1.icon
            var latLng = new google.maps.LatLng(data1[i].lat, data1[i].lng);
//         	if(map.getBounds().contains(latLng)){
            addMarker(latLng, data1[i]);
//         		$("#place").append("<tr><td>"+lat+"</td><td>"+lng+"</td><td>"+icon+"</td></tr>");
//         		index++;
//         	}
        }
        for(var i = 0; i < data2.length; i++){
	        var latLng = new google.maps.LatLng(data2[i].lat, data2[i].lng);
	        addMarker(latLng, data2[i]);
        }
        for(var i = 0; i < data3.length; i++){
	        var latLng = new google.maps.LatLng(data3[i].lat, data3[i].lng);
	        addMarker(latLng, data3[i]);
        }
      }
    }
    
    // Adds a marker to the map and push to the array.
    function addMarker(latLng, result) {
      var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        icon: iconBase + result.icon,
        animation: google.maps.Animation.DROP
      });
      markers.push(marker);
      var infowindow = new google.maps.InfoWindow({
          content: "<h2>"+result.adr+"</h2>"+
          "<img src='data:image/jpg;base64,"+result.pic+"' style='height:10rem;' class='rounded'>"+
          "<input style='height: 1rem; opacity: 0.5;' class='infoFlagBtn' type='image' name='submit_Btn' src='<%=request.getContextPath()%>/img/flag.png'>"+
          "<input type='hidden' name='loc_no4rpt' class='loc_no4rpt' value='"+result.loc_no4json+"'>"+
          "<p><h2><b>經度: </b>"+result.lng+
          "</br>緯度:&nbsp;&nbsp;&nbsp;&nbsp;"+result.lat+"</h2></p></div>"
      });
      marker.addListener('click', function() {
    	  console.log(777);
          infowindow.open(map, marker);
      });
    }
    
    // Deletes all markers in the array by removing references to them.
    function deleteMarkers() {
  		setMapOnAll(null);
     	markers = [];
    }
    
    // Sets the map on all markers in the array.
    function setMapOnAll(map) {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
      }
    }

//     $(function(){
    	
//     });

	$('#inlineCheckbox1_div').on('click', function(){
		deleteMarkers();
		var data1 = {};
		var data2 = {};
		var data3 = {};
		
		if(!$('#inlineCheckbox1').is(":checked")){
			data1 = ${waterJSON};
		}
		if($('#inlineCheckbox2').is(":checked")){
			data2 = ${sportFieldJSON};
		}
		if($('#inlineCheckbox3').is(":checked")){
			data3 = ${toiletJSON};
		}
		
		callback(null , data1, data2, data3);

	});	

	$('#inlineCheckbox2_div').on('click', function(){
		deleteMarkers();
		var data1 = {};
		var data2 = {};
		var data3 = {};
		
		if($('#inlineCheckbox1').is(":checked")){
			data1 = ${waterJSON};
		}
		if(!$('#inlineCheckbox2').is(":checked")){
			data2 = ${sportFieldJSON};
		}
		if($('#inlineCheckbox3').is(":checked")){
			data3 = ${toiletJSON};
		}
		
		callback(null , data1, data2, data3);

	});	

	$('#inlineCheckbox3_div').on('click', function(){
		deleteMarkers();
		var data1 = {};
		var data2 = {};
		var data3 = {};
		
		if($('#inlineCheckbox1').is(":checked")){
			data1 = ${waterJSON};
		}
		if($('#inlineCheckbox2').is(":checked")){
			data2 = ${sportFieldJSON};
		}
		if(!$('#inlineCheckbox3').is(":checked")){
			data3 = ${toiletJSON};
		}
		
		callback(null , data1, data2, data3);

	});	
	
	 var loc_rptInsertBox = $('.loc_rptInsertBox');
	 console.log(loc_rptInsertBox);
	 loc_rptInsertBox.css({'margin-left':'-' + (loc_rptInsertBox.width()/2) + 'px' , 'margin-top' : '-' + (loc_rptInsertBox.height()/2)+'px'});
	 
	 $("body").on('click', '.infoFlagBtn',function(){
		 $("#loc_no4rptInsert").val($(this).next().val());
	     $("#mb_id4rpt").val("${mb_id}");
		 loc_rptInsertBox.fadeIn();
		 $('.overlay').fadeIn();
	 });
	 
	 //檢舉地標的燈箱
// 	 $('.infoFlagBtn').click(function(){
// 		 console.log(1);
// 		 alert(2);
// 		 loc_rptInsertBox.fadeIn();
// 		 $('.overlay').fadeIn();
// 	 });
    
	 //讓燈箱共用關閉按鈕
	 $("#close").click(function() {
		  $('.overlay').fadeOut();
		  fblightbox.fadeOut();
		  loc_rptInsertBox.fadeOut();
	 });
	 
	 $(".fbclose").click(function() {
		  $('.overlay').fadeOut();
		  fblightbox.fadeOut();
		  loc_rptInsertBox.fadeOut();
	 });
	 
	 $(".overlay").click(function() {
		  $('.overlay').fadeOut();
		  fblightbox.fadeOut();
		  loc_rptInsertBox.fadeOut();
	 });
	 
    window.onload = initMap;  //測試用
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZv52MGeQchjobQgjXxUNTBzgUmY1qN7g&callback=initMap"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
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
	<!-- 當頁內容 -->
    <div id="map" class="ml-4 mr-2"></div>
    <div id="tableOutput m-0">
    
    				<div class="form-check form-check-inline ml-5 my-5">
    					<label for="inlineCheckbox1" class="form-check-label mr-2">補水點</label>
					    <input id="inlineCheckbox1" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-style="mr-1" checked>
					</div></br>
    
     				<div class="form-check form-check-inline ml-5 my-5">
    					<label for="inlineCheckbox1" class="form-check-label mr-2">運動場</label>
					    <input id="inlineCheckbox1" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-onstyle="success" data-style="mr-1" checked>
					</div></br>
    	
    				<div class="form-check form-check-inline ml-5 my-5">
    					<label for="inlineCheckbox1" class="form-check-label mr-2">御手洗</label>
					    <input id="inlineCheckbox1" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-onstyle="secondary" data-style="mr-1" checked>
					</div>
    		
   
    		<tbody id="place">
    		</tbody>
    	</table>
    </div>
    <script>
    var map;
    var markers = [];
    var position = ${locationJSON};
    function initMap() {
      map = new google.maps.Map(document.getElementById('map'), {
        zoom: 17,
        center: {"lat": 24.9677686, "lng": 121.1916822}
      });

      for (var i = 0; i < position.length; i++) {
        addMarker(i);
      }
    }

    function addMarker(e) {
      markers[e] = new google.maps.Marker({
        position: {
          lat: position[e].lat,
          lng: position[e].lng
        },
        map: map,
        animation: google.maps.Animation.BOUNCE,
        label: position[e].label,
        icon: "<%= request.getContextPath() %>/img/"+position[e].icon,
      });
    }
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZv52MGeQchjobQgjXxUNTBzgUmY1qN7g&callback=initMap"></script>
</body>
</html>
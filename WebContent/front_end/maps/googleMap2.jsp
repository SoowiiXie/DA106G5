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
	
    <div id="map" class="ml-4 mr-2"></div>
    <div id="tableOutput m-0">
    
    				<div class="form-check form-check-inline ml-5 my-5" id="inlineCheckbox1_div">
    					<label for="inlineCheckbox1"  class="form-check-label mr-2">補水點</label>
					    <input id="inlineCheckbox1" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-style="mr-1" checked value=1>
					</div></br>
    
     				<div class="form-check form-check-inline ml-5 my-5">
    					<label for="inlineCheckbox2" class="form-check-label mr-2">運動場</label>
					    <input id="inlineCheckbox2" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-onstyle="success" data-style="mr-1" checked value=2>
					</div></br>
    	
    				<div class="form-check form-check-inline ml-5 my-5">
    					<label for="inlineCheckbox3" class="form-check-label mr-2">御手洗</label>
					    <input id="inlineCheckbox3" class="form-check-input" data-size="lg" type="checkbox" data-toggle="toggle" data-onstyle="secondary" data-style="mr-1" checked value=3>
					</div>
<!--     		<tbody id="place"> -->
<!--     		</tbody> -->
    </div>
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
      
      
      map.addListener('click', function(event) {
          console.log('lat: ' + event.latLng.lat() + ', ' +
                  'lng: ' + event.latLng.lng());
        });

      // 擷取及時公車資訊
      callback(null, ${locationJSON});
    
      map.addListener('center_changed', function() {
	        	deleteMarkers();
// 	        	getJSON(${locationJSON}, callback);
	        	callback(null, ${locationJSON});
      });
      
//       initGallPeters();
//       map.mapTypes.set('gallPeters', gallPetersMapType);
//       map.setMapTypeId('gallPeters');

//       // Show the lat and lng under the mouse cursor.
//       var coordsDiv = document.getElementById('coords');
//       map.controls[google.maps.ControlPosition.TOP_CENTER].push(coordsDiv);
//       map.addListener('mousemove', function(event) {
//         coordsDiv.textContent =
//             'lat: ' + Math.round(event.latLng.lat()) + ', ' +
//             'lng: ' + Math.round(event.latLng.lng());
//       });
      
//       var gallPetersMapType;
//       function initGallPeters() {
//         var GALL_PETERS_RANGE_X = 800;
//         var GALL_PETERS_RANGE_Y = 512;

//         // Fetch Gall-Peters tiles stored locally on our server.
//         gallPetersMapType = new google.maps.ImageMapType({
//           getTileUrl: function(coord, zoom) {
//             var scale = 1 << zoom;

//             // Wrap tiles horizontally.
//             var x = ((coord.x % scale) + scale) % scale;

//             // Don't wrap tiles vertically.
//             var y = coord.y;
//             if (y < 0 || y >= scale) return null;

//             return 'https://developers.google.com/maps/documentation/' +
//                    'javascript/examples/full/images/gall-peters_' + zoom +
//                    '_' + x + '_' + y + '.png';
//           },
//           tileSize: new google.maps.Size(GALL_PETERS_RANGE_X, GALL_PETERS_RANGE_Y),
//           minZoom: 0,
//           maxZoom: 1,
//           name: 'Gall-Peters'
//         });

//         // Describe the Gall-Peters projection used by these tiles.
//         gallPetersMapType.projection = {
//           fromLatLngToPoint: function(latLng) {
//             var latRadians = latLng.lat() * Math.PI / 180;
//             return new google.maps.Point(
//                 GALL_PETERS_RANGE_X * (0.5 + latLng.lng() / 360),
//                 GALL_PETERS_RANGE_Y * (0.5 - 0.5 * Math.sin(latRadians)));
//           },
//           fromPointToLatLng: function(point, noWrap) {
//             var x = point.x / GALL_PETERS_RANGE_X;
//             var y = Math.max(0, Math.min(1, point.y / GALL_PETERS_RANGE_Y));

//             return new google.maps.LatLng(
//                 Math.asin(1 - 2 * y) * 180 / Math.PI,
//                 -180 + 360 * x,
//                 noWrap);
//           }
//         };
//       }
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

    function callback(err, data) {
      if (err !== null) {
        alert('Something went wrong: ' + err);
      } else {
		searchResult = data;
		var position = ${locationJSON};
        console.log(data[0]);
        $("#place").empty();
        var index = 1;
        for(var i = 0; i < data.length; i++){
//           var lat = data[i].lat;
          var lat = position.lat;
//           var lng = data[i].lng;
          var lng = position.lng;
//           var icon = data[i].icon
          var icon = position.icon
          var latLng = new google.maps.LatLng(data[i].lat, data[i].lng);
//         	if(map.getBounds().contains(latLng)){
        		addMarker(latLng, data[i]);
//         		$("#place").append("<tr><td>"+lat+"</td><td>"+lng+"</td><td>"+icon+"</td></tr>");
//         		index++;
//         	}
        }
      }
    }
    
    // Adds a marker to the map and push to the array.
    function addMarker(latLng, result) {
      var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        icon: iconBase + result.icon
      });
      markers.push(marker);
      var infowindow = new google.maps.InfoWindow({
          content: "<h2><b>地名: </b>"+result.adr+"</h2><p><h2><b>位置: </b>"+result.lng+","+result.lat+"</h2></p></div>"
      });
      marker.addListener('click', function() {
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
    console.log($('#inlineCheckbox1_div'));
    $('#inlineCheckbox1_div').on('click', function(){
		alert($('#inlineCheckbox1').is(":checked"))
	});	
    
    window.onload = initMap;  //測試用
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZv52MGeQchjobQgjXxUNTBzgUmY1qN7g&callback=initMap"></script>
</body>
</html>
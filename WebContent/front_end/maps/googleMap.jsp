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
    var position = [
      {label:'A',lat:25.0336962,lng:121.5643673},
      {label:'B',lat:25.0333698,lng:121.5641564},
      {label:'C',lat:25.033899,lng:121.564329},
      {label:'D',lat:25.0338407,lng:121.5645269},
      {label:'E',lat:25.0336377,lng:121.5645727}
    ];

    function initMap() {
      map = new google.maps.Map(document.getElementById('map'), {
        zoom: 19,
        center: {
          lat: 25.0336962,
          lng: 121.5643673
        }
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
        label: position[e].label
      });
    }
//       function initMap() {
//         var myLatlng = {lat: 24.9677686, lng: 121.1916822};

//         map = new google.maps.Map(document.getElementById('map'), {
//           center: myLatlng,
//           zoom: 17
//         });
//         var marker = new google.maps.Marker({
//           position: myLatlng,
//           map: map,
//           title: 'Click to zoom'
//         });

//         // 擷取及時公車資訊
//         getJSON('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Taoyuan?$format=JSON', callback);
      
//         map.addListener('center_changed', function() {
// 	        	deleteMarkers();
// 	        	getJSON('http://ptx.transportdata.tw/MOTC/v2/Bus/RealTimeByFrequency/City/Taoyuan?$format=JSON', callback);
//         });
//       }
          
//       function getJSON(url, callback) {
//         var xhr = new XMLHttpRequest();
//         xhr.open('GET', url, true);
//         xhr.responseType = 'json';
//         xhr.onload = function() {
//           var status = xhr.status;
//           if (status === 200) {
//             callback(null, xhr.response);
//           } else {
//             callback(status, xhr.response);
//           }
//         };
//         xhr.send();
//       }

//       function callback(err, data) {
//         if (err !== null) {
//           alert('Something went wrong: ' + err);
//         } else {
// 		  searchResult = data;
//           console.log(data[0]);
//           $("#place").empty();
//           var index = 1;
//           for(var i = 0; i < data.length; i++){
//             var plateNumb = data[i].PlateNumb;
//             var routeName = data[i].RouteName.Zh_tw;
//             var operatorID = data[i].OperatorID;
//             var busPosition = data[i].BusPosition;
//             var latLng = new google.maps.LatLng(busPosition.PositionLat, busPosition.PositionLon);
//           	if(map.getBounds().contains(latLng)){
//           		addMarker(latLng, data[i]);
//           		$("#place").append("<tr><td>"+index+"</td><td>"+routeName+"</td><td>"+plateNumb+"</td><td>"+operatorID+"</td><td>"+busPosition.PositionLat+"</td><td>"+busPosition.PositionLon+"</td></tr>");
//           		index++;
//           	}
//           }
//         }
//       }
      
//       // Adds a marker to the map and push to the array.
//       function addMarker(latLng, result) {
//         var marker = new google.maps.Marker({
//           position: latLng,
//           map: map,
//           icon: iconBase + 'bus_maps.png'
//         });
//         markers.push(marker);
//         var infowindow = new google.maps.InfoWindow({
//             content: "<h2>"+result.RouteName.Zh_tw+"</h2><div><p><b>車號: </b>"+result.PlateNumb+"</p><p><b>司機: </b>"+result.OperatorID+"</p><p><b>位置: </b>{"+result.BusPosition.PositionLat+","+result.BusPosition.PositionLon+"}</p></div>"
//         });
//         marker.addListener('click', function() {
//             infowindow.open(map, marker);
//         });
//       }
      
//       // Deletes all markers in the array by removing references to them.
//       function deleteMarkers() {
//     		setMapOnAll(null);
//        	markers = [];
//       }
      
//       // Sets the map on all markers in the array.
//       function setMapOnAll(map) {
//         for (var i = 0; i < markers.length; i++) {
//           markers[i].setMap(map);
//         }
//       }

      // window.onload = initMap;  //測試用
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZv52MGeQchjobQgjXxUNTBzgUmY1qN7g&callback=initMap"></script>
</body>
</html>
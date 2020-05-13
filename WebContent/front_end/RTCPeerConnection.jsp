<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
	response.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=Strict");
%>

<%-- 模擬登入的hostID(直播主ID)為peter  --%>
<%  
     String hostID = (String)session.getAttribute("hostID");
     session.setAttribute("hostID", hostID); 
%>

<%-- 模擬登入的clientID(觀眾ID)為Anonymous  --%>
<%! int count = 0; %>
<%  
     String clientID = (String)session.getAttribute("clientID");
     if(clientID==null) 
    	 clientID="Anonymous"+(++count);
     else 
    	 clientID="Anonymous"+(++count);
     session.setAttribute("clientID", clientID); 
     System.out.println("--------------------------------------------");
     System.out.println(clientID);
     System.out.println("--------------------------------------------");
%>

<!DOCTYPE html>
<html>
<head>
<title>WebRTC Video One to many Broadcasting</title>

        <script>
            if(!location.hash.replace('#', '').length) {
                location.href = location.href.split('#')[0] + '#' + (Math.random() * 100).toString().replace('.', '');
                location.reload();
            }
        </script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- <link rel="stylesheet" href="livestream/css/styles1.css"> -->
<link rel="stylesheet" href="livestream/css/bootstrap-4.3.1.min.css">
<link rel="stylesheet" href="livestream/css/styles2.css">
<link rel="stylesheet" href="livestream/css/font-awesome-4.5.0/css/font-awesome.css">
<link rel="stylesheet" href="livestream/css/getHTMLMediaElement.css">
<script src="livestream/js/jquery-1.11.1.min.js"></script>
<script	src="livestream/js/sweetalert2.all.min.js"></script>
<script src="livestream/js/getHTMLMediaElement.js"></script>

<style>

@-webkit-keyframes gradientBG {
	0% {
		background-position: 0% 50%;
	}
	50% {
		background-position: 100% 50%;
	}
	100% {
		background-position: 0% 50%;
	}
}
@keyframes gradientBG {
	0% {
		background-position: 0% 50%;
	}
	50% {
		background-position: 100% 50%;
	}
	100% {
		background-position: 0% 50%;
	}
}

h2 {
  font-size: 1.5vw;
}

h4 {
  font-size: 1.2vw;
}

h6 {
  font-size: 0.85vw;
}

audio, video {
	-moz-transition: all 1s ease;
	-ms-transition: all 1s ease;
	-o-transition: all 1s ease;
	-webkit-transition: all 1s ease;
	transition: all 1s ease;
	vertical-align: top;
	width: 100%;
}

select {
	border: 2px solid #d9d9d9;
	border-radius: 1px;
	height: 40px;
	margin-left: 0.6em;
	margin-top: 2em;
	padding: 0.1em;
	vertical-align: 5px;
	width: 33%;
}

.broadcasting-option {
  height: 2vw;
  vertical-align: middle;
  margin: 0.4em;
  font-size: 1.0vw;
}

.broadcast-name {
	border: 2px solid #d9d9d9;
	border-radius: 1px;
	font-size: 1.5vw;
	margin: 0.4em;
	margin-left: 0.2em;
	width: 33%;
	color: rgb(204, 14, 14);
    font-weight: bold;
    vertical-align: middle;
}

.videosContainer {
    border: 6px ridge rgb(100, 100, 100);
    border-radius: 4px;
    margin: 1em;
}

p {
	padding: 1em;
}

li {
	border-bottom: 1px solid rgb(189, 189, 189);
	border-left: 1px solid rgb(189, 189, 189);
	padding: .5em;
}
</style>

<style>
.CustomCard {
	padding-top: 20px;
	margin: 10px 0 20px 0;
	background-color: rgba(214, 224, 226, 0.2);
	border-top-width: 0;
	border-bottom-width: 2px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 15px;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.CustomCard.hoverCustomCard {
	position: relative;
	padding-top: 0;
	overflow: hidden;
	text-align: center;
}

.CustomCard.hoverCustomCard .CustomCardheader {
	background-size: cover;
	height: 85px;
}

.CustomCard.hoverCustomCard .avatar {
	position: relative;
	top: -50px;
	margin-bottom: -50px;
}

.CustomCard.hoverCustomCard .avatar img {
	width: 100px;
	height: 100px;
	max-width: 100px;
	max-height: 100px;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
	border: 5px solid rgba(255, 255, 255, 0.5);
}

.CustomCard.hoverCustomCard .info {
	padding: 4px 8px 10px;
}

.CustomCard.hoverCustomCard .info .desc {
	overflow: hidden;
	font-size: 12px;
	line-height: 20px;
	color: #737373;
	text-overflow: ellipsis;
}

.CustomCard.hoverCustomCard .bottom {
	padding: 20px 5px;
	margin-bottom: -6px;
	text-align: center;
}

.btn {
	width: 100px;
	height: 30px;
	line-height: 0px;
}

html {
	background: #f4f9f4;
}
</style>

<!-- This Library is used to detect WebRTC features -->
<script src="livestream/js/webrtc/DetectRTC.js"></script>
<script src="livestream/js/webrtc/socket.io.js"> </script>
<script src="livestream/js/webrtc/adapter-latest.js"></script>
<script src="livestream/js/webrtc/IceServersHandler.js"></script>
<script src="livestream/js/webrtc/CodecsHandler.js"></script>
<script	src="livestream/js/webrtc/RTCPeerConnection-v1.5.js"></script>
<script	src="livestream/js/webrtc/broadcast.js"></script>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col col-sm-12" style="display: none;">
				<div class="CustomCard hoverCustomCard">
					<div id="CustomCardheader" class="CustomCardheader text-white btn-warning">
						<input type="hidden" id="hostID" value="${hostID}" />
						<input type="hidden" id="lsViewNum" value="0" />
				<% if(hostID!=null){ %>
						<h5 id="subTitle" class="col pt-2"> <strong>這是 ${hostID} 直播間</strong></h5>
				<%} else { %>		
						<h5 id="subTitle" class="col pt-2"> <strong>您已經進入直播間</strong></h5>
				 <%} %>		
						
						<i id="WebSocket-count" class="far pt-2 pr-3 float-right" style="position: absolute; right: 0; top: 0px">目前在線人數 - </i>
						<i id="WebRTC-count"    class="far pt-2 pr-3 float-right" style="position: absolute; right: 0; top: 20px">WebRTC 累計觀看人數 0 </i>
					</div>
					<div class="avatar"> <img  id="slider" src="images/disw.gif"></div>

					<div class="bottom mx-auto">
						<button class="btn-3d-can" id="record"   style="display: none" disabled="disabled">開始錄影</button>
						<button class="btn-3d-can" id="download" style="display: none" disabled="disabled">儲存錄影</button>
						<button id="play" class="btn btn-success" style="display: none">播放</button>
					    <div style="display: none">
							<p>Echo cancellation: <input type="checkbox" id="echoCancellation"></p>
							<div id="errorMsg"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="col col-sm-12">
				<article>
<!-- 					<header style="text-align: center;"> -->
<!-- 						<h2>WebRTC 視 頻 廣 播</h2> -->
<!-- 					</header><br> -->

					<section id="session1" class="experiment">
						<section <%= (hostID==null)? "style='visibility: hidden;'":"" %>>
							<select id="broadcasting-option" class="broadcasting-option">
								<option>Audio + Video</option>
								<option>Only Audio</option>
							</select> 
							<input type="text" id="broadcast-name" class="broadcast-name" value="${hostID}">
							<button id="setup-new-broadcast">啟動新視頻</button>
						</section>
						<!-- list of all available broadcasting rooms --><br>
						<table style="width: 100%;" id="rooms-list"></table>
						<!-- local/remote videos container -->
						<div id="videos-container"></div>
						<div class="visible">
							<div style="text-align: center;">
									<h2><code><strong id="unique-token">#123456789</strong></code></h2>
							</div>
						</div>
					</section>
				</article>
			</div>
			<script>
			 var visibleElements = document.getElementsByClassName('visible'),
             length = visibleElements.length;
             for (var i = 0; i < length; i++) {
                visibleElements[i].style.display = 'none';
             }
			</script>
	
			<div class="col col-sm-3" style="height: 600px; display: none;">
				<br><br><br><br>
				<h4>WebSocket chatroom</h4>
				<textarea id="messagesArea" class="panel message-area" readonly></textarea>
				<div class="panel input-area">
				   <div id="webSocket-submit" class="g1">
					    <input id="message"  class="panel input-default" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();" /><br>
					    <input id="userName" class="panel input-default" type="text" placeholder="暱稱" value="${(hostID!=null)? hostID :clientID}" readonly="readonly"/>
					    <input type="submit" id="sendMessage" class="btn btn-danger" value="送出訊息" onclick="sendMessage();" />
				   </div>
				</div>
			</div>
		</div>
	</div>
</body>
			
			<script>
                var config = {
                    openSocket: function(config) {
                      var SIGNALING_SERVER = 'https://socketio-over-nodejs2.herokuapp.com:443/';
//
//                        var SIGNALING_SERVER = 'https://192.168.196.195:9001/';

                        config.channel = config.channel || location.href.replace(/\/|:|#|%|\.|\[|\]/g, '');
                        var sender = Math.round(Math.random() * 999999999) + 999999999;

                        io.connect(SIGNALING_SERVER).emit('new-channel', {
                            channel: config.channel,
                            sender: sender
                        });

                        var socket = io.connect(SIGNALING_SERVER + config.channel);
                        socket.channel = config.channel;
                        socket.on('connect', function () {
                            if (config.callback) config.callback(socket);
                        });

                        socket.send = function (message) {
                            socket.emit('message', {
                                sender: sender,
                                data: message
                            });
                        };

                        socket.on('message', config.onmessage);
                    },
                    onRemoteStream: function(htmlElement) {
                        videosContainer.appendChild(htmlElement);
                        rotateInCircle(htmlElement);
                    },
                    onRoomFound: function(room) {
                        document.getElementById("subTitle").innerHTML = "<strong>您正準備觀看 " + room.roomName + " 的直播</strong>";
                        var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
                        if (alreadyExist) return;

                        if (typeof roomsList === 'undefined') roomsList = document.body;

                        var tr = document.createElement('tr');
                        tr.innerHTML = '<td><strong>' + room.roomName + '</strong>和他的小夥伴們正在運動!</td>' +
                            '<td><button class="join">&nbsp;Join&nbsp;</button></td>';
                        roomsList.appendChild(tr);
 
                        var joinRoomButton = tr.querySelector('.join');
                        joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                        joinRoomButton.setAttribute('data-roomToken', room.broadcaster);
                        joinRoomButton.onclick = function() {
                        	document.getElementById("CustomCardheader").className = "CustomCardheader text-white btn-success";
                        	document.getElementById("webSocket-submit").className = "g2";
                        	document.getElementById("subTitle").innerHTML = "<strong2>您正在觀看 " + room.roomName + " 的直播</strong2>";
                        	document.getElementById('broadcast-name').value = room.roomName;
                            videosContainer.className = "videosContainer";                   	
                        	document.getElementById("slider").src = "images/tenor.gif";
                        	document.getElementById("WebRTC-count").innerHTML = "";
                            this.disabled = true;
//                          this.style.display = 'none';
                            
                            var broadcaster = this.getAttribute('data-broadcaster');
                            var roomToken = this.getAttribute('data-roomToken');
                            broadcastUI.joinRoom({
                                roomToken: roomToken,
                                joinUser: broadcaster
                            });
                            hideUnnecessaryStuff2();
                        };
                    },
                    onNewParticipant: function(numberOfViewers) {
                        document.title = 'Viewers: ' + numberOfViewers;
                        document.getElementById("WebRTC-count").innerHTML  = "WebRTC 累計觀看人數"+" "+numberOfViewers;
                        document.getElementById("lsViewNum").value  = numberOfViewers;
                    },
                    onReady: function() {
                        console.log('now you can open or join rooms');
                    }
                };

                function setupNewBroadcastButtonClickHandler() {
                	document.getElementById("subTitle").innerHTML = "<strong>${hostID}: 您正在直播</strong>"
                    $('#record').show();
  	                $('#download').show();
                    DetectRTC.load(function() {
                        captureUserMedia(function() {
                            var shared = 'video';
                            if (window.option == 'Only Audio') {
                                shared = 'audio';
                            }
                            if (window.option == 'Screen') {
                                shared = 'screen';
                            }

                            broadcastUI.createRoom({
                                roomName: (document.getElementById('broadcast-name') || { }).value || 'Anonymous',
                                isAudio: shared === 'audio'
                            });
                        });
                        hideUnnecessaryStuff();
                    });
                }

                function captureUserMedia(callback) {
                    var constraints = null;
                    window.option = broadcastingOption ? broadcastingOption.value : '';
                    if (option === 'Only Audio') {
                        constraints = {
                            audio: true,
                            video: false
                        };

                        if(DetectRTC.hasMicrophone !== true) {
                            alert('DetectRTC library is unable to find microphone; maybe you denied microphone access once and it is still denied or maybe microphone device is not attached to your system or another app is using same microphone.');
                        }
                    }
                    if (option === 'Screen') {
                        var video_constraints = {
                            mandatory: {
                                chromeMediaSource: 'screen'
                            },
                            optional: []
                        };
                        constraints = {
                            audio: false,
                            video: video_constraints
                        };

                        if(DetectRTC.isScreenCapturingSupported !== true) {
                           alert('DetectRTC library is unable to find screen capturing support. You MUST run chrome with command line flag "chrome --enable-usermedia-screen-capturing"');
                        }
                    }

                    if (option != 'Only Audio' && option != 'Screen' && DetectRTC.hasWebcam !== true) {
                        alert('DetectRTC library is unable to find webcam; maybe you denied webcam access once and it is still denied or maybe webcam device is not attached to your system or another app is using same webcam.');
                    }

                    var htmlElement = document.createElement(option === 'Only Audio' ? 'audio' : 'video');

                    htmlElement.muted = true;
                    htmlElement.volume = 0;

                    try {
                        htmlElement.setAttributeNode(document.createAttribute('autoplay'));
                        htmlElement.setAttributeNode(document.createAttribute('playsinline'));
                        htmlElement.setAttributeNode(document.createAttribute('controls'));
                    } catch (e) {
                        htmlElement.setAttribute('autoplay', true);
                        htmlElement.setAttribute('playsinline', true);
                        htmlElement.setAttribute('controls', true);
                    }
var mediaElement = getHTMLMediaElement(htmlElement, {
buttons: ['record-video']
});
                    var mediaConfig = {
                        video: htmlElement,
                        onsuccess: function(stream) {
                            config.attachStream = stream;
                            
                            videosContainer.appendChild(mediaElement);
                            rotateInCircle(htmlElement);
                            
                            callback && callback();
                        },
                        onerror: function() {
                            if (option === 'Only Audio') alert('unable to get access to your microphone');
                            else if (option === 'Screen') {
                                if (location.protocol === 'http:') alert('Please test this WebRTC experiment on HTTPS.');
                                else alert('Screen capturing is either denied or not supported. Are you enabled flag: "Enable screen capture support in getUserMedia"?');
                            } else alert('unable to get access to your webcam');
                        }
                    };
                    if (constraints) mediaConfig.constraints = constraints;
                    getUserMedia(mediaConfig);
                }

                var broadcastUI = broadcast(config);

                /* UI specific */
                var videosContainer = document.getElementById('videos-container') || document.body;
                var setupNewBroadcast = document.getElementById('setup-new-broadcast');
                var roomsList = document.getElementById('rooms-list');

                var broadcastingOption = document.getElementById('broadcasting-option');

                if (setupNewBroadcast) setupNewBroadcast.onclick = setupNewBroadcastButtonClickHandler;

                function hideUnnecessaryStuff() {
                	connect();

                    var visibleElements = document.getElementsByClassName('visible'),
                        length = visibleElements.length;
                    for (var i = 0; i < length; i++) {
                        visibleElements[i].style.display = 'inline';
                    }
                }
                
                function hideUnnecessaryStuff2() {
                	connect();
                }

                function rotateInCircle(video) {
                    video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
                    setTimeout(function() {
                        video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
                    }, 1000);
  	                document.querySelector('button#record').disabled = false;
                	document.getElementById("session1").className = "experiment2";
                 }
                (function() {
                    var uniqueToken = document.getElementById('unique-token');
                    if (uniqueToken)
                        if (location.hash.length > 2) uniqueToken.parentNode.parentNode.parentNode.innerHTML = '<div class="share"><h2>&nbsp;<i class="fa fa-hand-o-right fa-2x"></i><a href="' + location.href + '" target="_blank"><b>由此分享此直播間的鏈接 </b></a></h2></div>';
//                         else uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + (Math.random() * new Date().getTime()).toString(36).toUpperCase().replace( /\./g , '-');//URL網址生產
                        else uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + ('LIV00007');//URL網址生產
                })();
                
                

            </script>

<!-- =============================================以下為錄製、下載、與上傳============================================= -->
<script>
        'use strict';
        const mediaSource = new MediaSource();
        mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
        let mediaRecorder;
        let recordedBlobs;  //錄製成功的Blob
        let sourceBuffer;

        const errorMsgElement = document.querySelector('span#errorMsg'); 
        const recordedVideo = document.querySelector('video#recorded');
        const recordButton = document.querySelector('button#record');
        recordButton.addEventListener('click', () => {
          if (recordButton.textContent === '開始錄影') {
adjustControls();
volumeControl.style.opacity = 1;
recordVideo.className = 'control record-video';
            startRecording();
          } else {
recordVideo.className = recordVideo.className.replace('record-video', 'stop-recording-video selected');
            stopRecording();
            recordButton.textContent = '開始錄影';
volumeControl.style.opacity = 1;
            playButton.disabled = false;
            //downloadButton.disabled = false;
          }
        });
 
        const playButton = document.querySelector('button#play');
        playButton.addEventListener('click', () => {
          const superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
          recordedVideo.src = null;
          recordedVideo.srcObject = null;
          recordedVideo.src = window.URL.createObjectURL(superBuffer);
          recordedVideo.controls = true;
          recordedVideo.play();
        });

        const downloadButton = document.querySelector('button#download');
        downloadButton.addEventListener('click', () => {
              document.querySelector('button#record').disabled = false;
              document.querySelector('button#download').disabled = true;
              const blob = new Blob(recordedBlobs, {type: 'video/webm'});	 
        	  var xhr = new XMLHttpRequest();
         	  xhr.open('POST', '<%=request.getContextPath()%>/Update_StreamServlet', true);
        	  xhr.onload = function(e) { console.log("loaded"); };
        	  xhr.onreadystatechange = function(){
        	      console.log("state: " + xhr.readyState);
        	  };
        	  // Listen to the upload progress.
        	  xhr.upload.onprogress = function(e) { console.log("uploading..."); };
        	  xhr.setRequestHeader("Content-Type", "video/webm");
        	  xhr.send(blob);
        	  swal(
            		  '你已儲存影片！',
            		  '可以去直播管理 listAllStream.jsp 確認',
            		  'success'
            	  )
volumeControl.style.opacity = 0;        
        });

        function handleSourceOpen(event) {
          console.log('MediaSource opened');
          sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
          console.log('Source buffer: ', sourceBuffer);
        }

        function handleDataAvailable(event) {
          if (event.data && event.data.size > 0) {
            recordedBlobs.push(event.data);
          }
        }

        function startRecording() {
          recordedBlobs = [];
          let options = {mimeType: 'video/webm;codecs=vp9'};
          if (!MediaRecorder.isTypeSupported(options.mimeType)) {
            console.error(`${options.mimeType} is not Supported`);
            errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
            options = {mimeType: 'video/webm;codecs=vp8'};
            if (!MediaRecorder.isTypeSupported(options.mimeType)) {
              console.error(`${options.mimeType} is not Supported`);
              errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
              options = {mimeType: 'video/webm'};
              if (!MediaRecorder.isTypeSupported(options.mimeType)) {
                console.error(`${options.mimeType} is not Supported`);
                errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
                options = {mimeType: ''};
              }
            }
          }

          try {
            mediaRecorder = new MediaRecorder(window.stream, options);
          } catch (e) {
            console.error('Exception while creating MediaRecorder:', e);
            errorMsgElement.innerHTML = `Exception while creating MediaRecorder: ${JSON.stringify(e)}`;
            return;
          }

          console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
          recordButton.textContent = '結束錄影';
          playButton.disabled = true;
          downloadButton.disabled = true;
          mediaRecorder.onstop = (event) => {
            console.log('Recorder stopped: ', event);
          };
          mediaRecorder.ondataavailable = handleDataAvailable;
          mediaRecorder.start(10); // collect 10ms of data
          console.log('MediaRecorder started', mediaRecorder);
        }
        function stopRecording() {
          mediaRecorder.stop();
          console.log('Recorded Blobs: ', recordedBlobs);

        	 $.ajax({
        		 type: "POST",
        		 url: "<%=request.getContextPath()%>/InsertOrDelete_StreamServlet",
        		 data: creatQueryString($(this).val(), ""),
        		 dataType: "json",
        		 success: function (data){
        			aelrt("成功送資料庫囉");
        	     },
                 error: function(){
                	    swal(
                		  '您已完成錄影',
                		  '記得要按儲存影片',
                		  'success'
                		);
                	    downloadButton.disabled = false;
volumeControl.style.opacity = 1;
recordVideo.className = recordVideo.className.replace('stop-recording-video selected', 'stop-recording-video2 selected');
                 }
             })
             
             function creatQueryString(paramGrade, paramClass){
                    document.querySelector('button#record').disabled = true;       		 	
        		    var hostID=$("#hostID").val();
        		 	var lsViewNum=$("#lsViewNum").val();
        			var queryString= {"action":"insert", "hostID":hostID, "lsViewNum":lsViewNum};
        			return queryString;
        	 }

        }

        function handleSuccess(stream) {
          recordButton.disabled = false;
          downloadButton.disabled = true;
          console.log('getUserMedia() got stream:', stream);
          window.stream = stream;

        }

        async function init(constraints) {
            const stream = await navigator.mediaDevices.getUserMedia(constraints);
            handleSuccess(stream);
        }

        document.querySelector('#setup-new-broadcast').addEventListener('click', async () => {
          const hasEchoCancellation = document.querySelector('#echoCancellation').checked;
          const constraints = {
            audio: {
              echoCancellation: {exact: hasEchoCancellation}
            },
            video: {
              width: 1280, height: 720
            }
          };
          console.log('Using media constraints:', constraints);
          await init(constraints);
        });
</script>

<!-- =============================================以下為webSocket聊天室============================================= -->
<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;   <%--http上線請使用https , webSocket請使用wss--%>
	var webSocket;
	
	function connect() {
		var rtcroomName = document.getElementById('broadcast-name').value;
		alert(rtcroomName);
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL+"/"+rtcroomName);
		document.getElementById('broadcasting-option').style.display = 'none';
        document.getElementById('broadcast-name').style.display = 'none';
        document.getElementById('setup-new-broadcast').style.display = 'none';
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
		  if (event.data.indexOf('count=') == 0) {
			document.getElementById("WebSocket-count").innerHTML  = "目前在線人數"+" "+event.data.substring(6);
		  } else {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        var showCount = jsonObj.showCount;
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		  }
		};

		webSocket.onclose = function(event) {
			var hostID = document.getElementById("messagesArea");
		     var jsonObj = {"hostID" : userName, "message" : message};
		        webSocket.send(JSON.stringify(jsonObj));
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : document.getElementById("userName").value, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}
    
</script>
</html>
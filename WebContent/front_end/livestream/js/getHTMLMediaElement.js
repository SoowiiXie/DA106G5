var volumeControl;
var recordVideo;
function getHTMLMediaElement(mediaElement, config) {
	config = config || {};

	var buttons = config.buttons || [ 'record-video' ];
	buttons.has = function(element) {
		return buttons.indexOf(element) !== -1;
	};

	config.toggle = config.toggle || [];
	config.toggle.has = function(element) {
		return config.toggle.indexOf(element) !== -1;
	};

	var mediaElementContainer = document.createElement('div');
	mediaElementContainer.className = 'media-container';

	volumeControl = document.createElement('div');
	volumeControl.className = 'volume-control';

	if (buttons.has('record-video')) {
		recordVideo = document.createElement('div');
		recordVideo.className = 'control record-video';
		volumeControl.appendChild(recordVideo);
		videosContainer.appendChild(volumeControl);
		
        recordVideo.onclick = function() {
        	if (recordVideo.className.indexOf('record-video') != -1) {
        		recordVideo.className = recordVideo.className.replace('record-video', 'stop-recording-video selected');
                stopRecording();
                recordButton.textContent = '開始錄影';
                volumeControl.style.opacity = 1;
                playButton.disabled = false;
        	} 
		};
	}

	var mediaBox = document.createElement('div');
	mediaBox.className = 'media-box';
	mediaElementContainer.appendChild(mediaBox);

	mediaBox.appendChild(mediaElement);
	
	mediaElementContainer.style.width =  '100%';
	
//	mediaElementContainer.onmouseenter = mediaElementContainer.onmousedown = function() {
//		adjustControls();
//		volumeControl.style.opacity = 1;
//	};

//	mediaElementContainer.onmouseleave = function() {
//		volumeControl.style.opacity = 0;
//	};
	
	adjustControls();
	volumeControl.style.opacity = 0;
	
	return mediaElementContainer;

}

function adjustControls() {
	recordVideo.style.width = '100%';
	recordVideo.style.height = recordVideo.style.width;
	volumeControl.style.marginLeft = '5%';
	volumeControl.style.marginRight = '5%';
	volumeControl.style.marginTop = '-8%';
}
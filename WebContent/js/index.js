$(document).ready(function(){
			$('.thumbBtn').click(function(){
				 var thumbImg = $(this);
				 $.ajax({
					 type: "GET",
					 url: "<%=request.getContextPath()%>/thumbAjaxResponse.do",
					 data: {"action":"insert", "rcd_no":$(this).siblings('.rcd_no').val(), "mb_id":$(this).siblings('.mb_id').val()},
					 dataType: "json",
					 success: function (data){
						 thumbImg.parent().next().text(data);
						 if ($(".thumbBtn").attr("src")=="<%= request.getContextPath() %>/img/thumb.png"){
						 	$(".thumbBtn").attr("src", "<%= request.getContextPath() %>/img/thumbColor.png");
						 }
						 else{
							$(".thumbBtn").attr("src", "<%= request.getContextPath() %>/img/thumb.png");
						 }
				     },
		             error: function(){alert("AJAX-thumbBtn發生錯誤囉!")}
		         });
			});
			 
			$('.meTooBtn').click(function(){
				 var meTooImg = $(this);
				 $.ajax({
					 type: "GET",
					 url: "<%=request.getContextPath()%>/meTooAjaxResponse.do",
						 data: {"action":"insert", "rcd_no":$(this).siblings('.rcd_no').val(), "mb_id":$(this).siblings('.mb_id').val()},
						 dataType: "json",
						 success: function (data){
							 meTooImg.parent().next().text(data);
							 if ($(".meTooBtn").attr("src")=="<%= request.getContextPath() %>/img/ya.png"){
							 	$(".meTooBtn").attr("src", "<%= request.getContextPath() %>/img/yaColor.png");
							 }
							 else{
								$(".meTooBtn").attr("src", "<%= request.getContextPath() %>/img/ya.png");
							 }
					     },
			             error: function(){alert("AJAX-thumbBtn發生錯誤囉!")}
			         });
			});
				 
			$('.cmtBtn').click(function(){
				 $(this).parents().siblings('.cmtDiv').toggle(function(){
// 						    $(this).animate({height:400},200);
// 						  },function(){
// 						    $(this).animate({height:10},200);
						height: 'toggle'
					  });
				 });
				 
			 $('.oneCmtDiv').hover(function(){
				 $(this).find('.flagBtn').css("display","");
				 $(this).find('.garbageBtn').css("display","");
					 },function(){
						 $(this).find('.flagBtn').css("display","none");
						 $(this).find('.garbageBtn').css("display","none");
				 });
				 
			 //燈箱共用參數
			 var fblightbox = $('#fblightbox');
			 fblightbox.css({'margin-left':'-' + (fblightbox.width()/2) + 'px' , 'margin-top' : '-' + (fblightbox.height()/2)+'px'});
				 
			 //檢舉和留言的燈箱
			 var cmtNrpt = $('.cmtNrpt');
			 $('.flagBtn').click(function(){
				 $.ajax({
					 type: "GET",
					 url: "<%=request.getContextPath()%>/cmt/cmt.do",
					 data: {"action":"ajaxGetOne4Update", "cmt_no":$(this).siblings('.cmt_no').val()},
					 dataType: "json",
					 success: function (data){
// 						 $("#cmt_contentFB").val(data.cmt_content);
						 $("#cmt_noFB").val(data.cmt_no);
					     $("#cmt_statusFB").val(data.cmt_status);
						 $("#cmt_timeFB").val(data.cmt_time);
						 $("#mb_idFB").val(data.mb_id);
						 $("#rcd_noFB").val(data.rcd_no);
						 $('.overlay').fadeIn();
						 cmtNrpt.fadeIn();
				 	 },					
				 error: function(){alert("AJAX-flagBtn發生錯誤囉!")}
		 		 });
			 });
			 
			 //天氣的燈箱
			 var wth_loc = $('.wth_loc');
			 $('.wth_loc_btn').click(function(){
				 $.ajax({
					 type: "GET",
					 url: "<%=request.getContextPath()%>/weather/weather.do",
					 data: {"action":"ajaxGetByWeatherPlace", "weather_place":$(this).val()},
					 dataType: "json",
					 success: function (data){
// 						 $("#cmt_contentFB").val(data.cmt_content);
						 $("#cmt_noFB").val(data.cmt_no);
						 $("#cmt_statusFB").val(data.cmt_status);
						 $("#cmt_timeFB").val(data.cmt_time);
						 $("#mb_idFB").val(data.mb_id);
						 $("#rcd_noFB").val(data.rcd_no);
						 $('.overlay').fadeIn();
						 cmtNrpt.fadeIn();
					 },					
					 error: function(){alert("AJAX-flagBtn發生錯誤囉!")}
				 });
			 });
				 
			 //訊息的燈箱
			 var msgLightBox = $('.msgLightBox');
			 $('.msgRead').click(function(){
			 	$.ajax({
					type: "GET",
					url: "<%=request.getContextPath()%>/msg/msg.do",
						 data: {"action":"ajaxGetOne4Read", "cmt_no":$(this).siblings('.cmt_no').val()},
						 dataType: "json",
						 success: function (data){
// 					   		 $("#cmt_contentFB").val(data.cmt_content);
							 $("#cmt_noFB").val(data.cmt_no);
							 $("#cmt_statusFB").val(data.cmt_status);
							 $("#cmt_timeFB").val(data.cmt_time);
							 $("#mb_idFB").val(data.mb_id);
							 $("#rcd_noFB").val(data.rcd_no);
							 $('.overlay').fadeIn();
							 msgLightBox.fadeIn();
					 	 },					
					 error: function(){alert("AJAX-flagBtn發生錯誤囉!")}
			 		});
			 });
				 
			 //讓燈箱共用關閉按鈕
			 $("#close").click(function() {
				  $('.overlay').fadeOut();
				  fblightbox.fadeOut();
			 });
			 
			 $(".overlay").click(function() {
				  $('.overlay').fadeOut();
				  fblightbox.fadeOut();
			 });
		});
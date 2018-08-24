	
	function GetRequest() {  
	   var url = location.search; //获取url中"?"符后的字串  
	   var theRequest = new Object();  
	   if (url.indexOf("?") != -1) {  
	      var str = url.substr(1); 
	      strs = BASE64.decode(str).split("&");  
	      for(var i = 0; i < strs.length; i ++) {  
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
	      }  
	   }  
	   return theRequest;  
	}
	function	getSockJS(){
    	var	SockJS;
    	$.ajax({
    		type: "GET",
    		async:false,
    		url: "/sockjs",
    		dataType: "text",
    		success: function(data) {
    			SockJS=data
    		}
    	})
    	/*console.log("获取到的SOCKJS"+SockJS);*/
    	return	SockJS;
    }
    function	getWeSocket(){
    	var	wesocket;
    	$.ajax({
    		type: "GET",
    		async:false,
    		url: "/wesocket",
    		dataType: "text",
    		success: function(data) {
    			wesocket=data
    		}
    	})
    	/*console.log("获取到的WEBSOCKET"+wesocket);*/
    	return	wesocket;
    }
    
    function	get_login_user(token){
    	$.ajax({
    		type: "POST",
    		async:false,
    		url: "/js/userChat/get_user_info",
    		data:{
    			token:token
    		},
    		dataType: "json",
    		success: function(data) {
    			photoUrl	=	data.data.photoUrl;
    		},
    		error:function(){console.log('get_login_user')},
    	})
    }
    function	get_talking(petUserName){
    	var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/chatrooms/get_talking",
			data: {
				petUserName:petUserName
			},
			dataType: "json",
			success: function(data) {
				//console.log(data)
				res	=	data
			},
			error:function(){console.log('get_talking')},
		});
    	return	res;
    }
    //发送消息并返回结果
    function	sendMsg(content){
    	var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/messageses/sendMsg",
			data: {
				formUser: room, 
				toUser : seat,
				postMessages : content
			},
			dataType: "json",
			success: function(data) {
				console.log("消息发送")
				res	=	data;
			},
			error:function(){console.log('发送消息并返回结果')},
		});
    	return	res;
    }
    function	sendImg(images){
    	var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/messageses/sendImg",
			data: {
				formUser: room, 
				toUser : seat,
				postMessages : images
			},
			dataType: "json",
			success: function(data) {
				res	=	data;
			},
			error:function(){console.log('发送图片')},
		});
    	return	res;
    }
    function	customerSendImg(images){
    	var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/messageses/sendImg",
			data: {
				formUser: seat, 
				toUser : tousername,
				postMessages : images
			},
			dataType: "json",
			success: function(data) {
				console.log("发送图片")
				res	=	data;
			},
			error:function(){console.log('customerSendImg')},
		});
    	return	res;
    }
    /* 生成当前时间 */
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }
    function add0(m){return m<10?'0'+m:m }
    function format(shijianchuo)
    {
    /*shijianchuo是整数，否则要parseInt转换*/
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
    }
	function	alertInfo(content){
		layer.open({
			  title: '提示',
			  content: content
			}); 
	}
	function	alertPrompt(content){
		layer.msg('<p style="color: #FFB800;">'+content+'</p>', {icon: 6}); 
	}
	
	/*下拉刷新*/
	const start = new Date(new Date(new Date().toLocaleDateString()).getTime());
	var	thatday	=	start.getTime();
	var day =  new Date().getDate();
	var _element = document.getElementById('chatBox-content-demo'),
    _refreshText = document.querySelector('.show-sk'),
    _startPos = 0,
    _transitionHeight = 0;
    (function(window) {
        _element.addEventListener('touchstart', function(e) {
            //console.log('初始位置：', e.touches[0].pageY);
            _startPos = e.touches[0].pageY;
            _element.style.position = 'relative';
            _element.style.transition = 'transform 0s';
        }, false);

        _element.addEventListener('touchmove', function(e) {
            //console.log('当前位置：', e.touches[0].pageY);
            _transitionHeight = e.touches[0].pageY - _startPos;

            if (_transitionHeight > 0 && _transitionHeight < 60) {
                _refreshText.innerHTML = '<div class="sk-chasing-dots"><div class="sk-child sk-dot1"></div><div class="sk-child sk-dot2"></div></div>';
                _element.style.transform = 'translateY('+_transitionHeight+'px)';
                if (_transitionHeight > 55) {
                    _refreshText.innerHTML = '<div class="sk-double-bounce"><div class="sk-child sk-double-bounce1"></div><div class="sk-child sk-double-bounce2"></div></div>';
                }
            }
        }, false);
        _element.addEventListener('touchend', function(e) {
            _element.style.transition = 'transform 0.5s ease 1s';
            _element.style.transform = 'translateY(0px)';
            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
            refresh();
        }, false);
    })(window);
    
    function refresh(){
    	//这里是后台数据
    	$.ajax({
			type: "POST",
			async:false,
			url: "js/messageses/messageHistory",
			data: {
				userName: room, 
				thatDay : thatday,
			},
			dataType: "json",
			success: function(data) {
				thatday	=	thatday-(86400000);
				var	array	=	data.data;
				if(array.length>0){
					for(var	i=0;i<array.length;i++){
						if(array[i].formUser==room){
							if(array[i].messageType=='Text'){
								printFormUserMessage(array[i].postMessages,format(array[i].sendTime))
								if(i==array.length-1){_refreshText.innerHTML ='';}
								else{
						            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
								}
							}else
							if(array[i].messageType=="Picture"){
								printFormUserImage(array[i].postMessages,format(array[i].sendTime))
								if(i==array.length-1){_refreshText.innerHTML ='';}
								else{
						            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
								}
							}
							
						}else{
							if(array[i].messageType=='Text'){
								printToUserMessage(array[i].postMessages,format(array[i].sendTime))
								if(i==array.length-1){_refreshText.innerHTML ='';}
								else{
						            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
								}
							}else
							if(array[i].messageType=="Picture"){
								printToUserImage(array[i].postMessages,format(array[i].sendTime))
								if(i==array.length-1){_refreshText.innerHTML ='';}
								else{
						            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
								}
							}
						}
					}
				}
				else{ 
					_refreshText.innerHTML ='';
				}
			},
			error:function(){console.log('refresh')},
		});
        
    }
	function	printFormUserMessage(str,date) {
		var	textContent	=	'<div class="clearfloat">'
						+	'<div class="author-name">'
						+	'<small class="chat-date">'
						+	date
						+	'</small>'
						+	'</div>'
						+	'<div class="right">'
		            	+	'<div class="chat-message">'
		            	+	str
		            	+	'</div>'
		            	+	'<div class="chat-avatars"><img src="'
		            	+	photoUrl
		            	+	'" alt="头像"></div>'
		            	+	'</div>'
		            	+	'</div>';
        $(".chatBox-content-demo").append(textContent);
		refresh_bottom()
	}
	function	printFormUserImage(images,date){
		var	result	=	'<div class="clearfloat"><div class="author-name"><small class="chat-date">'
					+	date
					+	'</small> </div> <div class="right"> <div class="chat-message"><img src="'
					+	images
					+	'"></div> <div class="chat-avatars"><img src="'
					+	photoUrl
					+	'" alt="头像"></div> </div> </div>';
		$(".chatBox-content-demo").append(result);
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
	};

	function	printToUserMessage(str,date) {
		var	kefumessage	=	'<div class="clearfloat">'
						+	'<div class="author-name">'
						+	'<small class="chat-date">'
						+	date
						+	'</small>'
						+	'</div>'
			            +	'<div class="left">'
			            +	'<div class="chat-avatars"><img src="./layui/images/img/tu2.png" alt="头像"></div>'
			            +	'<div class="chat-message">'
			            +	str
			            +	'</div>'
			            +	'</div>'
			            +	'</div>';
		$(".chatBox-content-demo").append(kefumessage);
		refresh_bottom()
	}
	function	printToUserImage(images,date){
		var	result	=	'<div class="clearfloat"><div class="author-name"><small class="chat-date">'
					+	date
					+	'</small></div><div class="left"><div class="chat-avatars"><img src="'
					+	'./userchat/img/icon01.png'
					+	'" alt="头像"></div><div class="chat-message"><img src="'
					+	images
					+	'" alt=""></div></div></div>';
		$(".chatBox-content-demo").append(result);
		//聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
	}
	$('#back').click(function() {
		history.back(-1);
	});
	function	forPlat(platForm){
		   if(platForm=='android'){
			   $("#back").remove();
		   }else if(platForm=='apple'){
			   $("#back i").remove();
		   }else{
			   
		   }
	   }
    function screenFuc() {
        var topHeight = $(".chatBox-head").innerHeight();//聊天头部高度
        //屏幕小于768px时候,布局change
        var winWidth = $(window).innerWidth();
        if (winWidth <= 768) {
            var totalHeight = $(window).height(); //页面整体高度
            //$(".chatBox-info").css("height", totalHeight - topHeight);
            //var infoHeight = $(".chatBox-info").innerHeight();//聊天头部以下高度
            //中间内容高度
            $(".chatBox-content").css("height",totalHeight - topHeight-46);
            $(".chatBox-content-demo").css("height", totalHeight - topHeight-46);

            //$(".chatBox-list").css("height", totalHeight - topHeight);
            $(".chatBox-kuang").css("height", totalHeight - topHeight);
            $(".div-textarea").css("width", winWidth - 106);
        } else {
            //$(".chatBox-info").css("height", 495);
            $(".chatBox-content").css("height", 448);
            $(".chatBox-content-demo").css("height", 448);
            //$(".chatBox-list").css("height", 495);
            $(".chatBox-kuang").css("height", 495);
            $(".div-textarea").css("width", 260);
        }
    }
	function	refresh_bottom(){
    	/* 发送后清空输入框 */
    	$(".div-textarea").html("");
    	/* 聊天框默认最底部 */
    	$(document).ready(function () {
        	$("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
    	});           	
    }
	$("#chatPeople").click(function(){
		
		var	name	=	$(this).context.innerText;
		var	w	=	2*document.documentElement.clientWidth/3+'px';
		var	h	=	document.documentElement.clientHeight/3+'px';
		/*layer.open({
			closeBtn: 0,
			shadeClose: true,
			title: '评价',
			type: 2
		    ,content: ['./layui/iframe/avgrate.html?name='+BASE64.encode(name)+'&petUser='+BASE64.encode(room), 'no']
		    ,area: [w, h]
		    ,fixed: false //不固定
		    ,maxmin: false
		    ,btn: ['提交']
		    });*/
		var	number	=	3;
		layui.use(['rate'], function(){
			  var rate = layui.rate;
			  rate.render({
			    elem: '#test2'
			    ,value: number //初始值
			    ,text: true //开启文本
			    ,choose: function(value){
			    	number	=	value;
			    	console.log(number);
			        if(value > 4) alertPrompt( '么么哒' );
			      }
			  });
		});
		$('#content').val("");
		$('#avgrate').show();
		layer.open({
				shadeClose: true,
				scrollbar: false,
				title: '评价',
				type: 1,
				area: [w, h],
				content: $('#avgrate'),
				end: function(){
					$('#avgrate').hide();
					}
			});
		$("#username").html('<legend>'+name+'</legend>')
		var	iw	=	2*document.documentElement.clientWidth/8+'px';
		var	ih	=	0.2*document.documentElement.clientHeight/8+'px';
		$("#logo").attr("src","/js/comments/getAvg?seat="+name+"");
		$("#logo").css({width:iw,height:ih});
		$("#tjpj").click(function(){
			$.ajax({
				type: "POST",
				async:true,
				url: "/js/comments/score",
				data: {
					seat: name,
					fromPetUser: room,
					content: $("#content").val(),
					star: number
				},
				dataType: "text",
				success: function(data) {
					$('#avgrate').hide();
					layer.closeAll();
				},
				error:function(){console.log("#tjpj.click")},
			});
		})
	});
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
    	console.log("获取到的SOCKJS"+SockJS);
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
    	console.log("获取到的WEBSOCKET"+wesocket);
    	return	wesocket;
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
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
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
				console.log("消息发送")
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
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
				console.log("消息发送")
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
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
    //shijianchuo是整数，否则要parseInt转换
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
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.open({
				  title: '提示',
				  content: content
				});  
			}); 
	}
	function	alertPrompt(content){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  /*layer.open({
				  type: 3
				  ,title: '<i class="layui-icon layui-icon-speaker"></i>'
				  ,content: content
				  ,time:  3000
				  ,anim: 1
				  ,closeBtn: 0
				  ,offset: ['100px', '190px']
				});*/  
			  layer.msg('<p style="color: #FFB800;">'+content+'</p>', {icon: 6}); 
			});
	}
	
	//下拉刷新
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
		    	console.log(day)
				var	array	=	data.data;
				console.log(array)
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
			error:function(){alert('eoo')},
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
		            	+	'<div class="chat-avatars"><img src="./userchat/img/icon02.png" alt="头像"></div>'
		            	+	'</div>'
		            	+	'</div>'
        $(".chatBox-content-demo").append(textContent);
        //发送后清空输入框
        $(".div-textarea").html("");
        //聊天框默认最底部
        $(document).ready(function () {
        	$("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
	}
	function	printFormUserImage(images,date){
		var	result	=	'<div class="clearfloat"><div class="author-name"><small class="chat-date">'
					+	date
					+	'</small> </div> <div class="right"> <div class="chat-message"><img src="'
					+	images
					+	'"></div> <div class="chat-avatars"><img src="./userchat/img/icon01.png" alt="头像"></div> </div> </div>'
		$(".chatBox-content-demo").append(result)
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
			            +	'</div>'
		$(".chatBox-content-demo").append(kefumessage)
		//发送后清空输入框
		$(".div-textarea").html("");
		//聊天框默认最底部
		$(document).ready(function () {
		$("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
		});
	}
	function	printToUserImage(images,date){
		var	result	=	'<div class="clearfloat"><div class="author-name"><small class="chat-date">'
					+	date
					+	'</small></div><div class="left"><div class="chat-avatars"><img src="'
					+	'./userchat/img/icon01.png'
					+	'" alt="头像"></div><div class="chat-message"><img src="'
					+	images
					+	'" alt=""></div></div></div>'
		$(".chatBox-content-demo").append(result);
		//聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
	}
	
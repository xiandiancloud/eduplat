/**
 * 解析url，來整合，不是很理想，安全問題待加強
 * 用戶可以隨意更改，這兒簡單處理判斷下
 */
function parseUrl(debug)
{
	/*if (true)
	{
		var name = "飞燕";
    	name = encodeURI(name);
    	//alert("name --- "+name);
    	var url = "http://localhost:8080/carplat/car.html?ui=0&ur=1&un="+name+"&up=http://localhost:8080/carplat/images/1.jpg";
    	$("#demo").attr("href",url);
		return url;
	}*/
	var array = new Array();
	var href = location.href;
	var a = href.split("?");
	if (a.length < 2)
	{
		//location.href = "404.html";
		array[0] = "admin";
		return array;
	}
	var b = a[1];
	var c = b.split("&");
	if (c.length > 4)
	{		
		array[0] = c[0]+"&"+c[1]+"&"+c[2]+"&"+c[3];
	}
	else
	{
		array[0] = b;
	}			
	if (c.length < 4)
	{
		array[0] = "admin";
		return array;
	}
	var role = c[1].split("=")[1];
	array[1] = role;
	var uname = c[2].split("=")[1];	
	//var username = decodeURI(uname);	
	var username = decodeURI(decodeURI(uname));
	array[2] = username;
	//var uimg = c[3].split("=")[1];
	//array[3] = uimg;
	var uimg = c[3].split("=")[1];
	array[3] = decodeURIComponent(decodeURI(uimg));
	return array;
}
function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
} 
/**
 * 简单判断是否是网络url
 * @param str
 * @returns {Boolean}
 */
function isWebUrl(str)
{
	if ( str == "" ) return false;
	var pre = str.substring(0,4);
	if (pre == "http")
	{
		return true;	
	}
	else
	{
		return false;
	}
}
function createUploader(id,id2) { 
	var uploader = new qq.FineUploader({ 
	element: document.getElementById(id), 
	request: { 
	endpoint: 'upload.action' 
	}, 
	text: { 
	uploadButton: '<button class="btn btn-warning"><i class="icon-upload"></i>上传</button>' 
	}, 
	validation:{
		allowedExtensions: ['jpeg', 'jpg', 'gif', 'png'],
		sizeLimit: 20480000
	},
	template: 
	'<div class="qq-uploader">' + 
	'<pre class="qq-upload-drop-area"><span>{dragZoneText}</span></pre>' + 
	'<div class="qq-upload-button btn" style="width: auto;">{uploadButtonText}</div>' + 
	'<span class="qq-drop-processing"><span>{dropProcessingText}</span>'+ 
	'<span class="qq-drop-processing-spinner"></span></span>' + 
	'<ul class="qq-upload-list" style="margin-top: 10px; text-align: center;display:none"></ul>' + 
	'</div>', 
	classes: { 
	success: 'alert alert-success', 
	fail: 'alert alert-error' 
	}, 
	callbacks:{
		onComplete: function(id,  fileName,  responseJSON){		
			if (responseJSON.success == "true")
			{
				//$(id2).attr("value",responseJSON.path);
				document.getElementById(id2).value = responseJSON.path;
			}
		}
	},
	debug: true 
	}); 
}
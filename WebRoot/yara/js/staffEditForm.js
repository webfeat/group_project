(function($){
	if(window.data){
		console.log(data.data);
		$("#name").text(data.data.staffname);
		console.log(data.data.staffname);
		$("#email").val(data.data.email);
		console.log(data.data.email);
		$("#phone").val(data.data.phone);
		console.log(data.data.phone);
		$("#trainName").val(data.data.train.trainname);
		$("#staffId").val(data.data.staffid);
	}else{
		
	}
})(jQuery)

var update = function(){
	//更新方法
	console.log($("#staffId").val());
		$.ajax({
			url:'/group_project/temp/StaffServlet',
			type:"post",
			data:{staffId:$("#staffId").val(),operate:'update',email:$("#email").val(),phone:$("#phone").val()},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				window.gotoHtml("/group_project/yara/view/tables/mans.html",data);
			},
			error:function(error){
				alert(error);
			}
		});
	}


var back = function(){
	window.gotoHtml("/group_project/yara/view/tables/mans.html",data);
}
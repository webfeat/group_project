(function($){
	if(window.data){
		console.log(window.data);
		$("#name").text(data.data.applicant);
		$("#email").text(data.data.stafemail);
		$("#phone").text(data.data.staffphone);
		$("#trainName").text(data.data.trainname);
		console.log(data.data.staffapplyid);
		$("#staffApplyId").val(data.data.staffapplyid);
	}else{
		
	}
})(jQuery)

var submit = function(){
	console.log($("#staffApplyId").find("option:selected").text());
	//更新方法
	console.log($("#staffId").val());
		$.ajax({
			url:'/group_project/temp/StaffApplyServlet',
			type:"post",
			data:{staffApplyId:$("#staffApplyId").val(),operate:'deal',email:$("#email").val(),phone:$("#phone").val(),comment:$("#comment").val()},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				window.gotoHtml("/group_project/yara/view/tables/staffapplys.html",data);
			},
			error:function(error){
				alert(error);
			}
		});
	}

var back = function(){
	window.gotoHtml("/group_project/yara/view/tables/staffapplys.html",data);
}
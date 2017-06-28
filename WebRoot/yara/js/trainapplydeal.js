(function($){
	if(window.data){
		console.log("数据");
		console.log( window.data);
		$("#applicant").text(data.trainapply.applicant);
		$("#trainname").text(data.trainapply.trainname);
		$("#managename").text(data.trainapply.managename);
		$("#managephone").text(data.trainapply.managephone);
		$("#email").text(data.trainapply.email);
		$("#trainapplyid").val(data.trainapply.trainapplyid);
		console.log("申请ID" + $("#trainapplyid").val());
	}else{
		
	}
})(jQuery)

var submit = function(){
	console.log($("#trainapplyid").find("option:selected").text());
	//更新方法
	console.log($("#managename"));
		$.ajax({
			url:'/group_project/temp/TrainapplyVerify',
			type:"post",
			data:{trainApplyId:$("#trainapplyid").val(),operate:'deal',email:$("#email").val(),managephone:$("#managephone").val(),comment:$("#comment").val()},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				window.gotoHtml("/group_project/yara/view/tables/trainapplydeal.html",data);
			},
			error:function(error){
				alert(error);
			}
		});
	}

var back = function(){
	window.gotoHtml("/group_project/yara/view/tables/trainapplydeal.html",data);
}
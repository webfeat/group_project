var submit = () =>{
	$.ajax({
		url:'/group_project/temp/LoginAction',
		type:"post",
		data:{userName:$("#userName").val(),password:$("#password").val()},
		dataType: "json",
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success:function(data){
			if(data.state == false){
				alert(data.errorMessage);
			}else{
				self.location='/group_project/yara/view/index.html';
			}
		},
		error:function(error){
			console.log(error);
		}
	});
} 

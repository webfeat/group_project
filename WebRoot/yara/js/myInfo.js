
var getMyInfo = function(){
	var loginUser = localStorage.getItem("loginUser");
	loginuser = JSON.parse(loginUser);
	if(loginuser.staffname == '管理员'){
		$("#joined").hide();
	}else{
		//$("#trainName").text(loginuser.train.trainname);
	}
	console.log(loginuser);
	$("#staffName").text(loginuser.staffname);
	$("#phone").text(loginuser.phone);
	$("#email").text(loginuser.email);
}

getMyInfo();
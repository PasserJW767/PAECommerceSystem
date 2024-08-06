//非空验证
function delTrim(x){
	while(x.length>0 && x.charAt(0)==' ')
	 	x = x.substring(1,x.length);
	while(x.length>0&&x.charAt(x.length-1)==' ')
		x = x.substring(0,x.length-1);
	return x;
}
//非空验证
function isNull(elem,message){
	var va=delTrim(elem.value);
	if(va==""){
		alert(message);
		elem.focus();
		return false;
	}
	return true;
}
//验证账号
function validateUserName(){
	var userName=document.forms[0].username.value.charAt(0);
	var exp=/^[a-z0-9]+$/;
	if(isNull(document.forms[0].username,"请输入账号")){//验证非空
		//验证首字符
		if(userName>='a'&&userName<='z'||userName>='A'&&userName<='Z'){
		}
		else{
			alert("账号首字符必须是字母！");
			document.forms[0].username.focus();
			return false;
		}
		if(!exp.test(document.forms[0].username.value)){
			alert("账号必须是字母或数字！");
			document.forms[0].username.focus();
			return false;
		}
		return true;
	}else{
		return false;
	}
}
//验证密码
function validatePassword(){
	var Password=document.forms[0].userpwd.value;
	var exp=/^[a-z0-9]+$/;
	if(isNull(document.forms[0].userpwd,"请输入密码")){//验证非空
		if(document.forms[0].userpwd.value.length<=8){
			alert("密码大于8位");
			document.forms[0].userpwd.focus();
			return false;
		}else{
			if(exp.test(Password)){
				alert("密码需要有字母和数字之外的字符!");
				document.forms[0].userpwd.focus();
				return false;
			}
		}
	}else{
		return false;
	}
	if(document.forms[0].userpwd.value!=document.forms[0].surePassword.value){
		alert("两次密码不一样!");
		document.forms[0].userpwd.focus();
		return false;
	}
	return true;
}
/*
//检查性别
function checkGender(){
	var gender=document.forms[0].gender.value;
	var exp= /^['男'|'女']$/ ;
if(isNull(document.forms[0].gender,"请输入正确性别")){//验证非空
	if(!exp.test(gender)){
		alert("输入正确的性别");
		document.forms[0].gender.focus();
		return false;
	}
	return true;
	}else{
		return false;
	}
}*/

function checkTel(){
	var exp=/^([1]\d{10}|([\(（]?0[0-9]{2,3}[）\)]?[-]?)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?)$/;
	if(isNull(document.forms[0].telephone,"请输入电话号码")){//验证非空
		if(!exp.test(document.forms[0].telephone.value)){
			alert("电话号码格式错误！");
			document.forms[0].telephone.focus();
			return false;
		}
		return true;
	}else{
		return false;
	}
}

//验证身份证号
function checkIdentityCard(){
	        var identityCard = document.forms[0].identitycard.value;
            var exp = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
        if(isNull(document.forms[0].identitycard,"请输入身份证号")){//验证非空
		    if(!exp.test(identityCard)){
                alert("请输入正确的身份证号");
			    document.forms[0].identitycard.focus();
		    	return false;
		    }
            return true;
       }
        else
        	{
	          return false;
            }
}
/*
//验证Email
function checkEmail(){
	var exp=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(isNull(document.forms[0].mail,"请输入Email")){//验证非空
		if(!exp.test(document.forms[0].mail.value)){
			alert("Email格式错误！");
			document.forms[0].mail.focus();
			return false;
		}
		return true;
	}else{
		return false;
	}
}*/

//提交按钮
function go(){
	if(validateUserName()&&validatePassword()&&checkIdentityCard()&&checkTel()){
		document.forms[0].submit();
		return true;
	}
	return false;
}
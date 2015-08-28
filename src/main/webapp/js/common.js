//出现replace is not a function 时,检查引用对象的类型,不是str尝试用''+xx的方法转化为str　　
String.prototype.trim=function(){
　　    return this.replace(/(^\s*)|(\s*$)/g, "");
　　 }
　　 String.prototype.ltrim=function(){
　　    return this.replace(/(^\s*)/g,"");
　　 }
　　 String.prototype.rtrim=function(){
　　    return this.replace(/(\s*$)/g,"");
　　 }

String.prototype.startWith=function(str){    
  var reg=new RegExp("^"+str);    
  return reg.test(this);       
} 
 
String.prototype.endWith=function(str){    
  var reg=new RegExp(str+"$");    
  return reg.test(this);       
} 

//validate obj

/**
 * 非空数字
 * @param {Object} obj
 * @return {TypeName} 
 */
function checkInt(obj){
	var reg = /^[0-9]+$/;
	if(obj!=""&&!reg.test(obj)){
		return false;
	}
	return true;
} 
//validate $(obj)
function check_notEmpty(obj,obj_name){
	if(obj.val().trim()!=""){
		
	}
}
function check_double(obj,obj_name){  
    var reg = /^[0-9]+(\.[0-9]+)?$/;  
    if(obj.val()!=""&&!reg.test(obj.val())){  
        alert(obj_name+'所填必须为有效的双精度数字');  
        obj.focus();  
        return false;  
    }  
    return true;
}  

function checkNumber(obj,obj_name){  
    var reg = /^[0-9]+$/;  
    if(obj.val()!=""&&!reg.test(obj.val())){  
        alert(obj_name+'只能输入数字！');  
        obj.focus();  
        return false;  
    }  
    return true;
}

function le(small,big,smallName,bigName){
	var s = parseInt(small.val());
	if(small.val()==""){
		s=0;
	}
	if(s<=parseInt(big.val())){
		return true;
	}else{
		alert(smallName+'不应大于'+bigName);
		small.focus();
		return false;
	}
}

function neq(from,to,fromName,toName){
	if(from.val()!=to.val()){
		return true;
	}else{
		alert(fromName+'不应等于'+toName);
		from.focus();
		return false;
	}
}
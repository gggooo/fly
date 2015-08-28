var hkey_root,hkey_path,hkey_key
hkey_root="HKEY_CURRENT_USER"
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"
//设置网页打印的页眉页脚为空
function pagesetup_null(){
	try{
		var RegWsh = new ActiveXObject("WScript.Shell")
		hkey_key="header"
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
		hkey_key="footer"
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
	}catch(e){}
}

function prt(){
	pagesetup_null();
	document.all.WebBrowser.ExecWB(7,1);
}
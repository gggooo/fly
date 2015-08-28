package common.act;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import common.util.DateTimeUtils;

public class AttachmentAction extends BaseAction{


	private String fileName;
	
	public String download() throws Exception {
		//中文乱码处理
		try {
			setFileName(URLDecoder.decode(new String(getFileName().getBytes("ISO-8859-1"),"utf-8"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			return "resourceNotFonnd";
		}
		String docPath=request.getSession().getServletContext().getRealPath("upload");
		File resourceFile=new File(docPath+"\\"+getFileName());
		if(resourceFile.exists()){
			//这里添加下载权限设置.
			return SUCCESS;
		}else{
			return "resourceNotFonnd";
		}
	}
	
	public String getDownloadFileName() {
	   String downFileName = fileName;
	   try {
	    downFileName = new String(downFileName.getBytes(), "ISO8859-1");
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return downFileName;
	}

	public InputStream getDownloadFile(){
		return ServletActionContext.getServletContext().getResourceAsStream("/upload/"+fileName);

	}
	
	public static String saveOrderAttachment(File upload, HttpServletRequest request, String uploadFileName) throws Exception{
		if(upload!=null){	//上传附件
				String docPath=request.getSession().getServletContext().getRealPath("upload");
				File fileForUpload=new File(docPath + "\\" + uploadFileName);
				if(fileForUpload.exists()){
					String nowtime=DateTimeUtils.formatDate(new Date(System.currentTimeMillis()), "yyMMddHHmmss"); 
					int lastNum=uploadFileName.lastIndexOf(".");
					String newName=uploadFileName.substring(0,lastNum)+"@"+nowtime+uploadFileName.substring(lastNum,uploadFileName.length());
					uploadFileName=newName;
				}
				FileOutputStream fos = new FileOutputStream(docPath + "\\" + uploadFileName);
				FileInputStream fis = new FileInputStream(upload);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0){
					fos.write(buffer , 0 , len);
				}
			return uploadFileName; //将所有附件的相对路径累加字符处保存到数据库当中
		}else{
			return "";	
		}	
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
}

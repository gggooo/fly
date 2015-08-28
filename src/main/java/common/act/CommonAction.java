package common.act;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

import common.dao.BaseDao;

/**
 * 指标数据操作
 * 
 * @author DELL
 * 
 */
public class CommonAction extends BaseAction {
	private Logger log = Logger.getLogger(CommonAction.class);
	private List<NameValuePair> nl;
	private BaseDao baseDao;
	private String bodyHtml;
	private String navHtml;
	
	private String exportUrl;
	
	private InputStream excelStream;
	private String fileName;
	
	
	public String download() throws FileNotFoundException{
		File file = new File(exportUrl);
		fileName = file.getName();
		excelStream = new FileInputStream(file);
		return SUCCESS;
	}

	public String noProcess(){
		
		return SUCCESS;
	}
	
	public String home(){
		
		return SUCCESS;
	}
	
	/**
	 * 用于远程重启服务器
	 * @return
	 * @throws IOException
	 */
	public String restart() throws IOException{
		try {
			Process pro = Runtime.getRuntime().exec("cmd.exe /k start d:\\restartTomcat.bat");
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					pro.getInputStream()));
//			String msg = null;
//			while ((msg = br.readLine()) != null) {
//				System.out.println(msg);
//			}
			log.info("重启成功");
		} catch (IOException e) {
			log.error("重启失败");
		}
		return SUCCESS;
	}
	

	public void setNl(List<NameValuePair> nl) {
		this.nl = nl;
	}

	public List<NameValuePair> getNl() {
		return nl;
	}


	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getNavHtml() {
		return navHtml;
	}
	public void setNavHtml(String navHtml) {
		this.navHtml = navHtml;
	}
	
	
}

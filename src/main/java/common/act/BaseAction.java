package common.act;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cfg.vo.Member;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import common.util.Constant;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements Preparable {

	protected HttpServletRequest request;
	protected String remoteAddr;
	protected Timestamp now;
	protected String loginId;
	public void prepare() throws Exception {
		clearErrorsAndMessages();
		request = ServletActionContext.getRequest();
		remoteAddr = request.getRemoteAddr();
		now = new Timestamp(System.currentTimeMillis());
//		loginId = (Member)request.getSession().getAttribute(Constant.SESSION_MEMBER);
	}
	
}

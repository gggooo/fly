package common.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cfg.vo.PerfProperty;

import common.dao.BaseDao;

/**
 * 全局初始化。
 * 
 * @author qianxiaowei
 * 
 */
public class ContextInitialize implements ServletContextListener {

	private Logger log = Logger.getLogger(ContextInitialize.class);
	private BaseDao baseDao;

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {

		ServletContext servletContext = arg0.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		baseDao = (BaseDao) ctx.getBean("baseDao");

		
//		final boolean ifServer = Util.ifServer(baseDao);
		
		//吧cfg_perf_property放入PPS，方便查找
		//所以改了cfg_perf_property数据表需要重启服务器
		DetachedCriteria dc = DetachedCriteria.forClass(PerfProperty.class);
		Constant.PPS=baseDao.find(dc);
	}

	

	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}

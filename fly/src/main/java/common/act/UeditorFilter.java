package common.act;

import java.io.IOException;
 
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
 
import org.apache.struts2.dispatcher.FilterDispatcher;

/**
 * 在struts2应用中使用ueditor富文本编辑器上传图片或者附件时，即使配置好了上传路径信息，也会出现"未找到上传文件"的错误提示，出先该问题的原因是：在配置struts过滤器，过滤路径设置/*方式时，由于struts2框架默认使用apache的Commons FileUpload组件和内建的FileUploadInterceptor拦截器实现上传，会将request文件域封装到action中一个File类型的属性中，并删除request中的文件域，因此会上传文件失败。 
 * @author ggg
 *
 */
public class UeditorFilter extends FilterDispatcher{
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getRequestURI();        
//        System.out.println(url);        
        if (url.contains("/ueditor/jsp/")) {            
            chain.doFilter(req, res);        
        }else{            
            super.doFilter(req, res, chain);        
        }
    }
}
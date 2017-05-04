/**  
 * Project Name:lenovo_czsys  
 * File Name:sessionFilter.java  
 * Package Name:com.sys.filter  
 * Date:2016-4-23下午8:12:41  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.filter;  

import java.io.IOException;  

import javax.servlet.FilterChain;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.http.HttpStatus;  
import org.springframework.web.filter.OncePerRequestFilter;  

import com.general.util.UUIDGenerator;
import com.sys.comm.SessionKeyContent;

/** 
 * @ClassName: sessionFilter 
 * @Description: 登录过滤器
 * @author lm
 * @date 2016-4-23 下午8:12:41 
 * 
 * 
 */
public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 不过滤的uri  
        String[] notFilter =  
            new String[] {"/resource","/security/login","/security/checkLogin", "/security/code", "/security/loginout","/security/interface"};  
          
        // 请求的uri  
        String uri = request.getRequestURI();  
        // 是否过滤  
        boolean doFilter = true;  
        for (String s : notFilter)  
        {  
            if (uri.indexOf(s) != -1){   
                doFilter = false;  
                break;  
            }  
        }  
          
        if (doFilter)  
        {  
            // 执行过滤  
            // 从session中获取登录者实体  
            Object obj = request.getSession().getAttribute(SessionKeyContent.SESSION_KEY_OBJ_USER_BEAN);  
            if (null == obj)  
            {  
                boolean isAjaxRequest = isAjaxRequest(request);  
                if (isAjaxRequest)  
                {  
                    response.setCharacterEncoding("UTF-8");  
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");  
                    return ;  
                }  
                response.sendRedirect("/security/login?session="+UUIDGenerator.UUID());  
                return;  
            }  
            else  
            {  
                // 如果session中存在登录者实体，则继续  
                filterChain.doFilter(request, response);  
            }  
        }  
        else  
        {  
            // 如果不执行过滤，则继续  
            filterChain.doFilter(request, response);  
        }  
		   
		
	}
	 /** 判断是否为Ajax请求  
     * <功能详细描述> 
     * @param request 
     * @return 是true, 否false  
     * @see [类、类#方法、类#成员] 
     */  
    public static boolean isAjaxRequest(HttpServletRequest request){  
        String header = request.getHeader("X-Requested-With");   
        if (header != null && "XMLHttpRequest".equals(header))   
            return true;   
        else   
            return false;    
    }  
}
  

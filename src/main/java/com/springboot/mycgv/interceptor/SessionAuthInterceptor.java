package com.springboot.mycgv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springboot.mycgv.dto.SessionDto;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class SessionAuthInterceptor extends HandlerInterceptorAdapter {

	/**checking before go to Controller
	 * if there's session, return true
	 * or return false
	 *
	 * 어떨때 preHandle 메소드를 진행을 할 것인지 리스트 생성
	 * 
	 * 
	 * Spring Legacy에서는 servlet-context.xml에서 진행
	 *	sessionAuthInterceptor 
	 * 	<beans:bean id="sessionAuthInterceptor"
	 * 		 class="com.mycgv_jsp.interceptor.SessionAuthInterceptor">
	 * 	</beans:bean> 
	 *
	 * 	sessionAuthInterceptor check request
	 * 	<interceptors>
	 * 		<interceptor>
	 * 			<mapping path="/mypage.do"/>
	 * 			<beans:ref bean="sessionAuthInterceptor"></beans:ref>
	 * 		</interceptor>
	 * 		<interceptor>
	 * 			<mapping path="/admin*"/>
	 * 			<beans:ref bean="sessionAuthInterceptor"></beans:ref>
	 * 		</interceptor>
	 *
	 * 	</interceptors>
	 *
	 *  SpringBoot는 SessionConfig.java 생성해서 진행 interceptor/SessionConfig
	 * 
	 * 
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler)
								throws Exception {

		HttpSession session = request.getSession(); /**checking whether there's session or not*/
		SessionDto svo = (SessionDto)session.getAttribute("svo");
		
		if(svo == null) {
			response.sendRedirect("/login");
			return false;
		}
			return true;
	}
}









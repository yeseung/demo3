package com.gongdaeoppa.demo3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gongdaeoppa.demo3.service.ArticleService;
import com.gongdaeoppa.demo3.service.BoardService;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NeedLoginInterceptor implements HandlerInterceptor {
	
	
	private final Rq rq;
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		//System.out.println("로그인 필요!!");
		
		
		//Rq rq = (Rq) req.getAttribute("rq");

		if (!rq.isLogined()) {
			//rq.printHistoryBackJs("로그인 후 이용해주세요____.");
			//rq.printReplaceJs("로그인 후 이용해주세요.__________", "../member/login");
			
			//String afterLoginUri = rq.getEncodedCurrentUri();
			//String afterLoginUri = rq.getAfterLoginUri();
			//rq.printReplaceJs("로그인 후 ___이용해주세요.", "../member/login?afterLoginUri=" + afterLoginUri);
			
			
			
			if ( rq.isAjax() ) {
				resp.setContentType("application/json; charset=UTF-8");
				resp.getWriter().append("{\"resultCode\":\"F-A\",\"msg\":\"로그인 후 이용해주세요.\"}");
			}
			else {
				String afterLoginUri = rq.getAfterLoginUri();
				rq.printReplaceJs("로____그인 후 이용해주세요.", "/usr/member/login?afterLoginUri=" + afterLoginUri);
			}
			
			
			
			
			
			
			
			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
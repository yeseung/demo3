package com.gongdaeoppa.demo3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gongdaeoppa.demo3.service.ArticleService;
import com.gongdaeoppa.demo3.service.BoardService;
import com.gongdaeoppa.demo3.service.MemberService;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	
	
	//@Autowired
	//private MemberService memberService;
	
	private final Rq rq;
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		System.out.println("________________실행되니?++++++++++++++");
		
		//Rq rq = new Rq(req, resp, memberService);
		//req.setAttribute("rq", rq);
		//System.out.println("++++++++++++ = " + rq.getLoginedMember());
		
		
		//rq.initOnBeforeActionInterceptor();
		req.setAttribute("rq", rq);
		

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
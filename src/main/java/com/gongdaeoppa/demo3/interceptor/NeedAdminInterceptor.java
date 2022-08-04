package com.gongdaeoppa.demo3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NeedAdminInterceptor implements HandlerInterceptor {


	private final Rq rq;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		if (!rq.isAdmin()) {
			if ( rq.isAjax() ) {
				resp.setContentType("application/json; charset=UTF-8");
				rq.print("{\"resultCode\":\"F-A\",\"msg\":\"____권한이 없습니다.\"}");
			}
			else {
				String afterLoginUri = rq.getAfterLoginUri();
				rq.printHistoryBackJs("__관리자로 로그인 후 다시 이용해주세요.");
			}

			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
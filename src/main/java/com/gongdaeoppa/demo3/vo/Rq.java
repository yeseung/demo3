package com.gongdaeoppa.demo3.vo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.gongdaeoppa.demo3.service.MemberService;
import com.gongdaeoppa.demo3.util.Ut;

import lombok.Getter;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	
	
	@Getter
	private boolean isAjax;
	
	
	
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	private HttpSession session;
	
	
	
	private Map<String, String> paramMap;
	
	

	//public Rq(HttpServletRequest req) {
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
	
		this.session = req.getSession();
		//HttpSession httpSession = req.getSession();
		
		
		
		
		
		
		paramMap = Ut.getParamMap(req);
		
		
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		Member loginedMember = null;

		//if (httpSession.getAttribute("loginedMemberId") != null) {
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			//loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			
			
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		
		this.loginedMember = loginedMember;
		//System.out.println("=============" + loginedMember);
		
		
		//this.req.setAttribute("rq", this);
		
		
		
		
		
		
		
		String requestUri = req.getRequestURI();

		// 해당 요청이 ajax 요청인지 아닌지 체크
		boolean isAjax = requestUri.endsWith("Ajax");

		if (isAjax == false) {
			if (paramMap.containsKey("ajax") && paramMap.get("ajax").equals("Y")) {
				isAjax = true;
			}
			else if (paramMap.containsKey("isAjax") && paramMap.get("isAjax").equals("Y")) {
				isAjax = true;
			}
		}

		if (isAjax == false) {
			if (requestUri.contains("/get")) {
				isAjax = true;
			}
		}

		this.isAjax = isAjax;
		
		
		
		
		
		
		
		
		
	}
	
	
	
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	
	
	
	
	
	
	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");

		/*println("<script>");
		if (!Ut.empty(msg)) {
			println("alert('" + msg + "');");
		}
		println("history.back();");
		println("</script>");*/
		
		print(Ut.jsHistoryBack(msg));
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void println(String str) {
		print(str + "\n");
	}



	public void logout() {
		session.removeAttribute("loginedMemberId");
		
	}


	public String historyBackJsOnView(String msg) {
		req.setAttribute("msg", msg + "qqqqqqqqqqqqqqq");
		req.setAttribute("historyBack", true);
		return "common/js";
	}



	public String jsHistoryBack(String msg) {
		return Ut.jsHistoryBack(msg);
	}

	public String jsReplace(String msg, String uri) {
		return Ut.jsReplace(msg, uri);
	}


	
	
	
	
	
	
	
	
	
	

	// 이 메서드는 Rq 객체가 자연스럽게 생성되도록 유도하는 역할을 한다.
	// 지우면 안되고,
	// 편의를 위해 BeforeActionInterceptor 에서 꼭 호출해줘야 한다.
	//public void initOnBeforeActionInterceptor() {}
	
	
	
	
	public void runA() {
		System.out.println("A 호출됨!");
		runB();
	}

	public void runB() {
		System.out.println("B 호출됨!");	
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            currentUri += "?" + queryString;
        }

        return currentUri;
	}

	public String getEncodedCurrentUri() {
		return Ut.getUriEncoded(getCurrentUri());
	}
	
	
	
	
	
	
	
	
	
	public boolean isNotLogined() {
		return !isLogined;
	}




	
	public void printReplaceJs(String msg, String url) {
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsReplace(msg, url));
	}



	
	public String getLoginUri() {
		return "/usr/member/login?afterLoginUri=" + getAfterLoginUri();
	}

	public String getAfterLoginUri() {
		
		
		String requestUri = req.getRequestURI();

		switch (requestUri) {
		case "/usr/member/login":
		case "/usr/member/join":
		case "/usr/member/findLoginId":
		case "/usr/member/findLoginPw":
			//return Ut.getUriEncoded(paramMap.get("afterLoginUri"));
			return Ut.getUriEncoded(Ut.getStrAttr(paramMap, "afterLoginUri", ""));
		}
		
		
		return getEncodedCurrentUri();
	}
	
	
	
	
	
	
	
	
	
	
	public String getLogoutUri() {
		String requestUri = req.getRequestURI();

		// 필요하다면 활성화
		/*
		switch (requestUri) {
		case "/usr/article/write":
			return "";
		}
		*/

		return "/usr/member/doLogout?afterLogoutUri=" + getAfterLogoutUri();
	}
	
	public String getAfterLogoutUri() {
		return getEncodedCurrentUri();
	}
	
	
	
	
	
	
	
	
	
	
	public String getArticleDetailUriFromArticleList(Article article) {
		return "../article/detail?id=" + article.getId() + "&listUri=" + getEncodedCurrentUri();
	}
	
	
	
	
	
	
	
	
	
	public String historyBackJsOnView(String resultCode, String msg) {
		req.setAttribute("msg", String.format("[%s] %s", resultCode, msg));
		req.setAttribute("historyBack", true);
		return "common/js";
	}

	public String jsHistoryBack(String resultCode, String msg) {
		msg = String.format("[%s] %s", resultCode, msg);
		return Ut.jsHistoryBack(msg);
	}
	
	public String getJoinUri() {
		return "/usr/member/join?afterLoginUri=" + getAfterLoginUri();
	}




	
	public boolean isAdmin() {
		if ( isLogined == false ) {
			return false;
		}

		return loginedMember.isAdmin();
	}
	
	
}
package com.gongdaeoppa.demo3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gongdaeoppa.demo3.service.ArticleService;
import com.gongdaeoppa.demo3.service.BoardService;
import com.gongdaeoppa.demo3.service.MemberService;
import com.gongdaeoppa.demo3.util.Ut;
import com.gongdaeoppa.demo3.vo.Member;
import com.gongdaeoppa.demo3.vo.ResultData;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class UsrMemberController {

	
	private final MemberService memberService;

	/*public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}*/
	
	private final Rq rq;

	/*@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		
		
		/*if (loginId == null || loginId.trim().length() == 0) {
			return "loginId(을)를 입력해주세요.";
		}*

		if (Ut.empty(loginId)) {
			return "loginId(을)를 입력해주세요.";
		}

		if (Ut.empty(loginPw)) {
			return "loginPw(을)를 입력해주세요.";
		}

		if (Ut.empty(name)) {
			return "name(을)를 입력해주세요.";
		}

		if (Ut.empty(nickname)) {
			return "nickname(을)를 입력해주세요.";
		}

		if (Ut.empty(cellphoneNo)) {
			return "cellphoneNo(을)를 입력해주세요.";
		}

		if (Ut.empty(email)) {
			return "email(을)를 입력해주세요.";
		}
		
		
		
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		/*if (id == -1) {
			return "해당 로그인아이디는 이미 사용중입니다.";
		}*
		
		
		if (id == -1) {
			return Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId);
		}

		if (id == -2) {
			return Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email);
		}

		
		
		Member member = memberService.getMemberById(id);

		return member;
	}
	*/
	
	
	
	
	
	
	/*@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession httpSession, String loginId, String loginPw) {
		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("F-5", "이미 로그인되었습니다.");
		}

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return ResultData.from("F-3", "존재하지 않은 로그인아이디 입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}

		httpSession.setAttribute("loginedMemberId", member.getId());

		return ResultData.from("S-1", Ut.f("%s님 환영합니다.", member.getNickname()));
	}*/
	
	

	
	
	/*@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession httpSession) {
		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("S-1", "이미 로그아웃 상태입니다.");
		}

		httpSession.removeAttribute("loginedMemberId");

		return ResultData.from("S-2", "로그아웃 되었습니다.");
	}*/
	
	
	
	
	
	
	
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	//public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
	public String doJoin(
			String loginId, 
			String loginPw, 
			String name, 
			String nickname, 
			String cellphoneNo,
			String email, 
			@RequestParam(defaultValue = "/") String afterLoginUri) {
		if (Ut.empty(loginId)) {
			//return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
			return rq.jsHistoryBack("F-1", "loginId(을)를 입력해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return rq.jsHistoryBack("F-2", "loginPw(을)를 입력해주세요.");
		}

		if (Ut.empty(name)) {
			return rq.jsHistoryBack("F-3", "name(을)를 입력해주세요.");
		}

		if (Ut.empty(nickname)) { 
			return rq.jsHistoryBack("F-4", "nickname(을)를 입력해주세요.");
		}

		if (Ut.empty(cellphoneNo)) {
			return rq.jsHistoryBack("F-5", "cellphoneNo(을)를 입력해주세요.");
		}

		if (Ut.empty(email)) {
			return rq.jsHistoryBack("F-6", "email(을)를 입력해주세요.");
		}
		
		
		
		/*Member oldMember = memberService.getMemberByLoginId(loginId);
		if (oldMember != null) {
			return rq.jsHistoryBack("F-7", String.format("입력하신 로그인아이디(%s)(은)는 이미 사용중입니다.__", loginId));
		}*/
		

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			//return (ResultData)joinRd;
			rq.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}
		
		
		//Member member = memberService.getMemberById((int) joinRd.getData1());
		//Member member = memberService.getMemberById(joinRd.getData1());
		
		//return ResultData.newData(joinRd, "member", member);
		
		String afterJoinUri = "../member/login?afterLoginUri=" + Ut.getUriEncoded(afterLoginUri);

		return rq.jsReplace("회원가입이 완료되었습니다. 로그인 후 이용해주세요.", afterJoinUri);

		
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/usr/member/login")
	//public String showLogin(HttpSession httpSession) {
	public String showLogin() {
		return "usr/member/login";
	}
	
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	//public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
	//public String doLogin(String loginId, String loginPw) {
	public String doLogin(String loginId, String loginPw, @RequestParam(defaultValue = "/") String afterLoginUri) {
		//Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			//return Ut.jsHistoryBack("이미 로그인되었습니다.");
			return rq.jsHistoryBack("이미 로그인되었습니다.");
		}

		if (Ut.empty(loginId)) {
			//return Ut.jsHistoryBack("loginId(을)를 입력해주세요.");
			return rq.jsHistoryBack("loginId(을)를 입력해주세요.");
		}

		if (Ut.empty(loginPw)) {
			//return Ut.jsHistoryBack("loginPw(을)를 입력해주세요.");
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			//return Ut.jsHistoryBack("존재하지 않은 로그인아이디 입니다.");
			return rq.jsHistoryBack("존재하지 않은 로그인아이디 입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			//return Ut.jsHistoryBack("비밀번호가 일치하지 않습니다.");
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		//httpSession.setAttribute("loginedMemberId", member.getId());
		rq.login(member);

		//return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
		return rq.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), afterLoginUri);
	}

	
	
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	//public String doLogout(HttpServletRequest req) {
	/*public String doLogout() {
		//Rq rq = (Rq) req.getAttribute("rq");

		if (!rq.isLogined()) {
			//return Ut.jsHistoryBack("이미 로그아웃 상태입니다.");
			return rq.jsHistoryBack("이미 로그아웃 상태입니다.");
		}

		rq.logout();

		//return Ut.jsReplace("로그아웃 되었습니다.", "/");
		return rq.jsReplace("로그아웃 되었습니다.", "/");
	}*/	
	public String doLogout(
		@RequestParam(defaultValue = "/") String afterLogoutUri) {
	
		rq.logout();

		return rq.jsReplace("로그아웃---- 되었습니다.", afterLogoutUri);
	}
	
	
	
	
	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {
		return "usr/member/myPage";
	}
	
	
	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword() {
		return "usr/member/checkPassword";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping("/usr/member/doCheckPassword")
	@ResponseBody
	public String doCheckPassword(String loginPw, String replaceUri) {
		if (Ut.empty(loginPw)) {
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		if (rq.getLoginedMember().getLoginPw().equals(loginPw) == false) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		
		
		
		
		if ( replaceUri.equals("../member/modify") ) {
			String memberModifyAuthKey = memberService.genMemberModifyAuthKey(rq.getLoginedMemberId());

			replaceUri += "?memberModifyAuthKey=" + memberModifyAuthKey; 
		}
		
		
		
		return rq.jsReplace("", replaceUri);
	}
	
	
	
	
	
	@RequestMapping("/usr/member/modify")
	//public String showModify() {
	public String showModify(String memberModifyAuthKey) {
		if ( Ut.empty(memberModifyAuthKey) ) {
			return rq.historyBackJsOnView("memberModifyAuthKey(이)가 필요합니다.");
		}

		ResultData checkMemberModifyAuthKeyRd = memberService.checkMemberModifyAuthKey(rq.getLoginedMemberId(), memberModifyAuthKey);

		if ( checkMemberModifyAuthKeyRd.isFail() ) {
			return rq.historyBackJsOnView(checkMemberModifyAuthKeyRd.getMsg());
		}
		return "usr/member/modify";
	}
	
	
	
	
	
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	//public String doModify(String loginPw, String name, String nickname, String email, String cellphoneNo) {
	public String doModify(String memberModifyAuthKey, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		if ( Ut.empty(memberModifyAuthKey) ) {
			return rq.jsHistoryBack("memberModifyAuthKey(이)가 필요합니다.");
		}

		ResultData checkMemberModifyAuthKeyRd = memberService.checkMemberModifyAuthKey(rq.getLoginedMemberId(), memberModifyAuthKey);

		if ( checkMemberModifyAuthKeyRd.isFail() ) {
			return rq.jsHistoryBack(checkMemberModifyAuthKeyRd.getMsg());
		}
		
		
		
		
		if (Ut.empty(loginPw)) {
			loginPw = null;
		}

		if (Ut.empty(name)) {
			return rq.jsHistoryBack("name(을)를 입력해주세요.");
		}

		if (Ut.empty(nickname)) {
			return rq.jsHistoryBack("nickname(을)를 입력해주세요.");
		}

		if (Ut.empty(email)) {
			return rq.jsHistoryBack("email(을)를 입력해주세요.");
		}

		if (Ut.empty(cellphoneNo)) {
			return rq.jsHistoryBack("cellphoneNo(을)를 입력해주세요.");
		}

		ResultData modifyRd = memberService.modify(rq.getLoginedMemberId(), loginPw, name, nickname, email, cellphoneNo);

		return rq.jsReplace(modifyRd.getMsg(), "/");
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData getLoginIdDup(String loginId) {
		if (Ut.empty(loginId)) {
			return ResultData.from("F-A1", "loginId를 입력해주세요.");
		}

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return ResultData.from("F-A2", "해당 로그인아이디는 이미 사용중입니다.", "loginId", loginId);
		}

		return ResultData.from("S-1", "사용가능한 로그인아이디 입니다.", "loginId", loginId);
	}
	
	
	
	
	
	
	
	
	
}

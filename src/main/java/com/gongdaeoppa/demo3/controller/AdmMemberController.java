package com.gongdaeoppa.demo3.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gongdaeoppa.demo3.service.ArticleService;
import com.gongdaeoppa.demo3.service.BoardService;
import com.gongdaeoppa.demo3.service.MemberService;
import com.gongdaeoppa.demo3.service.ReactionPointService;
import com.gongdaeoppa.demo3.service.ReplyService;
import com.gongdaeoppa.demo3.util.Ut;
import com.gongdaeoppa.demo3.vo.Member;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdmMemberController {
	private final MemberService memberService;
	private final Rq rq;



	@RequestMapping("/adm/member/list")
	public String showList(Model model, @RequestParam(defaultValue = "0") int authLevel,
			@RequestParam(defaultValue = "loginId,name,nickname") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {
		int membersCount = memberService.getMembersCount(authLevel, searchKeywordTypeCode, searchKeyword);

		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) membersCount / itemsCountInAPage);
		
		List<Member> members = memberService.getForPrintMembers(authLevel, searchKeywordTypeCode, searchKeyword, itemsCountInAPage, page);
		

		model.addAttribute("authLevel", authLevel);
		model.addAttribute("searchKeywordTypeCode", searchKeywordTypeCode);
		model.addAttribute("searchKeyword", searchKeyword);
		
		
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("page", page);
		

		model.addAttribute("membersCount", membersCount);
		model.addAttribute("members", members);

		return "adm/member/list";
	}
	
	
	
	
	
	
	
	@RequestMapping("/adm/member/doDeleteMembers")
    @ResponseBody
    public String doDeleteMembers(@RequestParam(defaultValue = "") String ids, @RequestParam(defaultValue = "/adm/member/list") String replaceUri) {
        List<Integer> memberIds = new ArrayList<>();

        for (String idStr : ids.split(",")) {
            memberIds.add(Integer.parseInt(idStr));
        }

        memberService.deleteMembers(memberIds);

        return Ut.jsReplace("해당 회원들이 삭제되었습니다.", replaceUri);
    }
	
	
	
	
}
package com.gongdaeoppa.demo3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gongdaeoppa.demo3.service.ArticleService;
import com.gongdaeoppa.demo3.service.BoardService;
import com.gongdaeoppa.demo3.service.MemberService;
import com.gongdaeoppa.demo3.service.ReactionPointService;
import com.gongdaeoppa.demo3.service.ReplyService;
import com.gongdaeoppa.demo3.util.Ut;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class UsrHomeController {
	

	private final Rq rq;
	
	
	
	@RequestMapping("/tmp")
	@ResponseBody
	public String tmp() {
		return Ut.jsHistoryBack(Ut.f("%d번 _______게시물이 존재하지 않습니다.", 1234));
	}
	
	
	
	/*@RequestMapping("/usr/home/main")
	@ResponseBody
	public String getString() {
		return "안녕하세요.";
	}*/
	
	
	@RequestMapping("/usr/home/main")
	public String showMain() {
		
		
		//임시
		rq.runA();
		
		
		
		
		
		
		
		return "usr/home/main";
	}

	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}

	
	
	/*private int count;
	
	public UsrHomeController() {
		count = -1;
	}
	

	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕";
	}
	
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반갑습니다.";
	}

	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "또 만나요.";
	}
	
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showMain4() {
		count++;
		return count;
	}
	
	@RequestMapping("/usr/home/main5")
	@ResponseBody
	public String showMain5() {
		count = 0;
		return "count의 값이 0으로 초기화 되었습니다.";
	}
	
	
	
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int getCount() {
		return count;
	}

	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String doSetCount(int count) {
		this.count = count;
		return "count의 값이 " + this.count + "으로 초기화 되었습니다.";

	}
	
	
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("철수나이", 22);
		map.put("영희나이", 21);

		return map;
	}
	
	
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("철수");
		list.add("영희");

		return list;
	}
	
	*/

	

	/*@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article artricle = new Article(1, "제목1");
		return artricle;
	}
	
	
	
	
	
	@RequestMapping("/usr/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		Article artricle1 = new Article(1, "제목1");
		Article artricle2 = new Article(2, "제목2");

		List<Article> list = new ArrayList<>();
		list.add(artricle1);
		list.add(artricle2);

		return list;
	}*/
	
	
	
}









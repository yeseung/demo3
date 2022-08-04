package com.gongdaeoppa.demo3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gongdaeoppa.demo3.service.ArticleService;
import com.gongdaeoppa.demo3.service.BoardService;
import com.gongdaeoppa.demo3.service.ReactionPointService;
import com.gongdaeoppa.demo3.service.ReplyService;
import com.gongdaeoppa.demo3.util.Ut;
import com.gongdaeoppa.demo3.vo.Article;
import com.gongdaeoppa.demo3.vo.Board;
import com.gongdaeoppa.demo3.vo.Reply;
import com.gongdaeoppa.demo3.vo.ResultData;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsrArticleController {
	
	
	
	//@Autowired
	private final ArticleService articleService;
	
	
	private final BoardService boardService;
	
	
	
	private final ReactionPointService reactionPointService;
	
	
	private final Rq rq;
	
	
	
	
	private final ReplyService replyService;
	
	

	/*public UsrArticleController(ArticleService articleService, BoardService boardService) {
		this.articleService = articleService;
		this.boardService = boardService;
	}*/
	
	
	//private int articlesLastId;
	//private List<Article> articles;
	
	
	/*public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}
	

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
			
		}
	}
	
	
	
	
	
	private Article getArticle(int id) {
		for ( Article article : articles ) {
			if ( article.getId() == id ) {
				return article;
			}
		}

		return null;
	}

	private void deleteArticle(int id) {
		Article article = getArticle(id);

		articles.remove(article);
	}
	
	
	
	
	public Article writeArticle(String title, String body) {
		int id = articlesLastId + 1;
		Article article = new Article(id, title, body);

		articles.add(article);
		articlesLastId = id;

		return article;
	}
	
	*/

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	//public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
	//public String doDelete(HttpSession httpSession, int id) {
	//public String doDelete(HttpServletRequest req, int id) {
	public String doDelete(int id) {
		//Rq rq = new Rq(req);
		//Rq rq = (Rq)req.getAttribute("rq");
		
		/*boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {*/
		/*if (rq.isLogined() == false) {
			//return ResultData.from("F-A", "로그인 후 이용해주세요.");
			return Ut.jsHistoryBack("로그인 후 이용해주세요.");
		}*/

		//Article article = articleService.getArticle(id);
		//Article article = articleService.getForPrintArticle(id);
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		
		if ( article == null ) {
			//return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			//return Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return rq.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			//return ResultData.from("F-2", "권한이 없습니다.");
			//return Ut.jsHistoryBack("권한이 없습니다.");
			return rq.jsHistoryBack("권한이 ---없습니다.");
		}
		

		

		articleService.deleteArticle(id);
		

		//return ResultData.from("S-1", Ut.f("%d번 게시물을 삭제하였습니다.", id), "id", id);
		//return Ut.jsHistoryBack(Ut.f("%d번 게시물을 삭제하였습니다.", id));
		//return Ut.jsReplace(Ut.f("%d번 게시물을 삭제하였습니다.____.", id), "/usr/article/list");
		//return Ut.jsReplace(id + "번 게시물을 삭제하였습니다._.", "/usr/article/list");
		return rq.jsReplace(Ut.f("%d번 게시물을 삭제하였습니다.", id), "../article/list");
	}
	
	
	/*
	private void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
	}
	
	*/
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	//public ResultData<Integer> doModify(int id, String title, String body) {
	//public ResultData<Article> doModify(HttpSession httpSession, int id, String title, String body) {
	//public ResultData<Article> doModify(HttpServletRequest req, int id, String title, String body) {
	//public String doModify(HttpServletRequest req, int id, String title, String body) {
	public String doModify(int id, String title, String body) {
		//Rq rq = new Rq(req);
		//Rq rq = (Rq)req.getAttribute("rq");
			
		/*boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {*/
		/*if (rq.isLogined() == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}*/
		
		//Article article = articleService.getArticle(id);
		//Article article = articleService.getForPrintArticle(id);
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if ( article == null ) {
			//ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			//return Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return rq.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		//articleService.modifyArticle(id, title, body);

		//return ResultData.from("S-1", Ut.f("%d번 게시물을 수정하였습니다.", id), id);
		
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if ( actorCanModifyRd.isFail() ) {
			//return actorCanModifyRd;
			//return Ut.jsHistoryBack(actorCanModifyRd.getMsg());
			return rq.jsHistoryBack(actorCanModifyRd.getMsg());
		}

		articleService.modifyArticle(id, title, body);
		
		//return articleService.modifyArticle(id, title, body);
		//return Ut.jsReplace(Ut.f("%d번 글이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
		return rq.jsReplace(Ut.f("%d번 글이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
	}
	
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	//public ResultData<Article> getArticle(int id) {
	//public Object getArticleAction(int id) {
	//public ResultData<Article> getArticle(HttpSession httpSession, int id) {
	//public ResultData<Article> getArticle(HttpServletRequest req, int id) {
	public ResultData<Article> getArticle(int id) {
		//Rq rq = new Rq(req);
		//Rq rq = (Rq)req.getAttribute("rq");
		
		/*boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}*/

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
	
		//Article article = articleService.getArticle(id);
		//Article article = articleService.getForPrintArticle(id);

		if ( article == null ) {
			//return new Article();
			//return id + "번 게시물은 존재하지 않습니다.";
			return ResultData.from("F-1", Ut.f("%d___번 게시물이 존재하지 않습니다.", id));
		}

		//return article;
		return ResultData.from("S-1", Ut.f("%d___번 게시물입니다.", id), "article", article);
	}
	
	
	
	

	/*@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = articleService.writeArticle(title, body);

		return article;
	}*/

	/*@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}*/
	
	
	/*@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();

		return ResultData.from("S-1", "게시물 리스트 입니다.", "articles", articles);
	}*/
	
	
	
	
	/*@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articleService.writeArticle(title, body);

		Article article = articleService.getArticle(id);

		return article;
	}*/
	
	
	/*@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	//public ResultData<Article> doAdd(String title, String body) {
	/*public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {*
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		//Rq rq = new Rq(req);
		Rq rq = (Rq)req.getAttribute("rq");
		
		/*if (rq.isLogined() == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}*
	
	
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return ResultData.from("F-2", "body(을)를 입력해주세요.");
		}

		//ResultData writeArticleRd = articleService.writeArticle(title, body);
		//System.out.println(writeArticleRd.getData1());
		//int id = (int) writeArticleRd.getData1();
		
		
		//ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body);
		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		int id = writeArticleRd.getData1();
		
		
		//Article article = articleService.getArticle(id);
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		//return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
		return ResultData.newData(writeArticleRd, "article", article);

	}*/	
	
	
	
	
	@RequestMapping("/usr/article/list")
	//public String showList(Model model) {
	//public String showList(HttpSession httpSession, Model model) {
	//public String showList(HttpServletRequest req, Model model) {
	//public String showList(HttpServletRequest req, Model model, int boardId) {
		//Rq rq = new Rq(req);
		//Rq rq = (Rq)req.getAttribute("rq");
	//public String showList(Model model, int boardId) {
	/*public String showList(Model model, 
			@RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "1") int page) {*/
	public String showList(Model model,
		@RequestParam(defaultValue = "1") int boardId,
		@RequestParam(defaultValue = "title,body") String searchKeywordTypeCode,
		@RequestParam(defaultValue = "") String searchKeyword,
		@RequestParam(defaultValue = "1") int page) {
		
		Board board = boardService.getBoardById(boardId);
		
		System.out.println("***** - - " + board);
		
		if ( board == null ) {
			return rq.historyBackJsOnView(Ut.f("%d번 게시판은 존재___하지 않습니다.", boardId));
		}
		
		
		
		//int articlesCount = articleService.getArticlesCount(boardId);
		int articlesCount = articleService.getArticlesCount(boardId, searchKeywordTypeCode, searchKeyword);
		System.out.println("###### " + articlesCount + " ######");
		
		
		/*boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}*/

		//List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId());
		//List<Article> articles = articleService.getArticles();
		//List<Article> articles = articleService.getForPrintArticles();
		//List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId(), boardId);
		
		
		int itemsCountInAPage = 10;
		int pagesCount = (int)Math.ceil((double)articlesCount / itemsCountInAPage);
		
		//List<Article> articles = articleService.getForPrintArticles(
		//		rq.getLoginedMemberId(), boardId, itemsCountInAPage, page);
		List<Article> articles = articleService.getForPrintArticles(
				rq.getLoginedMemberId(), 
				boardId,
				searchKeywordTypeCode, 
				searchKeyword, 
				itemsCountInAPage, 
				page);
		
		
		
		model.addAttribute("boardId", boardId);
		model.addAttribute("page", page);
		model.addAttribute("board", board);
		model.addAttribute("pagesCount", pagesCount);
		
		
		model.addAttribute("articlesCount", articlesCount);

		model.addAttribute("articles", articles);
		
		
		
		
		

		return "usr/article/list";
	}
	
	
	
	@RequestMapping("/usr/article/detail")
	//public String showDetail(Model model, int id) {
	//public String showDetail(HttpSession httpSession, Model model, int id) {
	//public String showDetail(HttpServletRequest req, Model model, int id) {
	public String showDetail(Model model, int id) {
		//Rq rq = new Rq(req);
		//Rq rq = (Rq)req.getAttribute("rq");
		
		/*boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}*/
		
		
		
		
		
		/*ResultData<Integer> increaseHitCountRd = articleService.increaseHitCount(id);

		if ( increaseHitCountRd.isFail() ) {
			return rq.historyBackJsOnView(increaseHitCountRd.getMsg());
		}*/
		
		
		
		

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		//Article article = articleService.getArticle(id);
		//Article article = articleService.getForPrintArticle(id);
		
		
		
		
		//220719
		if ( article == null ) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다_________.", id));
		}
		
		

		model.addAttribute("article", article);
		
		
		
		
		List<Reply> replies = replyService.getForPrintReplies(rq.getLoginedMember(), "article", id);
		//int repliesCount = replies.size();
		
		//model.addAttribute("repliesCount", repliesCount);
		
		model.addAttribute("replies", replies);
		
		//220726
		for ( Reply replie : replies ) {
			System.out.println("================" + replie.getBody());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		//boolean actorCanMakeReactionPoint = articleService.actorCanMakeReactionPoint(
		//		rq.getLoginedMemberId(), id);
		
		//boolean actorCanMakeReactionPoint = reactionPointService.actorCanMakeReactionPoint(
		//		rq.getLoginedMemberId(), "article", id).isSuccess();

		//model.addAttribute("actorCanMakeReactionPoint", actorCanMakeReactionPoint);
		
		
		
		ResultData actorCanMakeReactionPointRd = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(), "article", id);
		
		model.addAttribute("actorCanMakeReaction", actorCanMakeReactionPointRd.isSuccess());
		
		if ( actorCanMakeReactionPointRd.getResultCode().equals("F-2") ) {
			int sumReactionPointByMemberId = (int)actorCanMakeReactionPointRd.getData1();
			
			if ( sumReactionPointByMemberId > 0 ) {
				model.addAttribute("actorCanCancelGoodReaction", true);
			}
			else {
				model.addAttribute("actorCanCancelBadReaction", true);
			}
		}
		

		return "usr/article/detail";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping("/usr/article/modify")
	//public String doModify(HttpServletRequest req, int id) {
	//public String showModify(HttpServletRequest req, Model model, int id) {
	public String showModify(Model model, int id) {
		//Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.historyBackJsOnView(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return rq.historyBackJsOnView(actorCanModifyRd.getMsg());
		}
		
		
		model.addAttribute("article", article);
		

		return "usr/article/modify";
	}
	
	
	
	
	
	
	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req, Model model) {
		return "usr/article/write";
	}
	
	
	
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	//public String doWrite(HttpServletRequest req, String title, String body, String replaceUri) {
	public String doWrite(int boardId, String title, String body, String replaceUri) {
		//Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.empty(title)) {
			return rq.jsHistoryBack("title(을)를 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body(을)를 입력해주세요.");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), boardId, title, body);
		int id = writeArticleRd.getData1();

		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}

		return rq.jsReplace(Ut.f("%d번 글이 생성되었습니다.", id), replaceUri);
	}
	
	
	
	
	
	
	@RequestMapping("/usr/article/doIncreaseHitCountRd")
	@ResponseBody
	public ResultData<Integer> doIncreaseHitCountRd(int id) {
		ResultData<Integer> increaseHitCountRd = articleService.increaseHitCount(id);

		if (increaseHitCountRd.isFail()) {
			return increaseHitCountRd;
		}

		return ResultData.newData(increaseHitCountRd, "hitCount", articleService.getArticleHitCount(id));
	}
	
	
	
	
	
	
}




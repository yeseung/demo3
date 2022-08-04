package com.gongdaeoppa.demo3.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.gongdaeoppa.demo3.repository.ArticleRepository;
import com.gongdaeoppa.demo3.util.Ut;
import com.gongdaeoppa.demo3.vo.Article;
import com.gongdaeoppa.demo3.vo.ResultData;

@Service
public class ArticleService {
	
	
	
	
	
	
	
	
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		//articleRepository.makeTestData();
	}
	
	
	

	/*private int articlesLastId;
	private List<Article> articles;

	public ArticleService() {
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
	}*/
	
	
	//public ResultData writeArticle(String title, String body) {
	public ResultData writeArticle(int memberId, int boardId, String title, String body) {
		//articleRepository.writeArticle(title, body);
		articleRepository.writeArticle(memberId, boardId, title, body);
		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), "id", id);
	}
	

	/*public int writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);
		return articleRepository.getLastInsertId(); 
	}*/
	
	/*public void writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}*/

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	/*public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}*/
	
	
	
	public ResultData<Article> modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);

		//Article article = getArticle(id);
		//Article article = getForPrintArticle(id);
		Article article = getForPrintArticle(0, id);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id), "article", article);
	}
	
	
	
	public ResultData actorCanModify(int actorId, Article article) {
		if ( article == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if ( article.getMemberId() != actorId ) {
			return ResultData.from("F-2", "______권한이 없습니다.__");
		}

		return ResultData.from("S-1", "게시물 수정이 가능합니다.");
	}
	
	

	//public List<Article> getArticles() {
	//public List<Article> getForPrintArticles() {
		//return articleRepository.getArticles();
	//}
	//public List<Article> getForPrintArticles(int actorId, int boardId) {
	//public List<Article> getForPrintArticles(int actorId, int boardId, int itemsCountInAPage, int page) {
		//List<Article> articles = articleRepository.getArticles();
		//List<Article> articles = articleRepository.getArticles(boardId);
	public List<Article> getForPrintArticles(
			int actorId, 
			int boardId, 
			String searchKeywordTypeCode, 
			String searchKeyword, 
			int itemsCountInAPage, 
			int page) {
		int limitStart = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;

		//List<Article> articles = articleRepository.getArticles(
		//		boardId, limitStart, limitTake);
		//List<Article> articles = articleRepository.getArticles(
		List<Article> articles = articleRepository.getForPrintArticles(
			boardId, 
			searchKeywordTypeCode, 
			searchKeyword, 
			limitStart, 
			limitTake);
		
		for ( Article article : articles ) {
			updateForPrintData(actorId, article);
		}

		return articles;
	}
	
	
	public Article getForPrintArticle(int actorId, int id) {
		Article article = articleRepository.getForPrintArticle(id);

		updateForPrintData(actorId, article);

		return article;
	}



	/*public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}*/




	/*public Article getForPrintArticle(int id) {
		return articleRepository.getForPrintArticle(id);
	}*/
	
	
	private void updateForPrintData(int actorId, Article article) {
		if ( article == null ) {
			return;
		}

		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
		
		
		
		ResultData actorCanModifyRd = actorCanModify(actorId, article);
		article.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
	}


	
	public ResultData actorCanDelete(int actorId, Article article) {
		if ( article == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if ( article.getMemberId() != actorId ) {
			return ResultData.from("F-2", "___권한이 없습니다.");
		}

		return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
	}




	/*public int getArticlesCount(int boardId) {
		return articleRepository.getArticlesCount(boardId);
	}*/
	
	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {
		return articleRepository.getArticlesCount(boardId, searchKeywordTypeCode, searchKeyword);
	}




	public ResultData<Integer> increaseHitCount(int id) {
		int affectedRowsCount = articleRepository.increaseHitCount(id);

		if (affectedRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.", "affectedRowsCount", affectedRowsCount);
		}

		return ResultData.from("S-1", "조회수가 증가되었습니다.", "affectedRowsCount", affectedRowsCount);
	}






	public int getArticleHitCount(int id) {
		return articleRepository.getArticleHitCount(id);
	}



	
	/*
	
	public boolean actorCanMakeReactionPoint(int actorId, int id) {
		return articleRepository.getSumReactionPointByMemberId(id, actorId) == 0;
	}*/


	
	
	
	
	
	
	public ResultData increaseGoodReactionPoint(int relId) {
		int affectedRowsCount = articleRepository.increaseGoodReactionPoint(relId);

		if (affectedRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.", "affectedRowsCount", affectedRowsCount);
		}

		return ResultData.from("S-1", "좋아요 수가 증가되었습니다.", "affectedRowsCount", affectedRowsCount);
	}

	public ResultData increaseBadReactionPoint(int relId) {
		int affectedRowsCount = articleRepository.increaseBadReactionPoint(relId);

		if (affectedRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.", "affectedRowsCount", affectedRowsCount);
		}

		return ResultData.from("S-1", "싫어요 수가 증가되었습니다.", "affectedRowsCount", affectedRowsCount);
	}


	
	
	
	
	
	
	public ResultData decreaseGoodReactionPoint(int relId) {
		int affectedRowsCount = articleRepository.decreaseGoodReactionPoint(relId);

		if (affectedRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.", "affectedRowsCount", affectedRowsCount);
		}

		return ResultData.from("S-1", "좋아요d 수가 감소되었습니다.", "affectedRowsCount", affectedRowsCount);
	}

	public ResultData decreaseBadReactionPoint(int relId) {
		int affectedRowsCount = articleRepository.decreaseBadReactionPoint(relId);

		if (affectedRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.", "affectedRowsCount", affectedRowsCount);
		}

		return ResultData.from("S-1", "싫어요 수가 감소되었습니다.", "affectedRowsCount", affectedRowsCount);
	}




	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}



}
package com.gongdaeoppa.demo3.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

//import java.util.ArrayList;
//import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gongdaeoppa.demo3.vo.Article;


@Mapper
public interface ArticleRepository {

	//@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
	//public void writeArticle(@Param("title") String title, @Param("body") String body);
	public void writeArticle(
			@Param("memberId") int memberId, 
			@Param("boardId") int boardId, 
			@Param("title") String title, 
			@Param("body") String body);
	
	//public Article writeArticle(String title, String body);

	//@Select("SELECT * FROM article WHERE id = #{id}")
	//public Article getArticle(@Param("id") int id);
	
	
	
	/*@Select(" SELECT A.*, " +
			" M.nickname AS extra__writerName " +
			" FROM article AS A " + 
			" LEFT JOIN `member` AS M " +
			" ON A.memberId = M.id " +
			" WHERE 1 " +
			" AND A.id = #{id} ")*/
	public Article getForPrintArticle(@Param("id") int id);
	
	

	// DELETE FROM article WHERE id = ?
	//@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(@Param("id") int id);

	//@Update("UPDATE article SET title = #{title}, `body` = #{body}, updateDate = NOW() WHERE id = #{id}")
	
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	//@Select("SELECT * FROM article ORDER BY id DESC")
	/*@Select(" SELECT A.*, " +
			" M.nickname AS extra__writerName " +
			" FROM article AS A " +
			" LEFT JOIN member AS M " +
			" ON A.memberId = M.id " +
			" ORDER BY A.id DESC ")
	public List<Article> getArticles();*/
	
	
	
	/*
	@Select("""
			<script>
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId = M.id
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			ORDER BY A.id DESC
			</script>
			""")
	 */
	
	/*@Select(" SELECT A.*, " +
			" M.nickname AS extra__writerName " +
			" FROM article AS A " +
			" LEFT JOIN member AS M " +
			" ON A.memberId = M.id " +
			" ORDER BY A.id DESC ")*/
	//public List<Article> getArticles(@Param("boardId") int boardId);
	//public List<Article> getArticles(
	//		@Param("boardId") int boardId, int limitStart, int limitTake);
	//public List<Article> getArticles(
	public List<Article> getForPrintArticles(
			int boardId, 
			String searchKeywordTypeCode, 
			String searchKeyword, 
			int limitStart,
			int limitTake);
	
	//@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	
	
	//public int getArticlesCount(int boardId);
	
	/*@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM article AS A
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			</script>
			""")*/
	//public int getArticlesCount(@Param("boardId") int boardId);
	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);
	
	/*private int articlesLastId;
	private List<Article> articles;

	public ArticleRepository() {
		articlesLastId = 0;
		articles = new ArrayList<>();
	}

	public void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
		}
	}

	public Article writeArticle(String title, String body) {
		int id = articlesLastId + 1;
		Article article = new Article(id, title, body);

		articles.add(article);
		articlesLastId = id;

		return article;
	}

	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	public void deleteArticle(int id) {
		Article article = getArticle(id);

		articles.remove(article);
	}

	public void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
	}

	public List<Article> getArticles() {
		return articles;
	}*/
	
	
	
	
	/*@Update("""
			<script>
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id}
			</script>
			""")*/
	public int increaseHitCount(int id);
	
	
	/*@Select("""
			<script>
			SELECT hitCount
			FROM article
			WHERE id = #{id}
			</script>
			""")*/
	public int getArticleHitCount(int id);
	
	
	
	
	
	
	/*@Select("""
			<script>
			SELECT IFNULL(SUM(RP.point), 0) AS s
			FROM reactionPoint AS RP
			WHERE RP.relTypeCode = 'article'
			AND RP.relId = #{id}
			AND RP.memberId = #{memberId}
			</script>
			""")*/
	/*public int getSumReactionPointByMemberId(int id, int memberId);*/
	
	
	
	
	
	
	/*@Update("""
			<script>
			UPDATE article
			SET goodReactionPoint = goodReactionPoint + 1
			WHERE id = #{id}
			</script>
			""")*/
	public int increaseGoodReactionPoint(int id);

	/*@Update("""
			<script>
			UPDATE article
			SET badReactionPoint = badReactionPoint + 1
			WHERE id = #{id}
			</script>
			""")*/
	public int increaseBadReactionPoint(int id);
	
	
	
	/*@Update("""
			<script>
			UPDATE article
			SET goodReactionPoint = goodReactionPoint - 1
			WHERE id = #{id}
			</script>
			""")*/
	public int decreaseGoodReactionPoint(int id);

	/*@Update("""
			<script>
			UPDATE article
			SET badReactionPoint = badReactionPoint - 1
			WHERE id = #{id}
			</script>
			""")*/
	public int decreaseBadReactionPoint(int id);

	
	
	/*@Select("""
			<script>
			SELECT *
			FROM article
			WHERE id = #{id}
			</script>
			""")*/
	public Article getArticle(int id);
	
	
	
}
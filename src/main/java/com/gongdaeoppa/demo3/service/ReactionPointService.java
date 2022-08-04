package com.gongdaeoppa.demo3.service;

import org.springframework.stereotype.Service;

import com.gongdaeoppa.demo3.repository.ReactionPointRepository;
import com.gongdaeoppa.demo3.vo.ResultData;
import com.gongdaeoppa.demo3.vo.Rq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactionPointService {
	private final ReactionPointRepository reactionPointRepository;
	private final ArticleService articleService;
	


	/*public boolean actorCanMakeReactionPoint(int actorId, String relTypeCode, int relId) {
		if ( actorId == 0 ) {
			return false;
		}
		
		return reactionPointRepository.getSumReactionPointByMemberId(relTypeCode, relId, actorId) == 0;
	}*/
	
	
	
	
	public ResultData actorCanMakeReactionPoint(int actorId, String relTypeCode, int relId) {
		if ( actorId == 0 ) {
			return ResultData.from("F-1", "로그인 후 이용해주세요.");
		}

		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(relTypeCode, relId, actorId);

		if ( sumReactionPointByMemberId != 0 ) {
			return ResultData.from("F-2", "리액션이 불가능합니다.", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}

		return ResultData.from("S-1", "리액션이 가능합니다.", "sumReactionPointByMemberId", sumReactionPointByMemberId);
	}
	
	
	
	
	
	
	
	
	
	
	

	public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addGoodReactionPoint(actorId, relTypeCode, relId);
		
		switch ( relTypeCode ) {
		case "article":
			articleService.increaseGoodReactionPoint(relId);
			break;
		}
		
		return ResultData.from("S-1", "좋아요 처리 되었습니다");
	}
	
	public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addBadReactionPoint(actorId, relTypeCode, relId);
		
		switch ( relTypeCode ) {
		case "article":
			articleService.increaseBadReactionPoint(relId);
			break;
		}
		
		return ResultData.from("S-1", "싫어요 처리 되었습니다");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ResultData deleteGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreaseGoodReactionPoint(relId);
			break;
		}

		return ResultData.from("S-1", "좋아요가 취소처리 되었습니다");
	}

	public ResultData deleteBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreaseBadReactionPoint(relId);
			break;
		}

		return ResultData.from("S-1", "싫어요 취소처리 되었습니다");
	}
	
	
	
	
	
}
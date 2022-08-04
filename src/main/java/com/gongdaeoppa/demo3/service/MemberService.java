package com.gongdaeoppa.demo3.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.gongdaeoppa.demo3.repository.MemberRepository;
import com.gongdaeoppa.demo3.util.Ut;
import com.gongdaeoppa.demo3.vo.Member;
import com.gongdaeoppa.demo3.vo.ResultData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	
	private final AttrService attrService;


	
	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) {
			return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
		}
		
		
		// 이름+이메일 중복체크
		oldMember = getMemberByNameAndEmail(name, email);

		if (oldMember != null) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email));
		}
				

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.", "id", id);
	}
	
	


	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	
	
	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	
	
	
	
	public ResultData modify(int id, String loginPw, String name, String nickname, String email, String cellphoneNo) {

		memberRepository.modify(id, loginPw, name, nickname, email, cellphoneNo);

		return ResultData.from("S-1", "회원정보가 수정되었습니다.");
	}


	
	
	
	
	
	
	
	public String genMemberModifyAuthKey(int actorId) {
		String memberModifyAuthKey = Ut.getTempPassword(10);

		attrService.setValue("member", actorId, "extra", "memberModifyAuthKey", memberModifyAuthKey, Ut.getDateStrLater(60 * 5));

		return memberModifyAuthKey;
	}





	
	
	public ResultData checkMemberModifyAuthKey(int actorId, String memberModifyAuthKey) {
		String saved = attrService.getValue("member", actorId, "extra", "memberModifyAuthKey");

		if ( !saved.equals(memberModifyAuthKey) ) {
			return ResultData.from("F-1", "일치하지 않거나 만료되었습니다.");
		}

		return ResultData.from("S-1", "정상적인 코드입니다.");
	}
	
	
	
	
	
	
	
	
	
	
	public List<Member> getForPrintMembers(int authLevel, String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page) {
		int limitStart = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;

		List<Member> members = memberRepository.getForPrintMembers(authLevel, searchKeywordTypeCode, searchKeyword,
				limitStart, limitTake);

		return members;
	}
	
	
	
	
	public int getMembersCount(int authLevel, String searchKeywordTypeCode, String searchKeyword) {
		return memberRepository.getMembersCount(authLevel, searchKeywordTypeCode, searchKeyword);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void deleteMembers(List<Integer> memberIds) {
		for ( int memberId : memberIds ) {
			Member member = getMemberById(memberId);

			if ( member != null ) {
				deleteMember(member);
			}
		}
    }

	private void deleteMember(Member member) {
		memberRepository.deleteMember(member.getId());
	}
	
	
	
	
	
	

}

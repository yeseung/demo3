package com.gongdaeoppa.demo3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gongdaeoppa.demo3.repository.MemberRepository;
import com.gongdaeoppa.demo3.service.AttrService;
import com.gongdaeoppa.demo3.vo.Member;

import lombok.RequiredArgsConstructor;

@SpringBootTest
class Demo3ApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void contextLoads() {
		
		Member member = memberRepository.getMemberById(2);
		System.out.println("-.-.-.-.-.-.-.- " + member);

			
	}

}

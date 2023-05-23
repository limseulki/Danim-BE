package com.project.danim_be.member.controller;

import org.springframework.http.HttpStatus;
import com.project.danim_be.common.util.Message;
import com.project.danim_be.member.service.NaverService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.danim_be.common.util.Message;
import com.project.danim_be.member.dto.SignupRequestDto;
import com.project.danim_be.member.service.MemberService;
import java.io.IOException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final NaverService naverService;

	@PostMapping("/signup")
	public ResponseEntity<Message> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			String errMessage =  bindingResult.getAllErrors().get(0).getDefaultMessage();
			Message apiResult = Message.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message(errMessage)
				.build();
			return ResponseEntity.badRequest().body(apiResult);
		}else{
			return memberService.signup(signupRequestDto);
		}
	}
	//네이버 소셜 로그인
	@GetMapping("/naver/callback")
	public ResponseEntity<Message> naverLogin(@RequestParam String code, @RequestParam String state, HttpServletResponse response) throws IOException {
		return naverService.naverLogin(code, state, response);
	}





}

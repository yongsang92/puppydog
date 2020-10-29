package com.puppydog.mall.cs.controller;
import javax.validation.Valid;

import com.puppydog.mall.cs.service.MemberService;
import com.puppydog.mall.dd.domain.Member;
import com.puppydog.mall.form.MemberForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;
    

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "index/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute MemberForm memberForm, BindingResult br) {

        if (br.hasErrors()) {
            return "/index/signup";
        }
        Member member = memberForm.toEntity();
        memberService.save(member);
        return "index/index";
    }
 
    @ResponseBody
    @GetMapping("/idcheck")
    public boolean idDuplicationCheck(String id) {
        return memberService.idCheck(id);
    }
}
package com.puppydog.mall.cs.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index/index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referrer);
        return "index/login";
    }

    @PostMapping("/logout")
    public void logout() {
    }
}
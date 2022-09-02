package com.codeLab.authLab.controllers;

import com.codeLab.authLab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ContentController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model m){
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        m.addAttribute("username", username);

        return "/home";
    }

}

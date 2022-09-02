package com.codeLab.authLab.controllers;

import com.codeLab.authLab.model.SiteUser;
import com.codeLab.authLab.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "/signUp.html";
    }

    @PostMapping("/signUp")
    public RedirectView signUpUser(String username, String password) {
    String hashPass = BCrypt.hashpw(password,BCrypt.gensalt(10));
    SiteUser newUser = new SiteUser(username,hashPass);
    userRepository.save(newUser);
    return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/login.html";
    }

    @PostMapping("/login")
    public RedirectView logInSiteUser(HttpServletRequest request, String username, String password){
        SiteUser userFromDB =userRepository.findByUsername(username);
        if ((userFromDB == null)
            || (!BCrypt.checkpw(password, userFromDB.getPassword()))){
            return new RedirectView("/login");
        }
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return new RedirectView("/home");

    }


}

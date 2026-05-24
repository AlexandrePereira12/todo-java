package com.udemy.spring.controller;

import com.udemy.spring.DTO.UserDTO;
import com.udemy.spring.services.ListService;
import com.udemy.spring.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    UserService service;
    AuthenticationManager authenticationManager;
    ListService listService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@RequestParam Map <String, String> body){
        UserDTO dto = new UserDTO(body.get("email"), body.get("password"), body.get("name"));
        service.saveByDTO(dto);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> postMethodName(@RequestParam Map <String, String> body)
    {
        Authentication authReq = UsernamePasswordAuthenticationToken.unauthenticated(body.get("username"), body.get("password"));
        Authentication authResp = authenticationManager.authenticate(authReq);
        return new ResponseEntity<Boolean>(authResp.isAuthenticated(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView dashboard(){
        ModelAndView view = new ModelAndView("dashboard");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        view.addObject("auth", auth);
        view.addObject("lists", listService.findByUserEmail(auth.getName()));
        return view;
    }
}
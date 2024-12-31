package com.jojoldu.book.springboot.controller;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addCommonAttributes(Model model, @SessionAttribute(name = "user", required = false) SessionUser user) {
        if (user != null) {
            model.addAttribute("userNm", user.getName());
            model.addAttribute("userId", user.getUserId());
        } else {
            model.addAttribute("userNm", null);
        }
    }
}

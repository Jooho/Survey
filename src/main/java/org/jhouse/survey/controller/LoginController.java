package org.jhouse.survey.controller;

import org.jhouse.survey.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
/**
 * Created by jhouse on 12/13/14.
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap map,HttpSession session, @RequestParam("userName") String username, @RequestParam("passwd") String passwd) {
        String view = "survey";

        boolean isNew = loginService.loginProcess(username,passwd);

        if (isNew) {
            session.setAttribute("username",username);
            view = username.equals("admin") ? "admin":"survey";
            map.put("msg", "This page is not 100% encoded !!! ^^");
        } else {
            view = "login";
            map.put("msg", "Please input your redhat email Id <br/> <span style='color:red'> WITHOUT </span> '@redhat.com'");
        }

        return view;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}

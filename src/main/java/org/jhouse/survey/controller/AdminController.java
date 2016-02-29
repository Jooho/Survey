package org.jhouse.survey.controller;

import org.jhouse.survey.service.AdminService;
import org.jhouse.survey.vo.ElectVo;
import org.jhouse.survey.vo.QuizVo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jhouse on 12/14/14.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(ModelMap map, HttpSession session) {
        String view = "admin";
        String username = (String) session.getAttribute("username");
        boolean isAdminUser = username != null ? session.getAttribute("username").equals("admin") : false;

        if (isAdminUser) {
            map.put("msg", "You are ADMIN!! Nice to meet you");
        } else {
            view = "survey";
            map.put("msg", "You are not ADMIN!! Please do try to sneak this page on yours, Mr " + username);
        }

        return view;
    }

    @RequestMapping(value = "/publishQuiz.ajax", method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    boolean publishQuiz(@RequestBody QuizVo data) {
        boolean success = adminService.publishQuiz(data.getNumber());
        return success;
    }

    @RequestMapping(value = "/quizList.ajax", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8", produces="text/plain;charset=UTF-8" )
    public
    @ResponseBody
    String quizList() {
        JSONObject peerList = adminService.getQuizList();
        return peerList.toJSONString();
    }

    @RequestMapping(value = "/electQuiz.ajax", method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    boolean elect(@RequestBody ElectVo data) {
        boolean success = adminService.electQuiz(data);
        return success;
    }

    @RequestMapping(value = "/showResult.ajax", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8", produces="text/plain;charset=UTF-8")
    public
    @ResponseBody
    String showResult(@RequestBody QuizVo data) {
        JSONObject resultPage = adminService.showResult(data);

        return resultPage.toJSONString();
    }


    @RequestMapping(value = "/peerList.ajax", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8", produces="text/plain;charset=UTF-8" )
    public
    @ResponseBody
    String peerList() {
        JSONObject peerList = adminService.peerList();
        return peerList.toJSONString();
    }


    @RequestMapping(value = "/disposeNotElectedPeerList.ajax", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8", produces="text/plain;charset=UTF-8" )
    public
    @ResponseBody
    String disposeNotElectedPeerList() {
        JSONObject notElectedPeerList = adminService.disposeNotElectedPeerList();
        return notElectedPeerList.toJSONString();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPage() {

        return "test";
    }

}

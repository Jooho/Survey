package org.jhouse.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jhouse on 12/13/14.
 */
@Controller
public class RestfulController {

    @RequestMapping("/helloRS")
    public @ResponseBody ABC  helloRS() {
        ABC test = new ABC();
        test.setName("yes");
        return test;

    }


    private class ABC {
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name) {

            this.name =name;
        }
    }
}

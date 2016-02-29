package org.jhouse.survey.service;

import org.jhouse.survey.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jhouse on 12/13/14.
 */
@Service
public class LoginService {

    @Autowired
    UserManager userManager;


    public boolean loginProcess(String username, String passwd) {
        boolean success=false;
        if (userManager.isValidUser(username) || username.equals("admin")) {
            if (userManager.isUserNamePreserved(username, passwd)) {
                success = false;

            } else {
                userManager.putUserName(username, passwd);
                success = true;
            }
        }

        return success;
    }


}

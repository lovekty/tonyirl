package me.tonyirl.passport.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tony on 16-4-17.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Value("#{commonConfigProperties['passport.default.redirecturi']}")
    private String defaultRedirectUri;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView usernameAndPasswordLoginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView usernameAndPasswordCheckLogin(@RequestParam(value = "redirect", required = false) String redirect, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isBlank(redirect)) {
            redirect = defaultRedirectUri;
        }
        return new ModelAndView("redirect:" + redirect);
    }
}

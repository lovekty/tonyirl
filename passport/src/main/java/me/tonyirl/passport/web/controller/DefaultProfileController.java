package me.tonyirl.passport.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tony on 16-4-17.
 */
@Controller
@RequestMapping("/profile")
public class DefaultProfileController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultProfilePage() {
        return new ModelAndView("profile");
    }
}

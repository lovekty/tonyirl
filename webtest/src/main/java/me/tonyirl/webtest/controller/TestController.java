package me.tonyirl.webtest.controller;

import me.tonyirl.webtest.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by tony on 2016/10/19.
 */
@Controller
public class TestController {

    @RequestMapping("test")
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("test");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setName("Tony");
        userInfo.setGender(1);
        userInfo.setIdentityCardNumber("110100199007280045");
        userInfo.setAge(28);

        List<Integer> integers = IntStream.range(1, 20).collect(ArrayList::new, ArrayList::add, (x, y) -> {
        });

        mav.addObject("userInfo", userInfo);
        mav.addObject("integers", integers);

        return mav;
    }
}

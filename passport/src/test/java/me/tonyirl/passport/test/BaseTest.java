package me.tonyirl.passport.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tony on 16/2/23.
 */
@ContextConfiguration({"classpath:spring-dubbo-client.xml","classpath*:spring-common.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
}

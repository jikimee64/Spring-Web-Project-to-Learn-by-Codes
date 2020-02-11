package org.zerock.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Slf4j
public class AopServiceTests {
    @Setter(onMethod_ = @Autowired)
    private AopService service;

    //@Test
    public void testClass() {
        log.info(String.valueOf(service));
        log.info(service.getClass().getName());
    }

    @Test
    public void testAdd() throws Exception {
        log.info(String.valueOf(service.doAdd("123", "456")));
    }

    //@Test
    public void testAddError() throws Exception {
        log.info(String.valueOf(service.doAdd("123", "ABC")));
    }
}

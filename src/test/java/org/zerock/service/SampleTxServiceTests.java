package org.zerock.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Slf4j
public class SampleTxServiceTests {
    @Setter(onMethod_ = @Autowired)
    private SampleTxService service;

    @Test
    public void testLong() {
        String str ="Starry\r\n +" +
                "Paint your palette blue and grey\r\n" +
                "Look out on a summer's day";

        log.info(String.valueOf(str.getBytes().length));

        service.addData(str);
    }


}

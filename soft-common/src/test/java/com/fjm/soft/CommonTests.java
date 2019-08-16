package com.fjm.soft;

import com.fjm.soft.utils.idgen.SnowFlake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-16 15:58
 * @Description:
 */
@RunWith(SpringRunner.class)
public class CommonTests {
    //@Test
    public void contextLoads() {
    }

    @Test
    public void testGenerateUUID() throws Exception {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlake.nextId());
        }
    }
}

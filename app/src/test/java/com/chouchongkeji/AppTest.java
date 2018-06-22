package com.chouchongkeji;

import com.chouchongkeji.goexplore.utils.Utils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

    @Test
    public void md5() {
        System.out.println(DateUtils.addDays(new Date(), 2).getTime());
    }
}

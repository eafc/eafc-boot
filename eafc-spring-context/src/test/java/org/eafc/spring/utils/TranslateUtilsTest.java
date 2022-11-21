package org.eafc.spring.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * @author liuxx
 * @date 2022/4/1
 */
public class TranslateUtilsTest {

    private static final String MESSAGE_CODE = "eafc.hello";
    private static final String HELLO_EN = "Hello eafc.";
    private static final String HELLO_CN = "您好 eafc.";

    private static final String MESSAGE_WRAPPER = "{eafc.plus} =1,2,3";

    @Test
    public void testLocale() {
        new ClassPathXmlApplicationContext("classpath:/context.xml");

        LocaleContextHolder.setLocale(Locale.US);
        Assert.assertEquals(HELLO_EN, TranslateUtils.translate(MESSAGE_CODE));

        LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        Assert.assertEquals(HELLO_CN, TranslateUtils.translate(MESSAGE_CODE));
    }

    @Test
    public void testLocaleWrapper() {
        new ClassPathXmlApplicationContext("classpath:/context.xml");

        LocaleContextHolder.setLocale(Locale.US);
        Assert.assertEquals("1 + 2 = 3", TranslateUtils.translateWrapper(MESSAGE_WRAPPER));

        LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        Assert.assertEquals("1 加 2 等于 3", TranslateUtils.translateWrapper(MESSAGE_WRAPPER));
    }
}

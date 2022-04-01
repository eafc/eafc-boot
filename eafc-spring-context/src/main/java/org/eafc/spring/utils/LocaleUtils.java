package org.eafc.spring.utils;

import lombok.extern.slf4j.Slf4j;
import org.eafc.core.Constant;
import org.eafc.spring.SpringContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * @author kayn.liu
 * @date 2022/3/31
 */
@Slf4j
public final class LocaleUtils {

    private static final Object[] EMPTY_ARRAY = {};
    private static final int MSG_TOKEN_LENGTH = 2;

    private LocaleUtils() {
    }

    /**
     * locale
     *
     * @param messageCode key of message source
     * @param params      replaced params
     * @return locale value
     */
    public static String locale(String messageCode, Object... params) {
        if (!StringUtils.hasText(messageCode)) {
            return messageCode;
        }

        MessageSource messageSource = SpringContextHolder.getBean(MessageSource.class);
        Locale currentLocale = LocaleContextHolder.getLocale();

        try {
            return messageSource.getMessage(messageCode, params, currentLocale);
        } catch (NoSuchMessageException ex) {
            log.warn("message locale missing,message:{},locale:{}", messageCode, currentLocale.getDisplayName());
            return messageCode;
        }
    }

    /**
     * localization for wrapped message
     *
     * @param message wrapped message, eg: {a.x}=1,2
     * @return locale value
     */
    public static String localeWrapper(String message) {
        if (!StringUtils.startsWithIgnoreCase(message, Constant.LEFT_BRACE)) {
            return message;
        }

        String[] msgToken = message.split(Constant.EQUAL);
        String msg = msgToken[0].trim();
        if (msg.startsWith(Constant.LEFT_BRACE) && msg.endsWith(Constant.RIGHT_BRACE)) {
            String messageCode = msg.substring(1, msg.length() - 1);
            Object[] params = buildParams(msgToken);
            return locale(messageCode, params);
        }
        return message;
    }

    private static Object[] buildParams(String[] msgToken) {
        if (msgToken.length == MSG_TOKEN_LENGTH) {
            return msgToken[1].split(Constant.COMMA);
        } else {
            return EMPTY_ARRAY;
        }
    }
}

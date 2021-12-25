package com.github.wugenshui.knife4j.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JSR303具体的校验器
 *
 * @author : chenbo
 * @date : 2020-02-08
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    public boolean required = false;

    /**
     * 初始化
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * 校验
     *
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 值是必须的就判断是否合法
        //if (required) {
        //    return isMobile(s);
        //} else {
        if (StringUtils.isEmpty(s)) {
            return true;
        } else {
            return isMobile(s);
        }
        //}
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     *
     * @param str 测试字符串
     * @return
     */
    private boolean isMobile(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
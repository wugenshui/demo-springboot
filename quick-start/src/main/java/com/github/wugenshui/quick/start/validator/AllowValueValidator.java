package com.github.wugenshui.quick.start.validator;

import com.github.wugenshui.quick.start.valid.AllowValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : chenbo
 * @date : 2021-06-26
 */
public class AllowValueValidator implements ConstraintValidator<AllowValue, Object> {

    private String[] strValues;
    private int[] intValues;

    @Override
    public void initialize(AllowValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 空值不参加判断
        if (value == null) {
            return true;
        }
        if (value instanceof String) {
            for (String s : strValues) {
                if (s.equals(value)) {
                    return true;
                }
            }
        } else if (value instanceof Integer) {
            for (Integer s : intValues) {
                if (s.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

}
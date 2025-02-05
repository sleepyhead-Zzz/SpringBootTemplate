package com.springboottemplate.common.enums;

import cn.hutool.core.convert.Convert;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode;
import java.util.Objects;

/**
 * @author Sleepyhead
 */
public class BasicEnumUtil {

    private BasicEnumUtil() {
    }

    public static final String UNKNOWN = "未知";

    public static <E extends Enum<E> & BasicEnum<?>> E fromValueSafely(Class<E> enumClass, Object value) {
        E target = null;

        for (E enumConstant : enumClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.getValue(), value)) {
                target = enumConstant;
                break; // 找到后可以直接退出循环
            }
        }

        return target;
    }

    public static <E extends Enum<E> & BasicEnum<?>> E fromValue(Class<E> enumClass, Object value) {
        E target = fromValueSafely(enumClass, value);

        if (target == null) {
            throw new ApiException(ErrorCode.Internal.GET_ENUM_FAILED, enumClass.getSimpleName());
        }

        return target;
    }

    public static <E extends Enum<E> & BasicEnum<?>> String getDescriptionByBool(Class<E> enumClass, Boolean bool) {
        Integer value = Convert.toInt(bool, 0);
        return getDescriptionByValue(enumClass, value);
    }

    public static <E extends Enum<E> & BasicEnum<?>> String getDescriptionByValue(Class<E> enumClass, Object value) {
        E basicEnum = fromValueSafely(enumClass, value);
        if (basicEnum != null) {
            return basicEnum.description();
        }
        return UNKNOWN;
    }
}

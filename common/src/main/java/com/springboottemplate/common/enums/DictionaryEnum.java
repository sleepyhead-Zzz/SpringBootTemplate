package com.springboottemplate.common.enums;

/**
 * 字典类型 接口
 *
 * @author Sleepyhead
 */
public interface DictionaryEnum<T> extends BasicEnum<T> {

    /**
     * 获取css标签
     *
     * @return css标签
     */
    String cssTag();

}

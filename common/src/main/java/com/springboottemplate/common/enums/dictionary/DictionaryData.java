package com.springboottemplate.common.enums.dictionary;


import com.springboottemplate.common.enums.DictionaryEnum;
import lombok.Data;

/**
 * 字典模型类
 *
 * @author Sleepyhead
 */
@Data
public class DictionaryData {

    private String label;
    private Integer value;
    private String cssTag;

    @SuppressWarnings("rawtypes")
    public DictionaryData(DictionaryEnum enumType) {
        if (enumType != null) {
            this.label = enumType.description();
            this.value = (Integer) enumType.getValue();
            this.cssTag = enumType.cssTag();
        }
    }

}

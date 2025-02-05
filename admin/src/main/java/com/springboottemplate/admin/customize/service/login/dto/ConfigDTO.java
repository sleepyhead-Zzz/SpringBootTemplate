package com.springboottemplate.admin.customize.service.login.dto;


import com.springboottemplate.common.enums.dictionary.DictionaryData;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class ConfigDTO {

    private Boolean isCaptchaOn;

    private Map<String, List<DictionaryData>> dictionary;

}

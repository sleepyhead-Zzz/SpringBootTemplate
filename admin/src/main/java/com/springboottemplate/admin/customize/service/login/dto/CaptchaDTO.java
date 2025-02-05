package com.springboottemplate.admin.customize.service.login.dto;

import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class CaptchaDTO {

    private Boolean isCaptchaOn;
    private String captchaCodeKey;
    private String captchaCodeImg;

}

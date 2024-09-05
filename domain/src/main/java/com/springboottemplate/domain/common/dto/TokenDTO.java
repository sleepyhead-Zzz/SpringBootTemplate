package com.springboottemplate.domain.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
@AllArgsConstructor
public class TokenDTO {

    private String token;

    private CurrentLoginUserDTO currentUser;

}

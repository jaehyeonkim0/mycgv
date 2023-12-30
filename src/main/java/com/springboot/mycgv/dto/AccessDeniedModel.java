package com.springboot.mycgv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessDeniedModel {

    private String error;
    private String message;
}

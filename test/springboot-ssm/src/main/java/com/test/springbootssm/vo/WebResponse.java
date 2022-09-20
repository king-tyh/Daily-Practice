package com.test.springbootssm.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WebResponse implements Serializable {

    private int code;

    private String msg;

    private  Object data;
}

package com.sell.VO;

import lombok.Data;

import java.util.List;

@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultVO() {
    }
}

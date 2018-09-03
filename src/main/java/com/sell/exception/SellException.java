package com.sell.exception;

import com.sell.enums.ResultEnum;

import javax.persistence.criteria.CriteriaBuilder;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super (resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }
}

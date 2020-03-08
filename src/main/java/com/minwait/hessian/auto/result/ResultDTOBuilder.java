package com.minwait.hessian.auto.result;

/**
 * ResultDTOBuilder
 * ResultDTO 构建器
 *
 * @author by xunmi
 * @version 1.0
 * @date 2020/3/4 14:13
 */
public class ResultDTOBuilder {

    private ResultDTO resultDTO;

    public static ResultDTOBuilder create() {
        ResultDTOBuilder resultDTOBuilder = new ResultDTOBuilder();
        resultDTOBuilder.resultDTO = new ResultDTO();
        return resultDTOBuilder;
    }

    public ResultDTOBuilder code(String code) {
        this.resultDTO.setCode(code);
        return this;
    }

    public ResultDTOBuilder message(String message) {
        this.resultDTO.setMessage(message);
        return this;
    }

    public ResultDTOBuilder data(Object data) {
        this.resultDTO.setData(data);
        return this;
    }

    public ResultDTO build() {
        return this.resultDTO;
    }

}

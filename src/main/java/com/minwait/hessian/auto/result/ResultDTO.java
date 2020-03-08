package com.minwait.hessian.auto.result;

import lombok.*;

/**
 * ResultDTO
 *
 * @author by xunmi
 * @version 1.0
 * @date 2020/3/4 13:29
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    /**
     * 状态码
     */
    @NonNull
    private String code;
    /**
     * 状态描述
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
}

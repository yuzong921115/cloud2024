package com.zongyu.cloud.resp;

import com.zongyu.cloud.enums.ReturnCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 失败返回
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}

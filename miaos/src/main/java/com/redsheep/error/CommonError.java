package com.redsheep.error;

/**
 * @author redsheep
 * @date 2019/6/12
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}

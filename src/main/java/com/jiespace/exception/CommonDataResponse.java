package com.jiespace.exception;

import com.google.gson.Gson;
import lombok.Data;

/**
 * 所有请求返回基类 (返回第三方的异常data信息)
 * Created by yangweiping on 16/9/20.
 */
@Data
public class CommonDataResponse {

  private static final Gson gson = new Gson();
  public static final String CODE_SUCCESS = "0";
  public static final String CODE_FAIL = "-1";
  public static final String MSG_SUCCESS = "success";
  public static final String MSG_FAIL = "fail";
  public static final String MSG_DATA = "";

  /**
   * 返回码
   */
  protected String ret;
  /**
   * 返回子码
   */
  protected String sub;
  /**
   * 返回消息
   */
  protected String msg;
  /**
   * 返回data信息
   */
  protected String data;

  public CommonDataResponse() {
    this.ret = CODE_SUCCESS;
    this.sub = CODE_SUCCESS;
    this.msg = MSG_SUCCESS;
    this.data = MSG_DATA;
  }

  public CommonDataResponse(String ret, String sub, String msg, String data) {
    this.ret = ret;
    this.sub = sub;
    this.msg = msg;
    this.data = data;
  }

  public CommonDataResponse(CommonResponse response, Object data) {
    this.ret = response.getRet();
    this.sub = response.getSub();
    this.msg = response.getMsg();
    this.data = gson.toJson(data);
  }

  private CommonDataResponse(CommonDataResponse.Builder builder) {
    this.ret = builder.ret;
    this.sub = builder.sub;
    this.msg = builder.msg;
    this.data = builder.data;
  }

  public static class Builder {

    private String ret;
    private String sub;
    private String msg;
    private String data;

    public CommonDataResponse.Builder withRet(String ret) {
      this.ret = ret;
      return this;
    }

    public CommonDataResponse.Builder withSub(String sub) {
      this.sub = sub;
      return this;
    }

    public CommonDataResponse.Builder withMsg(String msg) {
      this.msg = msg;
      return this;
    }

    public CommonDataResponse.Builder withData(String data) {
      this.data = data;
      return this;
    }


    public CommonDataResponse.Builder success() {
      this.ret = CODE_SUCCESS;
      this.sub = CODE_SUCCESS;
      this.msg = MSG_SUCCESS;
      this.data = MSG_DATA;
      return this;
    }

    public CommonDataResponse.Builder fail() {
      this.ret = CODE_FAIL;
      this.sub = CODE_FAIL;
      this.msg = MSG_FAIL;
      this.data = MSG_DATA;
      return this;
    }

    public CommonDataResponse build() {
      validate();
      return new CommonDataResponse(this);
    }

    private void validate() {
    }
  }
}

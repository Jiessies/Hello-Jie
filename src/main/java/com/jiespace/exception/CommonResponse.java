package com.jiespace.exception;

import lombok.Data;

/**
 * 所有请求返回的基类(无data)
 * Created by vance on 9/9/15.
 */
@Data
public class CommonResponse {

  public static final String CODE_SUCCESS = "0";
  public static final String CODE_FAIL = "-1";
  public static final String MSG_SUCCESS = "success";
  public static final String MSG_FAIL = "fail";

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
   * 兼容营销返回码
   */
  protected String retCode;

  public CommonResponse() {
    this.ret = CODE_SUCCESS;
    this.sub = CODE_SUCCESS;
    this.msg = MSG_SUCCESS;
  }

  public CommonResponse(String ret, String sub, String msg) {
    this.ret = ret;
    this.sub = sub;
    this.msg = msg;
  }

  private CommonResponse(Builder builder) {
    this.ret = builder.ret;
    this.sub = builder.sub;
    this.msg = builder.msg;
  }

  public CommonResponse(CommonResponse response) {
    this.ret = response.getRet();
    this.sub = response.getSub();
    this.msg = response.getMsg();
  }

  public static class Builder {

    private String ret;
    private String sub;
    private String msg;

    public Builder withRet(String ret) {
      this.ret = ret;
      return this;
    }

    public Builder withSub(String sub) {
      this.sub = sub;
      return this;
    }

    public Builder withMsg(String msg) {
      this.msg = msg;
      return this;
    }


    public Builder success() {
      this.ret = CODE_SUCCESS;
      this.sub = CODE_SUCCESS;
      this.msg = MSG_SUCCESS;
      return this;
    }

    public Builder fail() {
      this.ret = CODE_FAIL;
      this.sub = CODE_FAIL;
      this.msg = MSG_FAIL;
      return this;
    }

    public CommonResponse build() {
      validate();
      return new CommonResponse(this);
    }

    private void validate() {}
  }

}

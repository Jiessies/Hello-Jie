package com.jiespace.exception;


/**
 * 订单异常类
 * Created by vance on 9/9/15.
 */
public class OrderException extends RuntimeException {

  public static final long serialVersionUID = 1L;

  private CommonResponse commonResponse;

  private CommonDataResponse commonDataResponse;

  public OrderException(CommonResponse commonResponse) {
    super(commonResponse.getMsg());
    this.commonResponse = commonResponse;
  }

  public OrderException(CommonDataResponse commonDataResponse){
    super(commonDataResponse.getMsg());
    this.commonDataResponse = commonDataResponse;
  }


  public OrderException() {
    super();
  }

  public OrderException(Throwable cause) {
    super(cause);
  }

  public OrderException(String message) {
    super(message);
  }

  public OrderException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommonResponse getCommonResponse() {
    return commonResponse;
  }

  public CommonDataResponse getCommonDataResponse() {
    return commonDataResponse;
  }

}

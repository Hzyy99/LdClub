package com.black.auth.common.exception;

import java.io.Serializable;

/**
 * @author Black
 * @version 1.0
 */
public class RestErrorResponse implements Serializable {

 private String errMessage;

 public RestErrorResponse(String errMessage){
  this.errMessage= errMessage;
 }

 public String getErrMessage() {
  return errMessage;
 }

 public void setErrMessage(String errMessage) {
  this.errMessage = errMessage;
 }
}

package com.nadeul.ndj.enums;

/**
  * @FileName : ApiResponseEnum.java
  * @Project : ndj
  * @Date : 2023. 6. 7. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : 공통 응답코드&응답메세지 Enum
  */

public enum ApiResponseEnum {
	
  SUCCESS(200, "Success"),
  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error");

  private final int code;
  private final String message;

  ApiResponseEnum(int code, String message) {
      this.code = code;
      this.message = message;
  }

  public int getCode() {
      return code;
  }

  public String getMessage() {
      return message;
  }

}



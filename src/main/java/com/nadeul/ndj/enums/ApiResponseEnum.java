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
	
  SUCCESS("0000", "성공"),
  BAD_REQUEST("0001", "Bad Request"),
  UNAUTHORIZED("0002", "Unauthorized"),
  FORBIDDEN("0003", "Forbidden"),
  NOT_FOUND("0004", "Not Found"),
  INTERNAL_SERVER_ERROR("0005", "Internal Server Error"),
	VALIDATION_FAILED("0006","필드가 빈 값이거나 올바르지 않습니다."),
	DUPLICATION("0007","중복되었습니다.");

  private final String code;
  private final String message;

  ApiResponseEnum(String code, String message) {
      this.code = code;
      this.message = message;
  }

  public String getCode() {
      return code;
  }

  public String getMessage() {
      return message;
  }

}



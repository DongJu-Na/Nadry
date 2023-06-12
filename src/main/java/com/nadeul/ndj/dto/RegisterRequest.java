package com.nadeul.ndj.dto;

import com.nadeul.ndj.model.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
/*	
PK MEM_ID INT NOT NULL 회원번호
EMAIL VARCHAR 이메일
NAME VARCHAR 이름
PASSWORD VARCHAR 비밀번호
ROLE VARCHAR NOT NULL 나드리 이용 권한
LAST_LOGIN_DATE DATE 최종 로그인 일자
REGISTER_DATE DATE 가입일자
*/
	
	@Schema(description = "이름" , example = "나동주")
  private String name;
	@Schema(description = "이메일" , example = "ehdeld123@gmail.com")
  private String email;
	@Schema(description = "비밀번호" , example = "**********")
  private String password;
	@Schema(description = "나드리이용권한" , example = "")
  private Role role;
	@Schema(description = "생일 yyyy-mm-dd" , example = "1995-08-22")
  private String birthDay;
}

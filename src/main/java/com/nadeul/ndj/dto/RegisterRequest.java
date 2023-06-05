package com.nadeul.ndj.dto;

import com.nadeul.ndj.model.Role;
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
  private String name;
  private String email;
  private String password;
  private Role role;
  private String birthDay;
}

package com.nadeul.ndj.dto;

import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.model.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

  @Schema(description = "인덱스" , example = "1")
  private Integer memId;
  @Schema(description = "이름" , example = "나동주")
  private String name;
  @Schema(description = "이메일" , example = "ehdeld123@gmail.com")
  private String email;
  @Schema(description = "비밀번호" , example = "***********")
  private String password;
  @Schema(description = "생일" , example = "19950822")
  private String birthDay;
  @Schema(description = "역할" , example = "USER")
  private Role role;
  @Schema(description = "프로필이미지URL" , example = "www.nadry.com/profile/img.png")
  private String profileUrl;
  
  public MemberDto(Member member) {
	  this.memId = member.getMemId();
	  this.name = member.getName();
	  this.email = member.getEmail();
	  this.password = member.getPassword();
	  this.birthDay = member.getBirthDay();
	  this.role = member.getRole();
	  this.profileUrl = member.getProfileUrl();
  }
  
}

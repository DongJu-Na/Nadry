package com.nadeul.ndj.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nadeul.ndj.model.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Member implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
  @GeneratedValue
  private Integer memId;
  private String name;
  private String email;
  private String password;
  private String birthDay;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "member")
  private List<Token> tokens;
  
  @OneToMany(mappedBy = "memId")
  private List<Trip> trips;
  
  @OneToOne(mappedBy = "memId")
  private Point point;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Member update(String name) {
    this.name = name;
    return this;
  }
  
}

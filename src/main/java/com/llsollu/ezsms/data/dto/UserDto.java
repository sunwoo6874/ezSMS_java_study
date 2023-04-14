package com.llsollu.ezsms.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;

import com.llsollu.ezsms.data.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {

  @Id
  private Long id;

  private String name;

  private String userId;

  private String password;

  private String creationDate;

  public User toEntity() {
    return User.builder()
        .id(id)
        .name(name)
        .userId(userId)
        .password(password)
        .creationDate(creationDate)
        .build();
  }

}

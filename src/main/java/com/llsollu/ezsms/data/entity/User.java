package com.llsollu.ezsms.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.llsollu.ezsms.data.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id; // key

  String name;

  String userId;

  String password;

  String creationDate;

  public UserDto toDto() {
    return UserDto.builder()
        .id(id)
        .name(name)
        .userId(userId)
        .password(password)
        .creationDate(creationDate)
        .build();
  }

}

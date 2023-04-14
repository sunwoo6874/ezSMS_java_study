package com.llsollu.ezsms.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.llsollu.ezsms.data.dto.SttDto;

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
@Table(name = "stt")
public class Stt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id; // key

  String name;

  String eTime;

  String sttResult;

  String wavURL;

  public SttDto toDto() {
    return SttDto.builder()
        .sttId(id)
        .sttFileName(name)
        .sttEndTime(eTime)
        .sttResultText(sttResult)
        .sttWavURL(wavURL)
        .build();
  }

}

package com.llsollu.ezsms.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import com.llsollu.ezsms.data.entity.Stt;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SttDto {

  @Id
  private Long sttId;

  private String sttFileName;

  private String sttEndTime;

  private String sttResultText;

  private String sttWavURL;

  public Stt toEntity() {
    return Stt.builder()
        .id(sttId)
        .name(sttFileName)
        .eTime(sttEndTime)
        .sttResult(sttResultText)
        .wavURL(sttWavURL)
        .build();
  }

}

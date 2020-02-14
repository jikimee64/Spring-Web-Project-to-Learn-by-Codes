package org.zerock.domain;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AuthVO {

  private String userid;
  private String auth;
  
}

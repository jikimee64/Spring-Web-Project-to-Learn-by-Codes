package org.zerock.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    private int tno;
    private String owner;
    private String grade;
}

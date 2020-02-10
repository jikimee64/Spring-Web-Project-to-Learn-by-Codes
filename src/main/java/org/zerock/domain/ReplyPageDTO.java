package org.zerock.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyPageDTO {
    private int replyCnt;
    private List<ReplyVO> list;
}

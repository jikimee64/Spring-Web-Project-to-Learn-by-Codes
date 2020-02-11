package org.zerock.domain;

import lombok.*;
import org.zerock.service.BoardService;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardVO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updateDate;
    private int replyCnt;

    @Builder //@AllArgsConstructor대신 사용( Builder 사용하는것이 더 안정적)
    public BoardVO(Long bno, String title, String content, String writer, Date regdate, Date updateDate, int replyCnt) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regdate = regdate;
        this.updateDate = updateDate;
        this.replyCnt = replyCnt;
    }
}

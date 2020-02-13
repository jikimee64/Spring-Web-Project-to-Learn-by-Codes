package org.zerock.domain;

import lombok.*;
import java.util.Date;
import java.util.List;

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
    private List<BoardAttachVO> attachList;

    @Builder //@AllArgsConstructor대신 사용( Builder 사용하는것이 더 안정적)
    public BoardVO(Long bno, String title, String content, String writer, Date regdate, Date updateDate, int replyCnt, List<BoardAttachVO> attachList) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regdate = regdate;
        this.updateDate = updateDate;
        this.replyCnt = replyCnt;
        this.attachList = attachList;
    }
}

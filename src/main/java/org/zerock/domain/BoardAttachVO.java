package org.zerock.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardAttachVO {
    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;
    private Long bno;
}

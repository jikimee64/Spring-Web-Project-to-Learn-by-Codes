package org.zerock.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttachFileDTO {
    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
}

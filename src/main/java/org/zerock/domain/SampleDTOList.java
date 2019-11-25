package org.zerock.domain;

import lombok.Data;
import org.apache.ibatis.javassist.tools.rmi.Sample;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleDTOList {
    private List<SampleDTO> list;

    public SampleDTOList() {
        list = new ArrayList<>();
    }
}

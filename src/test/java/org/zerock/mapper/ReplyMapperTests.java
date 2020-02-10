package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Slf4j
public class ReplyMapperTests {

    private Long[] bnoArr = {398L, 397L, 396L, 365L, 364L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    //@Test
    public void testMapper(){
        log.info(String.valueOf(mapper));
    }

    //@Test
    public void testCreate(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();

            //게시물의 번호
            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplyer("replyer" + i);

            mapper.insert(vo);
        });
    }

   // @Test
    public void testRead(){
        Long targetRno = 5L;
        ReplyVO vo = mapper.read(targetRno);
        log.info(String.valueOf(vo));
    }

    //@Test
    public void testDelete(){
        Long targetRno = 1L;
        mapper.delete(targetRno);
    }

    //@Test
    public void testUpdate(){
        Long targetRno = 10L;
        ReplyVO vo = mapper.read(targetRno);
        vo.setReply("Update Reply ");
        int count = mapper.update(vo);
        log.info("UPDATE COUNT: " + count);
    }

    //@Test
    public void testList(){
        Criteria cri = new Criteria();
        //398L
        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
        replies.forEach(reply -> log.info(String.valueOf(reply)));
    }

    @Test
    public void testList2() {
        Criteria cri = new Criteria(2, 10);
        List<ReplyVO> replies = mapper.getListWithPaging(cri, 398L);
        replies.forEach(reply -> log.info(String.valueOf(reply)));
    }

}

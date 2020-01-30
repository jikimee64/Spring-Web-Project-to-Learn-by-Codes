package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Slf4j
public class BoardMapperTests<boardMapper> {
    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    //@Test
    public void testGetList() {
        mapper.getList().forEach(board -> log.info(String.valueOf(board)));
    }

    //@Test
    public void testInsert(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        mapper.insert(board);
        log.info(String.valueOf(board));
    }

    //@Test
    public void testInsertSelectKey() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글 select key");
        board.setContent("새로 작성하는 내용 select key");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);
        log.info(String.valueOf(board));
    }

    //@Test
    public void testRead() {
        //존재하는 게시물 번호로 테스트
        BoardVO board = mapper.read(21L);
        log.info(String.valueOf(board));
    }

    //@Test
    public void testDelete() {
        log.info("DELETE COUNT: " + mapper.delete(40L));
    }

    //@Test
    public void testUpdate() {
        BoardVO board = new BoardVO();
        //실행전 존재하는 번호인지 확인할 것
        board.setBno(45L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);
        log.info("UPDATE COUNT: " + count);
    }
        //@Test
        public void testPaging() {
            Criteria cri = new Criteria();
            //10개씩 3페이지
            cri.setPageNum(1);
            cri.setAmount(10);
            List<BoardVO> list = mapper.getListWithPaging(cri);
            list.forEach(board -> log.info(String.valueOf(board.getBno())));
        }

        @Test
    public void testSearch() {
        Criteria cri = new Criteria();
        cri.setKeyword("수정");
        cri.setType("TC");

        List<BoardVO> list = mapper.getListWithPaging(cri);
        list.forEach(board -> log.info(String.valueOf(board)));
    }

}

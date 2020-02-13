package org.zerock.controller;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board/*")
//@AllArgsConstructor
public class BoardController {
    private BoardService service;

    @Builder
    public BoardController(BoardService service) {
        this.service = service;
    }

//    @GetMapping("/list")
//    public void list(Model model) {
////        log.info("list");
//        model.addAttribute("list", service.getList());
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));
        //model.addAttribute("pageMaker", new PageDTO(cri, 123));
        int total = service.getTotal(cri);
        log.info("total: " + total);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    //등록 페이지를 볼 수 있게 해주는 역할
    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
//    @RequestMapping(value="/register" , method = {RequestMethod.GET, RequestMethod.POST})
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("=============테스트 시작");
        log.info("register: " + board);
        if(board.getAttachList() != null){
            board.getAttachList().forEach(attach -> log.info(String.valueOf(attach)));
        }
        log.info("=====================테스트 시작");
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
        // log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        //log.info("modify:" + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }

//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("remove..." + bno);

        List<BoardAttachVO> attachList = service.getAttachList(bno);

        if (service.remove(bno)) {
            //delete Attach Files
            deleteFiles(attachList);
            rttr.addFlashAttribute("result", "success");
        }
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list"  + cri.getListLink();
    }

    @GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {
        log.info("getAttachList " + bno);
        return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
    }

    private void deleteFiles(List<BoardAttachVO> attachList) {
        if(attachList == null || attachList.size() == 0){
            return;
        }
        log.info("delete attach files..............");
        log.info(String.valueOf(attachList));

        attachList.forEach(attach -> {
            try{
                Path file = Paths.get("D:\\kimwoocheol\\PersonelStudy\\IntelliJ\\SpringMavenProject_Java\\out\\artifacts\\SpringMavenProject_war_exploded\\upload\\"+
                        attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());

                Files.deleteIfExists(file);

                if(Files.probeContentType(file).startsWith("image")) {
                    Path thumbNail = Paths.get("D:\\kimwoocheol\\PersonelStudy\\IntelliJ\\SpringMavenProject_Java\\out\\artifacts\\SpringMavenProject_war_exploded\\upload\\"+attach.getUploadPath()+"\\s_" + attach.getUuid()+"_"+attach.getFileName());
                    Files.delete(thumbNail);
                }
            } catch (IOException e) {
                log.error("delete file error" + e.getMessage());
            } //end catch
        }); //end foreachd
    }

}

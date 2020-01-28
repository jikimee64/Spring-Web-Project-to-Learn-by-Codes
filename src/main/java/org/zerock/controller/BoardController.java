package org.zerock.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

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
    public void list(Criteria cri, Model model){
        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));
    }

    //등록 페이지를 볼 수 있게 해주는 역할
    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
//    @RequestMapping(value="/register" , method = {RequestMethod.GET, RequestMethod.POST})
    public String register(BoardVO board, RedirectAttributes rttr) {
       // log.info("register: " + board);
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"get", "/modify"})
    public void get(@RequestParam("bno") Long bno, Model model) {
       // log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        //log.info("modify:" + board);

        if(service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        //log.info("remove..." + bno);
        if(service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

}

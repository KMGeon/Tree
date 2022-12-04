package kr.or.ddit.Controller;

import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MainController {
    @Autowired
    MemberService memberService;
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String Main(){
        return "Main/main";
    }

    @RequestMapping(value = "/register" ,method = RequestMethod.GET)
    public String register(){
        return "Main/register";
    }
    @RequestMapping(value = "/registerPost" , method = RequestMethod.POST)
    public String registerPost(@ModelAttribute MemberVO memberVO){
        int result = this.memberService.registerMember(memberVO);
        if(result==1) { // 회원가입 성공 메세지
            return "redirect:/";
        }else {
            return "redirect:/register";
        }
    }
    //이메일 중복체크
    @GetMapping("/memRegisterCheck")
    public @ResponseBody int registerCheck(@RequestParam String memEmail){
        int result = this.memberService.registerCheck(memEmail);
        log.info("중복체크:::"+result);
        return result;
    }
}

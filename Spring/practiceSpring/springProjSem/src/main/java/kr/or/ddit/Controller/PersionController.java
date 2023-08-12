package kr.or.ddit.Controller;

import kr.or.ddit.service.PersonService;
import kr.or.ddit.vo.PersonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class PersionController {

    @Autowired
    PersonService personService;


    @RequestMapping(value = "/person" , method = RequestMethod.GET)
    public String MyController(){
        return "person/Main";
    }
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String Join(){
        return "person/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String Join2(@ModelAttribute PersonVO personVO , @RequestParam String password, @RequestParam String password2){
        int result = this.personService.joinPerson(personVO);
        if(result>0&& password.equals(password2)){
            log.info("회원가입 완료");
            log.info("result값 확인하기 :::"+result);
            return  "person/Main";
        }else {
            return "redirect:/person";
        }
    }

    @GetMapping("/memRegisterCheck")
    public @ResponseBody int registerCheck(@RequestParam String email){
        int result = this.personService.registerCheck(email);
        return result;
    }
}

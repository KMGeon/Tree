package kr.or.ddit.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import freemarker.template.utility.StringUtil;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
public class MemberController {
	@Autowired
	MemberService memberService;

	// 요청 파라미터 : register?userId = kim & passwd = 1234
	@GetMapping("/register")
	public String registerByParameter(String userId, String passwd, Model model) {
		log.info("userid" + userId);
		log.info("passwd" + passwd);
		return "success";
	}

	// 요청 uri : /register/hongkd
	// 경로(pathVariable)
	@RequestMapping("/register/{userId}")
	public String registerByPath(@PathVariable String userId) {
		log.info("=======================================================");
		log.info("userId" + userId);
		return "success";
	}

	@GetMapping("/register01")
	public String register01() {
		return "register";
	}

	@PostMapping("/register01")
	public String register01Post(String userId, String passwd, String coin, Model m) {
		log.info("=========================================================");
		log.info(userId);
		log.info(passwd);
		log.info(coin);
		m.addAttribute("asd", userId);
		m.addAttribute("zxc", passwd);
		m.addAttribute("qwe", coin);

		return "register02";
	}

	@GetMapping("/register/register03")
	public String register03() {
		return "register/register03";
	}

	@PostMapping("/register/register03")
	public String register03Post(String userId, String password, int coin, Model m, HttpServletRequest req) {
		log.info("userid", userId);
		log.info("password", password);
		log.info("coin", coin);
		m.addAttribute("userId", userId);
		log.info("dsadasda" + req.getRequestURI());
		log.info("dsadasda" + req.getRequestURL());

		return "register/success";
	}

	// url 경로 상의 경로변수가 여러 개일 때 @pathvariable 어노테이션을 사용하여 특정한 경로 변수명을 지정
	// 요청 uri : /register/register03/a001/100
	@RequestMapping(value = "/register/register03/{userId}/{coin}")
	public String register03ByPath(@PathVariable("userId") String userId, @PathVariable("coin") int coin,
			HttpServletRequest req) {
		log.info("========================PathVariavle=======================================");
		log.info("userId" + userId);
		log.info("coin" + coin);
		log.info("path" + req.getRequestURL());
		return "register/success";
	}

	@GetMapping("/register/register04")
	public String register04ByParam() {
		return "register/register04";
	}

	@PostMapping("/register/register04")
	public String register04Post(@RequestParam(value = "userId") String id,
			@RequestParam(value = "password") String pwd, String coin, Model m) {

		log.info(id);
		log.info(pwd);
		log.info(coin);
		m.addAttribute("id", id);
		return "register/success";
	}

	// userId=a001&birth=2022/12/03
	@GetMapping("/register/registerByGet01")
	public String registerByGet01(String userId, Date birth) {
		log.info("userId: " + userId);
		log.info("birth: " + birth);
		return "register/success";
	}

	@GetMapping("/register/registerByGet02")
	public String registerByGet02(MemberVO memvo) {
		log.info("userId: " + memvo.getUserId());

		return "register/success";
	}

	@GetMapping("/register/register05")
	public String register05Get() {
		return "register/register05";
	}

	@PostMapping("/register/register05")
	public String register05Post(@ModelAttribute MemberVO memberVO, int coin, ArrayList<String> cars,
			AddressVO addressVO, Model m) {
		log.info("membervo" + memberVO.toString());

		// 보유 자동차를 (string [] cars -> car)
		String car = StringUtils.join(memberVO.getCars(), ",");
		memberVO.setCar(car);
		// 취미를 (string[] hobbyList -> hobby)
		String hobby = StringUtils.join(memberVO.getHobbyList(), ",");
		memberVO.setHobby(hobby);

		log.info("나중 memberVO" + memberVO.toString());

		int result = this.memberService.memberInsert(memberVO);
		m.addAttribute("result", result);
		return "register/success";
	}

}

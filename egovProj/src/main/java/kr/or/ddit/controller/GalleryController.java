package kr.or.ddit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.service.GalleryService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping(value = "/gallery")
@Slf4j
public class GalleryController {
	@Autowired
	GalleryService galleryService;

	@Autowired
	AttachVO attachVO;

	@GetMapping("/list")
	public String galleryWelcome(@ModelAttribute BookVO bookVO, Model model) {
		bookVO = this.galleryService.list(bookVO);
		model.addAttribute("bookVO", bookVO);
		log.info("testtesttest", bookVO);
		return "gallery/list";
	}

	// 도서 목록 가져와서 select에 추가하기
	// json 데이터로 리턴
	@GetMapping("/bookList")
	public @ResponseBody List<BookVO> bookList(Model model) {
		List<BookVO> list = this.galleryService.bookList();
		return list;
	}

	/*
	 * 요청 url : /gallery/updatePost 방식:post 청부이미지를 변경함 파라미터 :
	 * attachVO{"userNo":"3","seq":"5"} + 파일객체(name은 uploadFile) ajax로 요청함
	 */
	// 매개변수 multipart의 이름은 list의 이름과 동일하게 해주어야 한다.
	@ResponseBody
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public AttachVO updatePos2t(MultipartFile[] uploadFile, @ModelAttribute AttachVO attachVO) {
		log.info("asdadad : " + uploadFile + ", attachVO : " + attachVO);

		// 1. 업로드 폴더 설정
		String uploadFolder = "C:\\Users\\PC-21\\eGovFrameDev-3.10.0-64bit\\workspace\\egovProj\\src\\main\\webapp\\resources\\upload";

		// 2. 연월일 폴더 생성
		File uploadPath = new File(uploadFolder, getFolder());
		System.out.println("upload Path: " + uploadPath);
		// 3. 만약 연/월/일 해당 폴더가 없으면 생성
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		// 4.파일 배열로부터 파일을 하나씩 가져와보자.
		// multipartfile[]uploadFile
		for (MultipartFile multipartFile : uploadFile) {
			log.info("============================");
			log.info("upload file name: " + multipartFile.getOriginalFilename());
			log.info("upload file size" + multipartFile.getSize());
			log.info("============================");
			// 파일 구분하기 위해서 원래의 파일이름을 변수에 추가한다.
			String uploadFileName = multipartFile.getOriginalFilename();
			// 5.같은날 같은 이미지 업로드 시 중복방지
			UUID uuid = UUID.randomUUID();
			// 6.원래 파일 이름과 구분하기 위해서 _를 추가한다.
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			// 7.같은 날 같은 이미지 업로드시 파일 중복을 막는다.끝 ↑

			// 8.File객체 설계(복사할 대상 경로 , 파일명) -> 여기서 복사x 설계만 하는거임
			File saveFile = new File(uploadPath, uploadFileName);

			try {
				// 9.위에 설계를 기반으로 파일 복사를 하겠다.
				multipartFile.transferTo(saveFile);

				// 10(밑에서 bool check).썸네일 처리 (썸네일은 이미지만 가능하니깐 여기서 이미지만 체크한다.)
				// 성공은 1을 리턴 실패하면 0을 리턴한다.
				if (checkImageType(saveFile)) {// 이미지라면
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					// 11.썸네일 생성(가로 세로 100px 썸네일을 닫는다.
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				/*
				 * UPDATE ATTACH SET FILENAME ='/2022/11/16/asdasdda.jpg' WHERE USER_NO = 3 AND
				 * SEQ=5;
				 */

				// 폴더이름 + 파일이름을 더하고 양식에 맞게 바꾼다.
				String filename = "/" + getFolder().replace("\\", "/") + "/" + uploadFileName;
				log.info("filename: :" + filename);

				attachVO.setFilename(filename);

				int result = this.galleryService.updateImg(attachVO);
				log.info("result: \\\\\\\\\\" + result);

				return attachVO;
			} catch (IllegalStateException e) {
				log.error("에러로그", e.getMessage());
				return null;
			} catch (IOException e1) {
				// TODO: handle exception
				log.error("에러로그", e1.getMessage());
				return null;
			}
		}

		return null;
	}

	public String getFolder() {
		// 2022-11-16형식(format)지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 객체 생성
		Date date = new Date();
		// 2022 - 11- 166
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}

	// 10-2 . 썸네일 이미지 확인하는 메소드
	public boolean checkImageType(File file) {
		// Mine타입(사진) 알아냄 -> .jpeg / .jpg
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			log.info("contentType: " + contentType);
			// image/jpeg는 image로 시작함 -> 참
			return contentType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("파일 썸네일 체크하는 부분에서 에러가 발생했다.");
		}
		// 이 파일이 이미지가 아닐 경우
		return false;
	}// end

	// @RequestBody : 요청 파라미터 타입/보내는 타입이 content:application/json/
	// json일때 map또는 vo로 받음 json데이터로 리턴할 때 사용한다.
	// 이미지 삭제
	@PostMapping("/deletePost")
	public @ResponseBody String deletePost(@RequestBody AttachVO attachVO) {
		log.info("attachVO" + attachVO);

		int test = this.galleryService.deletePost(attachVO);
		// delete from attach where user_no = 3 and seq =9
		log.info("result" + test);
		String result = Integer.toString(test);
		return result;
	}

	/**
	 * 22.11.17 이미지 다중 등록 요청 uri :/gallery/regist
	 */

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registGet(Model model, BookVO bookVO) {

		model.addAttribute("bodyTitle", "이미지등록");

		return "gallery/regist";
	}

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<BookVO> registPost(Model model, @RequestBody BookVO bookVO) {
		log.info("bookvo" + bookVO);
		List<BookVO> bookVOList = this.galleryService.searchBook(bookVO);

		return bookVOList;
	}

	/**
	 * 요청URI : /gallery/uploadAjaxAction 요청파라미터 : uploadFile[], bookId => 폼으로 오므로
	 * RequestBody는 안씀 요청 방식 : post 응답데이터 : {"bookId":"3","status":"1"}
	 */
	//다중 파일 insert하기=================================================
	@ResponseBody
	@PostMapping("/uploadAjaxAction")
	public Map<String, String> uploadAjaxAction(MultipartFile[] uploadFile, @RequestParam String bookId) {
		log.info("bookId : " + bookId);

		String uploadFolder = "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\egovProj\\src\\main\\webapp\\resources\\upload";

		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload Path : " + uploadPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		String uploadFileName = "";
		int seq = this.galleryService.getSeq(bookId);

		List<AttachVO> attachVOList = new ArrayList<AttachVO>();

		for (MultipartFile multipartFile : uploadFile) {
			AttachVO attachVO = new AttachVO();
			log.info("-----------------");
			log.info("upload File Name : " + multipartFile.getOriginalFilename());
			log.info("upload File Size :" + multipartFile.getSize());

			uploadFileName = multipartFile.getOriginalFilename();

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			File saveFile = new File(uploadPath, uploadFileName);

			try {

				multipartFile.transferTo(saveFile);

				if (checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}

				String filename = "/" + getFolder().replace("\\", "/") + "/" + uploadFileName;
				log.info("filename : " + filename);

				attachVO.setUserNo(bookId);

				attachVO.setSeq(seq++);

				attachVO.setFilename(filename);

				attachVO.setFilesize(Long.valueOf(multipartFile.getSize()).intValue());

				attachVOList.add(attachVO);

			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}
		int rslt = this.galleryService.uploadAjaxAction(attachVOList);

		Map<String, String> map = new HashMap<String, String>();
		map.put("bookId", bookId);
		map.put("status", rslt + "");

		return map;

	}

}

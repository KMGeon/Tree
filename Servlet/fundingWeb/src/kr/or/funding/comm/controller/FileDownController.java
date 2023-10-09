package kr.or.funding.comm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.comm.service.AtchFileServiceImpl;
import kr.or.funding.comm.service.IAtchFileService;
import kr.or.funding.comm.vo.AtchFileVO;

@WebServlet("/filedown.do")
public class FileDownController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long fileId = req.getParameter("fileId") != null ? Long.parseLong(req.getParameter("fileId")) : -1;
		int fileSn = req.getParameter("fileSn") != null ? Integer.parseInt(req.getParameter("fileSn")) : 1;

		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVO fileVO = new AtchFileVO();
		fileVO.setAtchFileId(fileId);
		fileVO.setFileSn(fileSn);
		fileVO = fileService.getAtchFileDetail(fileVO);

		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode(fileVO.getOrignlFileNm(), "UTF-8").replaceAll("//+", "%20") + "\"");

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileVO.getFileStreCours()));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());

		int readBytes = 0;

		while ((readBytes = bis.read()) != -1) {
			bos.write(readBytes);
		}

		bis.close();
		bos.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

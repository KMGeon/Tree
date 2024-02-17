package com.example.mybatis.application;

import com.example.mybatis.controller.dto.request.InsertBoardRequest;
import com.example.mybatis.controller.dto.response.BoardInfoResponseDTO;
import com.example.mybatis.domain.Board;
import com.example.mybatis.repository.BoardDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final BoardDao boardDao;
    private final SseService sseService;


    public List<BoardInfoResponseDTO> getBoardInfos() {
        List<BoardInfoResponseDTO> boardInfoDTOList = new ArrayList<>();

        boardDao.getList().forEach(board -> {
            logger.info("====== /board [" + getClass().getSimpleName() + ".getBoardList()] content :  " + board.getContent());
            logger.info("====== /board [" + getClass().getSimpleName() + ".getBoardList()] title :  " + board.getTitle());
            logger.info("====== /board [" + getClass().getSimpleName() + ".getBoardList()] writer :  " + board.getWriter());
            logger.info("====== /board [" + getClass().getSimpleName() + ".getBoardList()] count :  " + board.getCount());

            BoardInfoResponseDTO boardInfoDTO = BoardInfoResponseDTO.from(board);
            boardInfoDTOList.add(boardInfoDTO);
        });

        return boardInfoDTOList;
    }

    @Transactional
    public Integer insertBoard(InsertBoardRequest boardRequest) {
        String title = boardRequest.getTitle();
        String content = boardRequest.getContent();
        String writer = boardRequest.getWriter();
        logger.info("======  [" + getClass().getSimpleName() + ".insertBoard()] title :  " + title);
        logger.info("======  [" + getClass().getSimpleName() + ".insertBoard()] content :  " + content);
        logger.info("======  [" + getClass().getSimpleName() + ".insertBoard()] writer :  " + writer);
        int result = boardDao.insertBoard(title, content, writer);


        sseService.send(result);

        return result;
    }

    public Board boardDetail(int id) {
        Board detail = boardDao.detail(id);

        return detail;
    }

    public int delte(Long id) {
        boardDao.delete(id);
        return 0;
    }
}

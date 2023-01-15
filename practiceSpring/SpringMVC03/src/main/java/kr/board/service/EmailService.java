package kr.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import kr.board.dto.EmailDTO;

public interface EmailService {
    public void sendMail(EmailDTO dto);
}

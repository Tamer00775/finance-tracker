package kz.sdu.finance_tracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, Integer randomInt) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sdufinancetracker@yandex.ru");
        message.setTo(to);
        message.setSubject("Восстановление пароля FinanceTracker!");

        message.setText("""
            Вы запросили восстановление пароля.

            Пожалуйста, введите временный пароль в приложении FinanceTracker:

            %s

            Если вы не запрашивали восстановление пароля, просто проигнорируйте это письмо.

            С уважением,
            Finance Tracker SDU! 
            """.formatted(randomInt));

        mailSender.send(message);
    }
}

package com.example.foodis.api.service;

import com.example.foodis.api.io.ContactRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendHtmlMail(ContactRequest request) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("orhantrkmn15@gmail.com");
        helper.setTo(request.getEmail());
        helper.setSubject("🍔 Foodies - Yeni İletişim Mesajı");

        String htmlContent = """
                <html>
                  <body style="font-family:Arial, sans-serif; background-color:#f6f9fc; padding:30px;">
                    <div style="max-width:600px; margin:auto; background:white; border-radius:12px; padding:30px; box-shadow:0 3px 12px rgba(0,0,0,0.1);">
                      <h2 style="color:#FF6B6B;">📩 Yeni Mesaj Geldi!</h2>
                      <p><b>Gönderen:</b> %s</p>
                      <p><b>E-posta:</b> %s</p>
                      <hr style="border:none; border-top:1px solid #eee; margin:20px 0;">
                      <p style="font-size:16px; color:#333;">%s</p>
                      <hr style="border:none; border-top:1px solid #eee; margin:20px 0;">
                      <p style="font-size:13px; color:#999;">© 2025 Foodies | Lezzetle kalın 🍕</p>
                    </div>
                  </body>
                </html>
                """.formatted(request.getFirstName().concat(" ").concat(request.getLastName()), request.getEmail(), request.getMessage());

        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}

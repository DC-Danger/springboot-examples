package com.hz.learnboot.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from; //读取配置文件中的参数
    @Value("${spring.mail.nickname}")
    private String fromName;

    private String to = "xxx@163.com";
    private String toName = "某某";

    /**
     * 文本邮件
     */
    @Test
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("简单邮件");
        message.setText("简单邮件的简单内容");
        mailSender.send(message);
    }

    /**
     * HTML邮件
     */
    @Test
    public void sendHtmlMessage() {
        MimeMessage message;
        message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText(fromName) + "<" + from + ">");// 创建邮件发送者地址
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText(toName) + "<" + to + ">");// 创建邮件发送者地址
            helper.setTo(toAddress);
            helper.setSubject("Html邮件");
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:red;'>红色字</p>");
            //第二个参数指定发送的是HTML格式
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    /**
     * 附件邮件
     */
    @Test
    public void sendAttachmentsMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText(fromName) + "<" + from + ">");// 创建邮件发送者地址
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText(toName) + "<" + to + ">");// 创建邮件发送者地址
            helper.setTo(toAddress);
            helper.setSubject("带附件的邮件");
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>带附件的邮件内容</h1>")
                    .append("<p style='color:red;'>红色字</p>");
            //第二个参数指定发送的是HTML格式
            helper.setText(sb.toString(), true);
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/forbidden.jpg"));
            helper.addAttachment("图片.jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    /**
     * 带图片的邮件
     */
    @Test
    public void sendInlineMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText(fromName) + "<" + from + ">");// 创建邮件发送者地址
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText(toName) + "<" + to + ">");// 创建邮件发送者地址
            helper.setTo(toAddress);
            helper.setSubject("带静态资源的邮件");
            //第二个参数指定发送的是HTML格式
            helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:forbidden' /></body></html>", true);

            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/forbidden.jpg"));
            helper.addInline("forbidden", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

}

package com.leeyom.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEmailApplicationTests {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 测试发送简单邮箱
     */
    @Test
    public void testSimpleEmail() {
        mailService.sendSimpleMail("leeyomwang@qq.com", "测试spring boot的发送简单邮件", "1111111");
    }

    /**
     * 测试发送html邮件
     */
    @Test
    public void testHtmlEmail() {
        String context = "<html>\n" +
                "<body>\n" +
                "    <h3>今天晚上吃啥呢？</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("leeyomwang@qq.com", "测试spring boot的发送html邮件", context);
    }

    /**
     * 测试发送带附件的邮件
     */
    @Test
    public void testAttachmentFileEmail() {
        String filePath = "/Users/leeyom/Downloads/consumer.xml";
        mailService.sendAttachmentsMail("leeyomwang@qq.com", "测试spring boot的发送带附件的邮件", "111111111！", filePath);
    }

    /**
     * 测试发送模板邮件
     */
    @Test
    public void testSendTemplatesEmail() {
        Context context = new Context();
        context.setVariable("id", "1");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("leeyomwang@qq.com", "测试spring boot的发送模板邮件", emailContent);
    }

}

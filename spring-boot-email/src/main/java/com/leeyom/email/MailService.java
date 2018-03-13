package com.leeyom.email;

public interface MailService {
    /**
     * 发送简单邮件
     * @param to      接收人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html格式的邮件
     * @param to      接收人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to       接收人
     * @param subject  主题
     * @param content  内容
     * @param filePath 文件路径
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
}

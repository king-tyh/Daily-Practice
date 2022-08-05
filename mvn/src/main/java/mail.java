import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class mail {
    public static void main(String[] args) throws MessagingException, IOException {
        // 准备SMTP登录信息session=======================================================
        // 服务器地址:
        String smtp = "smtp.qq.com";
        // 登录用户名:
        String username = "2960054048@qq.com";
        String recevier = "1292989480@qq.com"
        // 登录口令:  此处需要授权码并非密码
        String password = "";
        // 连接到SMTP服务器587端口:

        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", "587"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        // 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // 设置debug模式便于调试:
        session.setDebug(true);

        // 准备邮件消息体message=============================================================
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress(username));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recevier));
        // 设置邮件主题:
        message.setSubject("Hello", "UTF-8");
        // 设置邮件正文:--------------------------------------------
        Multipart multipart = new MimeMultipart();
        // 添加text:
        BodyPart textpart = new MimeBodyPart();
        textpart.setContent("<h1>你好</h1><a href='http://www.baidu.com'>百度</a><img src='cid:img01'/>", "text/html;charset=utf-8");
        multipart.addBodyPart(textpart);
/*        // 添加嵌入图片image:
        BodyPart imagepart = new MimeBodyPart();
        File file = new File("D:\\workspaces\\Email\\src\\main\\resources\\111.jpg");
        imagepart.setFileName(file.getName());
        InputStream input = new FileInputStream(file);
        imagepart.setDataHandler(new DataHandler(new ByteArrayDataSource(input, "image/jpg")));
        imagepart.setHeader("Content-ID", "<img01>");
        multipart.addBodyPart(imagepart);
        // 添加附件
        BodyPart filepart = new MimeBodyPart();
        File file2 = new File("D:\\workspaces\\Email\\src\\main\\resources\\readme.txt");
        filepart.setFileName(file.getName());
        InputStream input2 = new FileInputStream(file2);
        filepart.setDataHandler(new DataHandler(new ByteArrayDataSource(input2, "application/octet-stream")));
        multipart.addBodyPart(filepart);
        // 设置邮件内容为multipart:
        message.setContent(multipart);
        */
        // 发送邮件:============================================================================
        Transport.send(message);
    }
}
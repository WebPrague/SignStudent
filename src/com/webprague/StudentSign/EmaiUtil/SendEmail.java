package com.webprague.StudentSign.EmaiUtil;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Qixuan.Chen
 */
public class SendEmail {

    public static String HOST ;
    public static String PROTOCOL;
    public static int PORT;
    public static String FROM;//发件人的email
    public static String PWD;//  在发件人密码

    //    public static ConfigReader reader = null;
    static{
//    	reader = new ConfigReader();
//    	Information information=new Information();
        HOST="smtp.139.com";
        PROTOCOL="smtp";
        PORT=25;
        FROM="cqhgt@139.com";
        PWD="tbictpx2ii";
//    	HOST = reader.read("mailHost");
//    	PROTOCOL = reader.read("mailProtocol");
//    	PORT = reader.readInt("mailPort");
//    	FROM = reader.read("mailFrom");
//    	PWD=reader.read("mailPwd");
    }
    /**
     * 获取Session
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , "true");

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

//    public static void sendActivateEmail(String toEmail , String url) {
//    	System.out.println("toemail"+toEmail);
//    	String content="<div align=\"center\"><h3>Yard Sell, your mobile market!<h3><br></div><h4>Good man, hello:<h4><br><br><h5>Please click the link to activate your account：<a href='"+url+"'>"+url+"</a><h5><br>You also can copy the Internet address to your browser, you should click it in 48 hours.<h5>";
//        Session session = getSession();
//        try {
//            System.out.println("--send--"+content);
//            // Instantiate a message
//            Message msg = new MimeMessage(session);
//
//            //Set message attributes
//            msg.setFrom(new InternetAddress(FROM));
//            InternetAddress[] address = {new InternetAddress(toEmail)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject("To activate your account:");
//            msg.setSentDate(new Date());
//            msg.setContent(content , "text/html;charset=utf-8");
//
//            //Send the message
//        	System.out.println("host:"+HOST+":::"+PROTOCOL+":::"+PORT+":::"+FROM+":::"+PWD);
//            Transport.send(msg);
//        }
//        catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }

    public static boolean sendFotgotPasswordEmail(String toEmail ,String username, String passwd){
        //String url = "http://" + baseUrl + "/reset_passwd?sk=" + sk + "&email=" + toEmail;
        String content="<div align=\"center\"><h5>学生助理签到系统-密码找回<h5><br></div>"
                + "<div align=\"center\"><h3>您好，"+username+"！</h3></div>"
                + "<div align=\"center\"><h3>您的密码是："+passwd+"</h3></div>"
                +"<div align=\"center\"><h3>请妥善保管好您的密码，防止泄露，防止非法人员不合法操作，谢谢合作！</h3></div>";
        //String content = "测试邮件";
        Session session = getSession();
        try {
            //System.out.println("--send--"+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("学生签到管理系统-找回密码");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }
}
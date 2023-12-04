
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class SendMail {

    private String msgSubject;  //Mail Subject
    private String msgText;     //Mail Massage
    private String userName;   // Reasever Mail (User Mail)
    private final static String PASSSWORD = "myax kkxr apwj wpgh";  //Password of the Google Account (gmail) account Generate
    private final static String SENDER = "sohaninfobeans4@gmail.com";  // Sender gmail addresss

    public static void main(String[] args) {
        SendMail email = new SendMail();
        int otp = otpGenerat();
        email.setMsgSubject("Account varification in Swastik..."); // set Subject
        email.setMsgText(" Dear swastik user. ,\n the one time OTP to reset your password at (swastik Account) is " + otp); // set Message
        email.sendEmailMessage(email); // Method Call for Send Mail

//        ----------------------------------------------------
        Scanner sc = new Scanner(System.in);
        int fillOtp = sc.nextInt();
        if (otp == fillOtp) {
            System.out.println("Registration Seccsess");
        } else {
            System.out.println("Registration Fail");
        }
    }

    private void sendEmailMessage(SendMail email) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SendMail.SENDER, SendMail.PASSSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(SendMail.SENDER)); //Set from address of the email
            message.setContent(email.getMsgText(), "text/html"); //set content type of the email

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getUserName()));   //Set User Mail

            message.setSubject(email.getMsgSubject()); //Set email message subject
            Transport.send(message); //Send email message

            System.out.println("sent email successfully!");

        } catch (MessagingException e) {

            System.out.println(e);
            System.out.println("Mail Not Send"); //if  Get Any Exception

        }
    }

    public static int otpGenerat() {
        int random = (int) (Math.random() * 999999);
        System.out.println("rendom num is: " + random);
        return random;
    }

    public String getMsgSubject() {
        return msgSubject;
    }

    public void setMsgSubject(String msgSubject) {
        this.msgSubject = msgSubject;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

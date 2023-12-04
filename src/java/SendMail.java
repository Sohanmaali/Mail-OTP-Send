
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

    private String msgSubject = new String();
    private String msgText = new String();
    private final String USER_NAME = "";   // Reasever Mail (User Mail)
    final String PASSSWORD = "";  //Password of the Goole(gmail) account
    final static String SENDER = "";  // Sender gmail addresss

    public static void main(String[] args) {
        SendMail email = new SendMail();
        //Sending test email
        int otp = otpGenerat();
        Scanner sc = new Scanner(System.in);
        email.createAndSendEmail(SENDER, "Account varification in Swastik...", " Dear swastik user. ,\n the one time OTP to reset your password at (swastik Account) is " + otp + ".\n \n This OTP will expire in 5 minutes. ");
        int fillOtp = sc.nextInt();
        if (otp == fillOtp) {
            System.out.println("Registration Seccsess");
        } else {
            System.out.println("Registration Fail");
        }
    }

    public void createAndSendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        sendEmailMessage();
    }

    private void sendEmailMessage() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER)); //Set from address of the email
            message.setContent(msgText, "text/html"); //set content type of the email

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(USER_NAME));

            message.setSubject(msgSubject); //Set email message subject
            Transport.send(message); //Send email message

            System.out.println("sent email successfully!");

        } catch (MessagingException e) {

            System.out.println(e);
        }
    }

    public static int otpGenerat() {
        int random = (int) (Math.random() * 999999);
        System.out.println("rendom num is: " + random);
        return random;
    }
}

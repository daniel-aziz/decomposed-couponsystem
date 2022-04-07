package com.jb.couponSystem.Services;


import com.jb.couponSystem.Beans.*;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.SystemErrMsg;
import com.jb.couponSystem.Repositories.CategoryEntityRepository;
import com.jb.couponSystem.Repositories.CouponRepository;
import com.jb.couponSystem.Repositories.CustomerRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
    private final CategoryEntityRepository categoryEntityRepository;


    /**
     * register a customer to the DB
     *
     * @param customer receives a customer bean
     * @throws CouponSystemException if a customer already exist in db
     */
    public void register(Customer customer) throws CouponSystemException {
        if (!(customerRepository.existsByEmail(customer.getEmail()))) {
            customerRepository.save(customer);
        } else throw new CouponSystemException(SystemErrMsg.CUSTOMER_IS_EXIST);
    }

    /**
     * gets all the coupons from the DB
     *
     * @return List of Coupons
     */
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    /**
     * gets all the coupons from the DB by category
     *
     * @param category
     * @return list of coupons
     */
    public List<Coupon> getAllCoupons(Category category) {
        return couponRepository.findByCategory(category);
    }

    /**
     * gets all the coupons from the DB by max price
     *
     * @param price
     * @return List of Coupons
     */
    @Deprecated
    public List<Coupon> getAllCoupons(double price) {
        return couponRepository.findByPriceLessThanEqual(price);
    }

    /**
     * gets all the coupons from the DB by price Min and price Max
     *
     * @param priceMin
     * @return List of Coupons
     */
    public List<Coupon> getAllCoupons(double priceMin, double priceMax) {
        return couponRepository.findByPriceBetween(priceMin,priceMax);
    }

    /**
     * gets all the coupon categories for the db
     * @return List<CategoryEntity>
     */
    public List<CategoryEntity> getAllCategories() {
        return categoryEntityRepository.findAll();
    }

    /**
     * Send an email to admin from website via contact form
     * @param contactDetails
     * @return
     */
    public void contactAdmin(ContactDetails contactDetails) throws CouponSystemException {
        // sets the message with HTML page
        contactDetails.setMessage(
                "<h1>Sender: "+ contactDetails.getName() + "</h1>" +
                        "\n" +
                        "<h4>Email: " + contactDetails.getEmail() + "</h4>" +
                        "\n" +
                        "<div>" +
                        "<p>" +
                        "Message: " +
                        "<br/>" +
                        contactDetails.getMessage() +
                        "</p>" +
                        "</div>"
        );

        // properties
        String adminEmail = "daniel.dev.contact@gmail.com";
        String senderEmail = contactDetails.getEmail();
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        // Get the Session object and set username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("daniel.dev.contact@gmail.com", "*Ssgm2Ldvg");
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adminEmail));
            // Set Subject: header field
            message.setSubject(contactDetails.getSubject());
            // Now set the actual message
            message.setContent(contactDetails.getMessage(),"text/html");
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            throw new CouponSystemException(mex.getMessage());
        }

    }
}

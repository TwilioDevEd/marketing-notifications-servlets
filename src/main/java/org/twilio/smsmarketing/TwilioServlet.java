package org.twilio.smsmarketing;

import com.twilio.sdk.verbs.*;
import org.twilio.smsmarketing.models.Subscriber;
import org.twilio.smsmarketing.repositories.SubscribersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwilioServlet extends HttpServlet {

    public static final String SUBSCRIBE_COMMAND = "subscribe";
    public static final String UNSUBSCRIBE_COMMAND = "unsubscribe";

    private final SubscribersRepository repository;

    @SuppressWarnings("unused")
    public TwilioServlet() {
        this(new org.twilio.smsmarketing.repositories.SubscribersRepository());
    }

    public TwilioServlet(org.twilio.smsmarketing.repositories.SubscribersRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String phone = request.getParameter("From");
        String body = request.getParameter("Body");

        String output;

        try {
            Subscriber subscriber = repository.findByPhoneNumber(phone);
            if (subscriber == null) {
                subscriber = repository.create(new Subscriber(phone));
                output = "Thanks for contacting TWBC! Text 'subscribe' if you would to receive updates via text message.";
            } else {
                output = processMessage(body, subscriber);
            }
        } catch (Exception e) {
            output = "Something went wrong. Try again.";
        }

        TwiMLResponse twiMLResponse = new TwiMLResponse();
        try {
            twiMLResponse = toTwiMLResponse(output);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        response.setContentType("text/xml");
        response.getWriter().write(twiMLResponse.toXML());
    }

    private String processMessage(String message, Subscriber subscriber) {
        String output = "Sorry, we don't recognize that command. Available commands are: 'subscribe' or 'unsubscribe'.";

        if (message.startsWith(TwilioServlet.SUBSCRIBE_COMMAND) || message.startsWith(TwilioServlet.UNSUBSCRIBE_COMMAND)) {
            subscriber.setSubscribed(message.startsWith(TwilioServlet.SUBSCRIBE_COMMAND));
            repository.update(subscriber);
            if (!subscriber.isSubscribed())
                output = "You have unsubscribed from notifications. Test 'subscribe' to start receiving updates again";
            else
                output = "You are now subscribed for updates.";
        }
        return output;
    }

    private TwiMLResponse toTwiMLResponse(String messageText) throws TwiMLException {
        Message message = new Message(messageText);
        TwiMLResponse response = new TwiMLResponse();
        response.append(message);
        return response;
    }
}
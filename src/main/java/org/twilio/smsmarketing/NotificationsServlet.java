package org.twilio.smsmarketing;

import org.twilio.smsmarketing.lib.RequestParametersValidator;
import org.twilio.smsmarketing.lib.Sender;
import org.twilio.smsmarketing.models.Subscriber;
import org.twilio.smsmarketing.repositories.SubscribersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotificationsServlet extends HttpServlet {

    private final SubscribersRepository repository;

    @SuppressWarnings("unused")
    public NotificationsServlet() {
        this(new SubscribersRepository());
    }

    public NotificationsServlet(org.twilio.smsmarketing.repositories.SubscribersRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/notifications.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String message = request.getParameter("message");
        String imageUri = request.getParameter("imageUrl");

        if (validateRequest(request)) {
            try {
                Sender sender = new Sender();
                Iterable<Subscriber> subscribers = repository.findAllSubscribed();
                for (Subscriber s : subscribers) {
                    sender.send(s.getPhoneNumber(), message, imageUri);
                }
            } catch (Exception e) {
                request.setAttribute("flash", "Something when wrong.");
            }

            request.setAttribute("flash", "Messages on their way!");
            request.getRequestDispatcher("/notifications.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/notifications.jsp").forward(request, response);
        }
    }

    private boolean validateRequest(HttpServletRequest request) {
        RequestParametersValidator validator = new RequestParametersValidator(request);

        return validator.validatePresence("message", "imageUrl");
    }
}
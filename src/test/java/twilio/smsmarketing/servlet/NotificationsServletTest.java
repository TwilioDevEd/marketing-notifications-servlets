package twilio.smsmarketing.servlet;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.twilio.smsmarketing.NotificationsServlet;
import org.twilio.smsmarketing.repositories.SubscribersRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class NotificationsServletTest extends BaseTwilioServletTest {

    @Mock HttpServletRequest request;

    @Mock HttpServletResponse response;

    @Mock private RequestDispatcher requestDispatcher;

    @Mock SubscribersRepository subscribersRepository;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMethod_RendersDefaultView() throws IOException, ServletException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        NotificationsServlet servlet = new NotificationsServlet(subscribersRepository);
        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/notifications.jsp");
    }

    @Test
    public void postMethodWithMissingMessage_RendersViewWithErrors() throws IOException, ServletException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getParameter("message")).thenReturn("");
        when(request.getParameter("imageUrl")).thenReturn("");

        NotificationsServlet servlet = new NotificationsServlet(subscribersRepository);
        servlet.doPost(request, response);

        verify(request).getRequestDispatcher("/notifications.jsp");
        verify(request).setAttribute("messageError", "Message can't be blank");
    }

    @Test
    public void postMethodWithMissingImageUrl_RendersViewWithErrors() throws IOException, ServletException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getParameter("message")).thenReturn("Blah");
        when(request.getParameter("imageUrl")).thenReturn("");

        NotificationsServlet servlet = new NotificationsServlet(subscribersRepository);
        servlet.doPost(request, response);

        verify(request).getRequestDispatcher("/notifications.jsp");
        verify(request).setAttribute("imageUrlError", "Image Url can't be blank");
    }
}
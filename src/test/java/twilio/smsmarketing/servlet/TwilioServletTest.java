package twilio.smsmarketing.servlet;

import junitparams.JUnitParamsRunner;
import org.hamcrest.CoreMatchers;
import org.jdom2.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.twilio.smsmarketing.TwilioServlet;
import org.twilio.smsmarketing.models.Subscriber;
import org.twilio.smsmarketing.repositories.SubscribersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class TwilioServletTest extends BaseTwilioServletTest {

    @Mock HttpServletRequest request;

    @Mock HttpServletResponse response;

    @Mock SubscribersRepository subscribersRepository;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void postMethod_RespondWithMessage() throws Exception {

        when(request.getParameter("From")).thenReturn("+1555555555");
        when(request.getParameter("Body")).thenReturn("");

        Subscriber bob = new Subscriber();
        bob.setId(1101);
        bob.setPhoneNumber("+1555555555");
        when(subscribersRepository.create(any(Subscriber.class))).thenReturn(bob);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(output);
        when(response.getWriter()).thenReturn(printWriter);

        TwilioServlet servlet = new TwilioServlet(subscribersRepository);
        servlet.doPost(request, response);

        printWriter.flush();
        String content = new String(output.toByteArray(), "UTF-8");

        Document document = getDocument(content);

        assertThatContentTypeIsXML(response);
        System.out.println(content);
        assertThat(getElement(document, "Message").getValue(), is(CoreMatchers.<String>notNullValue()));
    }

}
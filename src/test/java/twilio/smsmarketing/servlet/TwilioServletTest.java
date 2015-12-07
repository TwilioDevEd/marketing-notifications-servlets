package twilio.smsmarketing.servlet;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.CoreMatchers;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class TwilioServletTest extends BaseTwilioServletTest {

    @Mock HttpServletRequest request;

    @Mock HttpServletResponse response;

    ByteArrayOutputStream output;

    PrintWriter printWriter;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        output = new ByteArrayOutputStream();
        printWriter = new PrintWriter(output);
        when(response.getWriter()).thenReturn(printWriter);
    }
}
package org.twilio.smsmarketing.lib;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class Sender {

    private final TwilioRestClient client;

    public Sender() {
        client = new TwilioRestClient.Builder(Config.getAccountSid(), Config.getAuthToken()).build();
    }

    public Sender(TwilioRestClient client) {
        this.client = client;
    }

    public void send(String to, String message, String imageUri) {
        new MessageCreator(
                new PhoneNumber(to),
                new PhoneNumber(Config.getPhoneNumber()),
                message
        ).setMediaUrl(imageUri).create(client);
    }

}
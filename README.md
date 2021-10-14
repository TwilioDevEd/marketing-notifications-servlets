# SMS Notifications with Twilio and Java Servlets

[![Java Servlet CI](https://github.com/TwilioDevEd/marketing-notifications-servlets/actions/workflows/gradle.yml/badge.svg)](https://github.com/TwilioDevEd/marketing-notifications-servlets/actions/workflows/gradle.yml)

Use Twilio to create sms notifications to keep your subscribers in the loop.

[Read the full tutorial here](https://www.twilio.com/docs/tutorials/walkthrough/marketing-notifications/java/servlets)!

## Local Development

1. You will need to configure Twilio to send requests to your application when SMS are received.

   You will need to provision at least one Twilio number with sms capabilities so the application's users can make property reservations. You can buy a number [right here](https://www.twilio.com/user/account/phone-numbers/search). Once you have a number you need to configure it to work with your application. Open [the number management page](https://www.twilio.com/user/account/phone-numbers/incoming) and open a number's configuration by clicking on it.

   Remember the number you set on _SMS webhook_ must be the same one you configure on the `TwilioPhoneNumber` setting.

   ![Configure Voice](http://howtodocs.s3.amazonaws.com/twilio-number-config-all-med.gif)

   To start using `ngrok` on our project you'll have to execute the following line in the _command prompt_

    ```
    ngrok http 8080 -host-header="localhost:8080"
    ```

   Keep in mind that our endpoint is

    ```
    http://<your-ngrok-subdomain>.ngrok.io/message
    ```

1. Clone this repository and `cd` into it.
    ```
    git clone git@github.com:TwilioDevEd/marketing-notifications-servlets.git
    ```

1. Edit the sample configuration file `.environment` to match your database and Twilio's configuration.

    ```
  export TWILIO_ACCOUNT_SID=your_account_sid
  export TWILIO_AUTH_TOKEN=your_account_token
  export TWILIO_PHONE_NUMBER=your_twilio_number
    ```

   Once you have edited the `.environment` file, if you are using a UNIX operating system,
   just use the `source` command to load the variables into your environment.

   ```bash
   $ source .environment
   ```

   _If you are using a different operating system, make sure that all the
   variables from the `.environment` file are loaded into your environment._

1. Run the application.

   ```bash
   $ ./gradew jettyRun
   ```

1. Check it out at [http://localhost:8080](http://localhost:8080)

That's it!

To let our Twilio Phone number use the callback endpoint we exposed, our development server will need to be publicly accessible. [We recommend using ngrok to solve this problem](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html).

## Run the tests

```bash
./gradlew test
```

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.

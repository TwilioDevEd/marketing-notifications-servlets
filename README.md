# SMS Notifications with Twilio and Java Servlets

[![Java Servlet CI](https://github.com/TwilioDevEd/marketing-notifications-servlets/actions/workflows/gradle.yml/badge.svg)](https://github.com/TwilioDevEd/marketing-notifications-servlets/actions/workflows/gradle.yml)

Use Twilio to create sms notifications to keep your subscribers in the loop.

[Read the full tutorial here](https://www.twilio.com/docs/tutorials/walkthrough/marketing-notifications/java/servlets)!

## Set up

### Requirements

- [Java Development Kit](https://adoptopenjdk.net/) version 11 or later.
- [ngrok](https://ngrok.com)
- [PostgreSQL](https://www.postgresql.org/)
- A Twilio account - [sign up](https://www.twilio.com/try-twilio)

### Twilio Account Settings

This application should give you a ready-made starting point for writing your
own appointment reminder application. Before we begin, we need to collect
all the config values we need to run the application:

| Config Value | Description                                                                                                                                                  |
| :---------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Account Sid  | Your primary Twilio account identifier - find this [in the Console](https://www.twilio.com/console).                                                         |
| Auth Token   | Used to authenticate - [just like the above, you'll find this here](https://www.twilio.com/console).                                                         |
| Phone number | A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://www.twilio.com/console/phone-numbers/incoming) |

### Local development

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

1. Set your environment variables

    ```bash
    cp .env.example .env
    ```
   See [Twilio Account Settings](#twilio-account-settings) to locate the necessary environment variables.

1. Run the application.

   ```bash
   ./gradlew jettyRun
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

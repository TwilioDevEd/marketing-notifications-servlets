<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SMS Marketing Notifications</title>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw=="
          crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha256-k2/8zcNbxVIh5mnQ52A0r3a6jAgMGxFJFE2707UxGCk= sha512-ZV9KawG2Legkwp3nAlxLIVFudTauWuBpC10uEafMHYL0Sarrz5A7G79kXh5+5+woxQ5HM559XX2UZjMJ36Wplg=="
          crossorigin="anonymous">

    <link href="css/site.css" rel="stylesheet">
</head>
<body>

<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <a class="navbar-brand" href="#">Sms Notification</a>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-lg-8">
            <h1>SMS Notifications</h1>

            <p>Use this form to send MMS notifications to any subscribers.</p>

            <%
                String flash = (String) request.getAttribute("flash");
                if (flash != null && flash.length() > 0) {
            %>
            <div class="alert alert-info">
                ${flash}
            </div>
            <%
                }
            %>

            <form action="/notifications" method="post">
                <div class="form-group">
                    <label for="message">Enter message: </label>
                    <input type="text" class="form-control" id="message" name="message" placeholder="New things are happening!">
                    <span class="text-danger">${messageError}</span>
                </div>
                <div class="form-group">
                    <label for="imageUrl">Image URL: </label>
                    <input type="text" class="form-control" id="imageUrl" name="imageUrl" placeholder="http://fake.twilio.com/some_image.png">
                    <span class="text-danger">${imageUrlError}</span>
                </div>

                <button type="submit" class="btn btn-primary">Send Message</button>
            </form>
        </div>
    </div><!--/row-->

    <footer>
        <hr/>
        Made with <i class="fa fa-heart"></i> by your pals
        <a href="http://www.twilio.com">@twilio</a>
    </footer>
</div><!-- /.container -->

</body>
</html>
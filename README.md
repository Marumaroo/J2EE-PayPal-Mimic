# J2EE-PayPal-Mimic

A PayPal system mimic for a Web Applications and Services Assignment.

Within the webapps project you can find three web applications and services:

- main project - payment service
- thriftTimestamp - Thrift server for timestamping
- rest - RESTful API for currency conversion

The web application is designed to be deployed on a payara server and deploying the main project will also start the Thrift server and REST API.

## REST API

The API is accessible via the following format /conversion/{base-currency}/{target-currency}/{amount}

this will return a JSON containing the exchange rate and the given amount converted to the target currency.

The available currencies are hard-coded per assignment request and are GBP, EUR, and USD.

## Thrift Server

The thrift server code was auto-generated using apache thrift compiler. The file TimestampHandler.java contains the code for handling timestamping request and the TimestampServer.java contains the code for handling multiple requests.

## Main web application

The PayPal mimic service is created using JSF pages backed by CDI beans and are styled with the MaterializeCSS framework, while not requested by the assignment specification it allowed for ease of use and visual appeal.

The web application employs DAO and DTO design patterns for interacting with the Java Derby database and for transporting data between the layers in the application.

## timestampClient

Used to test the timestamping Thrift server. Running Timestamp Client will print the returned value from the server.
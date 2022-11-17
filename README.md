## About
Welcome!  
The project you are looking at is a REST API designed for clinics made in microservices technology.  
This part of my application is responsible for:
- download and save in database currencies & polish tax rate using Scheduler,
- share mentioned above data with the main project.

##Microservices & Front End
In order to run my application properly please use these projects:
- Configuration server - https://github.com/AlbertHeesch/cloud,
- Discovery - https://github.com/AlbertHeesch/discovery,
- Gateway - https://github.com/AlbertHeesch/gateway,
- Main Back End - https://github.com/AlbertHeesch/DentClinicApp,
- Front End - https://github.com/AlbertHeesch/DentClinicAppView.

## Requirements:

Java 11

Gradle

MySQL

## How to run
Set up your MySQL database in `application.properties` file.

Build your gradle with `gradlew build` in terminal.

Run the projects in order:
- Configuration server,
- Discovery,
- Gateway,
- DentAppClinic & DentAppClinicRates,
- DentAppClinicView.

Enter the http://localhost:8085/home page in your browser.

## How to configure
My application is updating currencies once a day and tax rate once a month.  
To see how do they work it is necessary to set your API Layer key in `application.properties` file.
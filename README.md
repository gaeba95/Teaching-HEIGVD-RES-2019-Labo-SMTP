# TCP client application with SMTP protocol

Authors : Gaetan Bacso et Marion Dutu Launay

## Description

This repository contains a TCP client application in Java. It is designed to use the Socket API to communicate with an SMTP server. The code also includes a partial implementation of the SMTP protocol.

The client application automatically plays pranks on a list of victims. The user of the app has to define (by editing the 3 config files in the folder "configs") : 
- a list of the e-mails addresses of the victims
- how many groups of victims should be formed in a given campaign
- a list of e-mail messages to be sent

In every group of victim, there should be 1 sender and at least 2 recipients, for a minimum size of 3. When a prank is played on a group of victims, then one of the messages from the list is selected. The prank is sent to all group recipients, from the address of the group sender, so the recipient victims are lead to believe that the sender victim has sent them.


## Setting up a mock SMTP server with Docker

To simulate a mock SMTP server, we used MockMock server (available [here](https://github.com/tweakers/MockMock). This tool is a cross-platform SMTP server built on Java and it allows you to test if outgoing e-mails are sent (without actually sending them) and to see what they look like. It provides a simple web interface that displays the e-mails that you have sent and their exact content. This way, you can be sure that your outgoing e-mails will not reach customers or users by accident.
To set it up, you have to download the jar file from the link above and run it manually.

You can also use the docker version provided in the docker directory.

To use it you have to :

- Go to the docker/MockMock/ directory
- Run the build-image.sh file : `./build-images.sh`
- Run the run-containers.sh file : `./run-containers.sh`
- Execute this command to get the IP address of your container : `docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $(docker ps -q)`


## Configure tool and run prank campaign

First, you need to clone this repository to get the code files. Then, as mentioned in the description above, the user must edit the 3 config files in the configs directory to send pranks :
- **config.properties** contains the SMTP server address, the SMTP server port, the number of groups that must be formed with the victims addresses and finally the e-mail of the person that will be in copy of all the pranks.
- **messages.utf8** contains all the messages that will be sent to the victims. Every message must begin with the line : `Subject: <subject>` and must be separated with `==`.
- **victims.utf8** is the file that contains the e-mail addresses of the victims. The groups are automatically formed by the application, depending of the number given in the first config file mentioned above.

All the pranks are automatically sent when you run the application. You can see the result in the MockMock server interface webpage : just type `<ip of mockmock server>:8282` in your favourite browser, after having set up and run the MockMock server with its jar file.



To use our tools you have to edit this 3 files with the right configuration. After that you can start the jar file contained in the release directory.

To start the jar use this command : `java -jar MailRobot.jar --config <path to the config directory>`

The config directory must contains the 3 files listed above with the same name.



You can also use our docker version :

- First go to the docker/MailRobot directory
- Edit the 3 files in the configs directory with your configuration
- Run the build-image.sh file : `./build-images.sh`
- Run the run-containers.sh file : `./run-containers.sh`
- IMPORTANT : to run build-image.sh you have to install maven before

## Implementation

Here is a class diagram to show what the classes of our program look like, and their depedencies with each other :

![UML diagram](https://github.com/gaeba95/Teaching-HEIGVD-RES-2019-Labo-SMTP/blob/master/figures/UML.png)

If you wish to look deeper into the code, here is what you need to know. We can split the job of our Java classes in 3 different categories :
- **Getting configuration data** : ConfigurationManager
- **Implementing application-specific business logic** : Prank, Group, Message, Person, PrankGenerator
- **Implementing SMTP protocol and sending e-mail messages** : SmtpClient

The class MainRobot contains the main function that runs the program.

Here is an example of a the dialogue between the client and the mock SMTP server (on the left, the program executed on my IDE that shows what is sent to the server, and on the right the MockMock server running in a terminal, showing what is received by it).

![Communication](https://github.com/gaeba95/Teaching-HEIGVD-RES-2019-Labo-SMTP/blob/master/figures/Com.PNG)

To have more informations about the program classes see our documentation :

[Documentation]( https://gaeba95.github.io/Teaching-HEIGVD-RES-2019-Labo-SMTP/)


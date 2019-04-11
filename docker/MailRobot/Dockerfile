FROM java:8
MAINTAINER Gaetan Bacso <gaetan.bacso@heig-vd.ch>

#
# When we build the image, we copy the executable jar in the image file system. 
#
COPY SMTP-Client-0.0.1-SNAPSHOT.jar /opt/app/MailRobot.jar
COPY configs /opt/app/configs

#
# This is the command that is executed when the Docker container starts
#
CMD ["java", "-jar", "/opt/app/MailRobot.jar", "--config", "/opt/app/configs/"]

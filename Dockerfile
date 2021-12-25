FROM tomcat:9.0.55-jdk17-openjdk
EXPOSE 8080
COPY ROOT.war /usr/local/tomcat/webapps/

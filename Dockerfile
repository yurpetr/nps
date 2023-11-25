FROM tomcat:9-jdk11-corretto
EXPOSE 8080 8443 9092
COPY ROOT.war /usr/local/tomcat/webapps/
COPY conf/* /usr/local/tomcat/conf/

For BrowserSync run:

```
npm run build && npm run watch
```
For run project:

```
mvn clean install spring-boot:run
```
For build and run docker:

```
cp nps-{version}.war ROOT.war
docker build -t npsapp . && docker run -itd -v ~/nps/db:/tmp/db -p 8080:8080 -p 8443:8443 -p 9092:9092 --name npsapp npsapp
```
OR 

```
docker build -t npsapp . && docker run -itd -v ~/nps/db:/tmp/db -P --name npsapp npsapp

docker update --restart=always npsapp
docker update --restart unless-stopped npsapp
```
Copy cert files into docker container:

```
docker cp ca_bundle.crt npsapp:/usr/local/tomcat/conf/
docker cp certificate.crt npsapp:/usr/local/tomcat/conf/
docker cp private.key npsapp:/usr/local/tomcat/conf/
sudo docker cp private.key npsapp:/usr/local/tomcat/conf/
```

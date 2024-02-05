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
mvn package -Dmaven.test.skip
cp ../target/nps-{version}.war.original ./ROOT.war
tar -czh . | docker compose up -d
```

Copy cert files into docker container (optional):

```
docker cp ca_bundle.crt npsapp:/usr/local/tomcat/conf/
docker cp certificate.crt npsapp:/usr/local/tomcat/conf/
docker cp private.key npsapp:/usr/local/tomcat/conf/
sudo docker cp private.key npsapp:/usr/local/tomcat/conf/
```

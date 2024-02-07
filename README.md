
Place certificates in 'docker/conf/'.

Environment variables are located in 'docker/.env.example', rename it to '.env' and fill in own data.

'PORTAL_URL','REST_ID','BX_TOKEN' - from bitrix24 inbound webhook 'https://{PORTAL_URL}/rest/{REST_ID}/{BX_TOKEN}/'

In 'com.yurpetr.nps.service.SetupDataLoader' change admin password, encrypted with BCryptPasswordEncoder.
Test class 'com.yurpetr.nps.PasswordGenerator' do it for you

Preparing

```
npm install
```

For BrowserSync run:

```
npm run build && npm run watch
```

For run project:

```
mvn clean spring-boot:run

OR

call docker\env.bat && mvn clean spring-boot:run -Dspring-boot.run.profiles=windows
```

For build and run docker:

```
mvn clean verify   OR   mvn clean package -Prelease
cp ../target/nps-{version}.war.original ./ROOT.war
tar -czh . | docker build -t npsapp -
docker compose up -d
```

Copy cert files into docker container (optional):

```
docker cp ca_bundle.crt npsapp:/usr/local/tomcat/conf/
docker cp certificate.crt npsapp:/usr/local/tomcat/conf/
sudo docker cp private.key npsapp:/usr/local/tomcat/conf/
```

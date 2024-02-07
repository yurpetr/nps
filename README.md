
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
mvn clean install spring-boot:run
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
docker cp private.key npsapp:/usr/local/tomcat/conf/
sudo docker cp private.key npsapp:/usr/local/tomcat/conf/
```

[
Preparing

```
npm init -
npx tailwindcss init -p
mvn verify
npm --version
node --version
npm run build:postcss
sudo npm install -g gulp-cli
npm i -D tailwindcss@latest postcss@latest autoprefixer@latest postcss-cli
npm install --save-dev gulp gulp-watch browser-sync gulp-babel @babel/core @babel/preset-env gulp-terser gulp-uglifycss gulp-environments
npm i @fullhuman/postcss-purgecss
npm i postcss-purgecss
npm config set registry http://registry.npmjs.org/
npm config set strict-ssl=false
npm i -D @fullhuman/postcss-purgecss postcss
npm i -D cssnano
npm install gulp-cli
npm i -D tailwindcss

```
]: #

[comment]: Preparing
[comment]: 
[comment]: ```
[comment]: npm init -
[comment]: npx tailwindcss init -p
[comment]: mvn verify
[comment]: npm --version
[comment]: node --version
[comment]: npm run build:postcss
[comment]: sudo npm install -g gulp-cli
[comment]: npm i -D tailwindcss@latest postcss@latest autoprefixer@latest postcss-cli
[comment]: npm install --save-dev gulp gulp-watch browser-sync gulp-babel @babel/core @babel/preset-env gulp-terser gulp-uglifycss gulp-environments
[comment]: npm i @fullhuman/postcss-purgecss
[comment]: npm i postcss-purgecss
[comment]: npm config set registry http://registry.npmjs.org/
[comment]: npm config set strict-ssl=false
[comment]: npm i -D @fullhuman/postcss-purgecss postcss
[comment]: npm i -D cssnano
[comment]: npm install gulp-cli
[comment]: npm i -D tailwindcss
[comment]: 
[comment]: ```
[comment]: 
[comment]: 
[comment]: 


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

# Spring Boot DataStore Objectify GAE

#### Run on dev server

    $ mvn clean install && mvn appengine:run

Go to http://localhost:8080

#### Deploy

modify application name in src/main/webapp/WEB-INF/appengine-web.xml if needed

    $ mvn clean install && mvn appengine:deploy -Dappengine.appId=foodmenulist -Dappengine.version=1

Demo site is [here](http://foodmenulist.appspot.com).

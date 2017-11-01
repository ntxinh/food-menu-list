---
# STACK

## BACKEND
- Java 8
- Spring Boot 1.5.8
- Objectify 5x (ORM for Google Cloud Datastory)
- Lombok
- Google App Engine 1.9.58 (Deploy Standard)
- Maven 3.5.x

## FRONTEND
- Angular 4
- Angular-CLI 1.4.9
- NodeJS 8.8.x
- NPM 5.5.x
- Python 2.7 (Just for host on Google App Engine Standard)

---
# HOW TO RUN & DEPLOY

## BACKEND

### Run on dev server
    $ cd /food-menu-list/backend
    $ mvn clean install && mvn appengine:run

Go to http://localhost:8080

### Deploy

modify application name in src/main/webapp/WEB-INF/appengine-web.xml if needed

    $ cd /food-menu-list/backend
    $ mvn clean install && mvn appengine:deploy

Demo site is [here](http://api-dot-foodmenulist.appspot.com).

## FRONDEND

### Run locally:
    $ npm start

Go to http://localhost:4200

### Build
    $ ng build -op ../deploy/dist

### Deploy
    $ cd /food-menu-list/frontend/deploy
    $ gcloud app deploy

Demo site is [here](http://foodmenulist.appspot.com).
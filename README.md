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

### Run on GAE dev server
    $ cd /food-menu-list/backend
    $ mvn clean install && mvn appengine:run

Go to http://localhost:8080

### Deploy

modify application name in src/main/webapp/WEB-INF/appengine-web.xml if needed

    $ cd /food-menu-list/backend
    $ mvn clean install && mvn appengine:deploy

## FRONDEND

- User Portal:

### Run locally:
    $ npm start

Go to http://localhost:4200

### Build
    $ cd /food-menu-list/frontend/user
    $ npm run build

### Deploy
    $ cd /food-menu-list/frontend/deploy_user
    $ gcloud app deploy

- Admin Portal:

### Run locally:
    $ cd /food-menu-list/frontend/user
    $ npm start

Go to http://localhost:4200

### Build
    $ npm run build

### Deploy
    $ cd /food-menu-list/frontend/deploy_admin
    $ gcloud app deploy

*Note:* If you don't see the change, try refresh browser with `Shift + F5` or `Ctrl + F5` or `Ctrl + R`
---
# DEMO
- User Portal:    http://foodmenulist.appspot.com
- Admin Portal:   http://admin-dot-foodmenulist.appspot.com
- Service Portal: http://api-dot-foodmenulist.appspot.com

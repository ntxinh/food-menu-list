---
# STACK

## BACKEND
- Java 8
- Spring Boot 1.5.x
- Maven 3.5.x
- Lombok
- Google App Engine 1.9.5x (Deploy Standard)
- Google Cloud Storage
- Google Cloud Datastore
- Objectify 5.1.x (ORM)

## FRONTEND
- Angular 4
- Angular-CLI 1.4.x
- NodeJS 8.8.x
- NPM 5.5.x
- Python 2.7 (Just for host on Google App Engine Standard)

---
# HOW TO RUN & DEPLOY

## BACKEND

### Run on GAE dev server
```bash
$ cd /food-menu-list/backend
$ mvn clean install && mvn appengine:run
```

Go to http://localhost:8080

### Deploy

modify application name in src/main/webapp/WEB-INF/appengine-web.xml if needed
```bash
$ cd /food-menu-list/backend
$ mvn clean install && mvn appengine:deploy
```

## FRONDEND

### User Portal:

- Run locally:
```bash
$ npm start
```

Go to http://localhost:4200

- Build:
```bash
$ cd /food-menu-list/frontend/user
$ npm run build
```

- Deploy:
```bash
$ cd /food-menu-list/frontend/deploy_user
$ gcloud app deploy
```

### Admin Portal:

- Run locally:
```bash
$ cd /food-menu-list/frontend/admin
$ npm start
```

Go to http://localhost:4200

- Build:

```bash
$ npm run build
```

- Deploy:
```bash
$ cd /food-menu-list/frontend/deploy_admin
$ gcloud app deploy
```

- Note: If you don't see the change, try refresh browser with `Shift + F5` or `Ctrl + F5` or `Ctrl + R`

---
# DEMO
- User Portal:    http://foodmenulist.appspot.com
- Admin Portal:   http://admin-dot-foodmenulist.appspot.com
- Service Portal: http://api-dot-foodmenulist.appspot.com

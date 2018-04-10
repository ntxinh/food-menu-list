# SCREENSHOTS

![Overview](https://raw.githubusercontent.com/nguyentrucxinh/food-menu-list/develop/screenshots/overview.gif)

# STACK

## BACKEND
- Java 8
- Spring Boot 1.5.x
- Maven 3.5.x
- Lombok
- Google App Engine 1.9.5x (Deploy Standard, Maximum 15 services [Pricing](https://cloud.google.com/appengine/pricing) [Quota](https://cloud.google.com/appengine/quotas)) 
- Google Cloud Storage (5 GB-months [Pricing](https://cloud.google.com/storage/pricing) [Quota](https://cloud.google.com/storage/quotas))
- Google Cloud Datastore (1 GB storage per day [Pricing & Quota](https://cloud.google.com/datastore/pricing))
- App Engine Application Logs (100 megabytes, 5 gigabytes Log data kept for a maximum of 7 days.)
- App Engine Mail API (10 calls, 10 messages, ...)
- Objectify 5.1.x (ORM)
- JWT 0.7.x
- Freemarker

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
$ npm run dev
or
$ npm run prod
```

Go to http://localhost:4200

- Build:
```bash
$ cd /food-menu-list/frontend/user
$ npm run build-dev
or
$ npm run build-prod
```

- Deploy:
```bash
$ cd /food-menu-list/frontend/user_deploy
$ gcloud app deploy
```

### Admin Portal:

- Run locally:
```bash
$ cd /food-menu-list/frontend/admin
$ npm run dev
or
$ npm run prod
```

Go to http://localhost:4200

- Build:

```bash
$ npm run build-dev
or
$ npm run build-prod
```

- Deploy:
```bash
$ cd /food-menu-list/frontend/admin_deploy
$ gcloud app deploy
```

- Note: If you don't see the change, try refresh browser with `Shift + F5` or `Ctrl + F5` or `Ctrl + R`

---
# APIs
- Helloworld 
   + /

- Home 
   + /user/hello 
   + /admin/hello

- Card (CRUD)
   + /api/user/cards

- Contact (CRUD)
   + /api/admin/contacts

- Task (CRUD)
   + /api/admin/tasks

- User (CRUD)
   + /api/admin/users

- Item (CRUD)
   + /api/user/items 
   + /api/admin/items
   + /api/admin/items/upload/{id}
   + /api/user/items/upload/{id}
```bash
curl -X POST \
  https://api-dot-foodmenulist.appspot.com/api/user/items/upload/0 \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'file=@/home/xinhnguyen/Downloads/YOUR_PHOTO.jpg' \
  -F 'name=Name 1' \
  -F 'description=Description 1'
```

- Auth
  + /sign-up 
```bash
curl -X POST \
  https://api-dot-foodmenulist.appspot.com/users/sign-up \
  -H 'content-type: application/json' \
  -d '{
    "username": "user",
    "password": "password"
}'
```

 + /sign-up-admin
```bash
curl -X POST \
  https://api-dot-foodmenulist.appspot.com/users/sign-up-admin \
  -H 'content-type: application/json' \
  -d '{
    "username": "admin",
    "password": "password"
}'
```

  + /confirm-mail-sign-up
```bash
curl -X GET \
  'https://api-dot-foodmenulist.appspot.com/users/confirm-mail-sign-up?token=YOUR_TOKEN'
```

  + /login
```bash
curl -X POST \
  http://api-dot-foodmenulist.appspot.com/login \
  -H 'content-type: application/json' \
  -d '{
    "username": "admin",
    "password": "password"
}'
```

---
# DEMO
- User Portal:    http://foodmenulist.appspot.com
- Admin Portal:   http://admin-dot-foodmenulist.appspot.com
- Service Portal: http://api-dot-foodmenulist.appspot.com

# ISSUES
- https://github.com/angular/angular-cli/issues/7113
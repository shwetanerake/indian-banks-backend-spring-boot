# indian-banks-backend-spring-boot

A example Java app using *Spring Boot*, *Restful API with Spring MVC*, *Hibernate* and *Postgresql*. Along with REST API's the app demonstrates pagination in spring boot 
with offset and limit, also search can be performed on all columns.   

The app is currently deployed on Heroku https://indian-banks-spring-boot-demo.herokuapp.com/. The app makes use of the data available in this [repository](https://github.com/snarayanank2/indian_banks)
which is hosted on https://www.clever-cloud.com/. 

## Technology Stack

|Technology                |Description                  |
|--------------------------|-----------------------------|
|Core Framework            |Spring Boot                  |
|Persistent Layer Framework|Spring Data JPA              |
|Database                  |PostgreSQL                   |
|Server - Backend          |Java 8                       |
|Libraries                 |Lombok                       | 
|External Tools & Services |Advanced REST client         |   
|Others                    |Git, Maven, Spring Tool Suite|

## Reference

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)
- [Deploying Spring Boot Applications to Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)

## API Request and Sample Response

1. Fetch details by IFSC

![Fetch by IFSC](https://github.com/shwetanerake/indian-banks-backend-spring-boot/blob/main/screenshots/fetchbyifsc.png)

2. Fetch details by City, **ordered by IFSC code** (ascending order) with limit and offset. 

![Fetch by IFSC](https://github.com/shwetanerake/indian-banks-backend-spring-boot/blob/main/screenshots/fetchbycity.png)

3. Search API to return possible matches across all columns and all rows, **ordered by IFSC code** (ascending order) with limit and offset.

URL: https://shielded-coast-37134.herokuapp.com/api/branches/autocomplete?city-name=MUMBAI&limit=1&offset=1&q=LALBAUG \
where, 
* q is the string to be searched
* city-name is the name of the city (optional)
* offset and limit are used for pagination (optional)

![Search API](https://github.com/shwetanerake/indian-banks-backend-spring-boot/blob/main/screenshots/search.png)

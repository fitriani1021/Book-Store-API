# Book-Store-API
an API for bookstore warehouse

to test the API, first you need to set the following required environtment variables: <p>
1. API_PORT : contains the local port to run the API <p>
2. DB_HOST : contains db host for the JPA entities <p>
3. DB_PORT : contains database port <p>
4. DB_NAME : contains database name <p>
5. DB_USERNAME : contains database username <p>
6. DB_PASS : contains database password <p>

then, you can test the API via postman or other API platforms. <p><p>

ERD of bookstore API :<p>
![image](https://user-images.githubusercontent.com/22906613/208131783-cd8eef4e-fff1-488e-99fb-d0f9bc02817d.png) <p><p>
This API can do these methods: <p>
./genres Mapping can do: <p>
1. GET all genres<p>
2. GET /{id} genre by id<p>
3. GET /name={name} genre by name<p>
4. POST create genre<p>
5. DELETE genre<p>
<p>
./books Mapping can do: <p>
1. GET all books <p>
2. GET /{id} books by id <p>
3. GET /title={title} books by name <p>
4. POST create book <p>
5. PATCH update stock and price by price id<p>
6. DELETE book by id <p>

# Crypto-App-Backend

Setting up Database:
-Install Docker Desktop for the local Database.<br>
-Create a new Directory and paste this code into a new text file that ends with .yaml <br>
Code:<br>
version: '3'

services: <br>
  database: <br>
    image: mysql <br>
    container_name: database <br>
    environment: <br>
      MYSQL_ROOT_PASSWORD: password <br>
      MYSQL_DATABASE: crypto_notification <br>
      MYSQL_USER: user <br>
      MYSQL_PASSWORD: password <br>
    ports: <br>
      - "3305:3306" <br>
<br>


-Open up cmd and go to the file directory your yaml file is placed in. <br>
-Now type "docker-compose up" and the database is installing.


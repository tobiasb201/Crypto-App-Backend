# Crypto-App-Backend

Setting up Database:
-Install Docker Desktop for the local Database.<br>
-Create a new Directory and paste this code into a new text file that ends with .yaml
Code:
version: '3'

services:
  database:
    image: mysql
    container_name: database
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: crypto_notification
      MYSQL_USER: user
      MYSQL_PASSWORD: password
    ports:
      - "3305:3306"

-Open up cmd and go to the file directory your yaml file is placed in.
-Now type "docker-compose up" and the database is installing.


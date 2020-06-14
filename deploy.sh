mvn clean install && mvn clean install spring-boot:repackage -pl Bootstrap
heroku deploy:jar Bootstrap/target/Bootstrap-1.0.jar --app moscowmetrobot

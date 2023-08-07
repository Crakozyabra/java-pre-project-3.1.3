echo 'Stop containers:'
docker ps
docker stop mysql-db spring-boot-container
echo 'Remove containers:'
docker ps -a
docker rm mysql-db spring-boot-container
echo 'Remove images:'
docker images
docker rmi mysql:latest spring-boot-image
echo 'Repack .jar spring boot application:'
mvn clean package -DskipTests
echo 'Compose up:'
docker compose up
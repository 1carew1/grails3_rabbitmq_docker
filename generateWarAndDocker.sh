grails package
docker build -f Dockerfile -t 1carew1/grails3_rabbitmq_docker .
docker run  -t -p 8080:8080 1carew1/grails3_rabbitmq_docker

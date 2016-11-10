# docker-note
docker notes and some useful console command

#This will remove the exited containers.
docker rm -v $(docker ps -a -q -f status=exited) 

#Remove unwanted ‘dangling’ images.
docker rmi $(docker images -f "dangling=true" -q)

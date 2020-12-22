Delete all images:
  docker rmi $(docker images -a -q)
Delete all containers:
  docker rm -f $(docker ps -a -q)

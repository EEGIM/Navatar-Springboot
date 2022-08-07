sudo docker ps -a -q --filter "name=navataraws" | grep -q . && docker stop navataraws && docker rm navataraws | true

sudo docker rmi navatarser/navataraws:1.0

sudo docker pull navatarser/navataraws:1.0

docker run -d -p 8080:8080 --name navataraws navatarser/navataraws:1.0

docker rmi -f $(docker images -f "dangling=true" -q) || true
build:
	docker compose -f DockerCompose.yml up --build -d --remove-orphans

up:
	docker compose -f DockerCompose.yml up -d

down:
	docker compose -f DockerCompose.yml down

show_logs:
	docker compose -f DockerCompose.yml logs

app-console:
	docker logs --follow iEstator

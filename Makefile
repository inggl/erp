.DEFAULT_GOAL := help

# Help
help:   ## Show this help.
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/##//'

# Docker Compose
docker-compose-up:  ## Build and run all services.
	docker-compose up --build -d
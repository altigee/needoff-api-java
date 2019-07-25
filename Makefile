.PHONY: build-docker build-run-docker run-docker

build-app:
	gradle build

build-docker-image:
	$(eval VERSION=$(shell sh -c "./gradlew properties -q | grep "version:" | sed 's/version: //g'"))
	$(eval TAG=needoff-api:$(VERSION))
	$(eval FILE := needoff-$(VERSION).jar)
	docker build -t $(TAG) --build-arg APPLICATION_FILENAME=$(FILE) .
	docker tag $(TAG) needoff-api:latest

build-run-docker: build-docker-image
	docker run -p 8080:8080 --name=needoff-api-latest $(TAG)

run-full:
	docker-compose up -d

stop-full:
	docker-compose down

build-run-full: build-docker-image run-full

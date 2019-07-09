.PHONY: build-docker build-run-docker run-docker


build-docker:
	$(eval VERSION=$(shell sh -c "gradle properties -q | grep "version:" | sed 's/version: //g'"))
	$(eval TAG=needoff-api:$(VERSION))
	$(eval FILE := needoff-$(VERSION).jar)
	gradle build
	docker build -t $(TAG) --build-arg APPLICATION_FILENAME=$(FILE) .
	docker tag $(TAG) needoff-api:latest

build-run-docker: build-docker
	docker run -p 8080:8080 --name=needoff-api-latest $(TAG)

run-docker:
	$(eval TAG=needoff-api-java:$(shell sh -c "gradle properties -q | grep "version:" | sed 's/version: //g'"))
	docker run -p 8080:8080 --name=needoff-api $(TAG)

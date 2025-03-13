.PHONY: build-ShortUrl

build-ShortUrl:
	mvn clean package
	if not exist $(ARTIFACTS_DIR) mkdir $(ARTIFACTS_DIR)
	copy target\urlShortener-1.0.jar $(ARTIFACTS_DIR)\
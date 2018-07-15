DOCROOT=resources/public/
SOURCES=server/

all: $(foreach f,$(wildcard $(SOURCES)*.clj),$(DOCROOT)$(notdir $(f:.clj=.php)))

$(DOCROOT)%.php: $(SOURCES)%.clj
	./frock.php $< > $@

.PHONY: clean watch serve

watch:
	while [ 1 ]; do $(MAKE) -q || $(MAKE); sleep 1; done

serve:
	php -S 127.0.0.1:8000 -t $(DOCROOT)

clean:
	rm -f $(DOCROOT)*.php

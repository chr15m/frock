SOURCES_MAL = readline.php types.php reader.php printer.php interop.php env.php core.php stepA_mal.php
SOURCES_PHP = $(foreach f,$(SOURCES_MAL),mal/php/$(f))
SOURCES = alias-hacks.mal frock.mal
DELIMITER="FROCKSCRIPTDELIMITER"

frock.php: build/mal.php src/*
	echo "#!/usr/bin/env php" > $@
	grep -B 10000 "// run mal file" $< | sed '$$d' >> $@
	echo "\$$script = <<<$(DELIMITER)" >> $@
	echo "(do" >> $@;
	cat src/* >> $@
	echo ")" >> $@
	echo "$(DELIMITER);" >> $@
	echo 'rep($$script);' >> $@
	echo "?>" >> $@
	chmod +x $@

build/mal.php: $(SOURCES_PHP) build
	cat $(SOURCES_PHP) | grep -v "^require_once" > $@

$(SOURCES_PHP):
	git clone https://github.com/chr15m/mal.git

build:
	mkdir -p build

test:
	./mal/runtest.py tests/test-frock.clj ./mal/php/mal

.PHONY: clean watch

watch:
	while [ 1 ]; do make -q || make; sleep 1; done

clean:
	rm -rf build/ frock.php


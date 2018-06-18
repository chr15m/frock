SOURCES_MAL = readline.php types.php reader.php printer.php interop.php env.php core.php stepA_mal.php
SOURCES_PHP = $(foreach f,$(SOURCES_MAL),mal/php/$(f))
SOURCES = alias-hacks.mal frock.mal

frock.php: build/mal.php
	cat src/* > build/bootstrap.mal
	FROCKBOOTSTRAP=$< php $< build/bootstrap.mal > build/bootstrap.php
	echo "#!/usr/bin/env php" > $@
	FROCKBOOTSTRAP=build/bootstrap.php php build/bootstrap.php src/frock.mal >> $@
	chmod +x $@

build/mal.php: $(SOURCES_PHP) build
	cat $(SOURCES_PHP) | grep -v "^require_once" > $@

$(SOURCES_PHP):
	git clone https://github.com/kanaka/mal.git

build:
	mkdir -p build

.PHONY: clean

clean:
	rm -rf build/ frock.php


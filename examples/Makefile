SOURCES=$(wildcard *.clj)
OUTPUTS=$(foreach f,$(SOURCES),$(f:.clj=.php))

all: $(OUTPUTS)

watch:
	while [ 1 ]; do make -q || make; sleep 1; done

%.php: %.clj
	../frock.php $< > $@

.PHONY: clean

clean:
	rm -rf *.php

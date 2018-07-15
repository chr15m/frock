# Frock tutorial

Let's build an example Frock back-end for a Clojurescript single-page web application that anybody can deploy by simply copying the files up to a PHP enabled server.

Examples of things we can enable with a minimal amount of server side code:

 * Proxying requests to APIs that would otherwise be defeated by CORs.
 * Persisting a small amount of data to text files or sqlite.
 * Authenticating access to some data.

You'll need to have the `php` command line binary installed in your path (this is the default on OSX).

All of the files from this tutorial are available in the folder where you're reading this.

### Set up the dev server

Start by creating a basic front-end Figwheel project:

	lein new figwheel frock-test
	cd frock-test

Note: *if you have some other prefered front-end dev lein template feel free to use that*.

Run the figwheel server in a separate terminal window:

	lein figwheel

Let's also run the PHP dev server so that we can run the resulting PHP scripts.

Do this in another terminal window:

	cd resources/public
	php -S 127.0.0.1:8000

Now open a browser tab and visit http://127.0.0.1:8000/. You should see the **Figwheel template** default output.

Note: *since we'll be using our PHP server on port `8000` you might want to change the figwheel `:open-urls` setting to point at port `8000` instead of `3449`*.

Great, we now have the development servers running.

Ctrl-C the PHP server for now as we'll run it as part of the build-on-reload process in the next step.

### Get the Frock transpiler

We want to transpile our frock code to PHP, and we want this to happen automatically whenever a frock server file is changed.

Fetch the frock transpiler so we can turn our Clojure-flavoured PHP into PHP:

	curl https://chr15m.github.io/frock/frock.php > frock.php
	chmod 700 frock.php

Create a new folder for our server script(s) to go into:

	mkdir server

Test that frock is working by creating a simple "hello world" script for transpiling:

	echo '(println "Hello world!")' > server/hello-world.clj
	./frock.php server/hello-world.clj > resources/public/hello-world.php

Now if you visit http://127.0.0.1:8000/hello-world.php in your browser you should see the text "Hello world!".

### Set up the Frock auto-build

We want to set things up so that any Frock files in `server` get re-compiled whenever they are changed. Let's use a Makefile for this task.

Create a `Makefile` at the top level of your project with the following content. You can also download a copy from [GitHub](./Makefile).

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

Now we can run this Makefile to watch, compile & serve the generated PHP files for us in a separate terminal window as follows:

	make -j2 watch serve

The `-j2` flag tells `make` to launch two threads, one for the `watch` rule and one for the `serve` rule.

If you change the text in `server/hello-world.clj` and refresh http://127.0.0.1:8000/hello-world.php after one second you should see the change in the browser.

You should now have a terminal window running the figwheel server and a separate terminal window running the Frock build & PHP server.

Note: *You could add a `lein` alias and use [https://github.com/hyPiRion/lein-shell](lein-shell) to run the `make -j2 watch serve` command if you don't want to remember the full command; or create a shell script*.

### Write our first Frock back-end

Now that we have our dev system up and running let's build some example back-end code.

We're going to build a trivial piece of server-side code to proxy requests which would otherwise be blocked by CORS.

First let's test out a normal request from our frontend to verify that it is indeed blocked by CORS.

Open the file `src/frock_test/core.cljs` and add the following to the end of it:

	; display some text in the page
	(defn set-content [content]
	  (aset (js/document.getElementById "app") "innerHTML" (str content)))
	
	; Make an HTTP request
	(-> 
	    (js/fetch "https://duckduckgo.com/")
	    (.then #(.then (.text %) set-content))
	    (.catch #(js/alert (str %))))

You should see the following alert when you save the file as the outgoing request is blocked by the browser:

> TypeError: NetworkError when attempting to fetch resource

Let's write a tiny proxy to fetch this content for us on the server side. Create a file `server/duck-proxy.clj` with the following text:

	(print (php/file_get_contents "https://duckduckgo.com/"))

When you save this file the PHP file should get built into `resources/public/duck-proxy.php` automatically.

Now in our front-end script change the URL which is being fetched from `"https://duckduckgo.com/"` to `"duck-proxy.php"` and hit save.

Voila! You should see the basic HTML form elements from the Duck Duck Go homepage rendered inside the page without any styling. This means our script has worked - it has made a request to `duck-proxy.php` which has fetched and returned the page contents to our front-end.

### A second back-end script

For your second back-end script write a small server which takes input from the `php/_POST` variable and appends it to a file.

Hints:

 * `(get php/_POST "myvar")` will get the contents of a passed parameter called `myvar`.
 * `(php/file_put_contents filename content php/FILE_APPEND)` will append content to a file named `filename`.

### Using in your own projects

To use this in your own projects just make sure the PHP files which are built into `resources/public` are included with your distributed package during your final build step.

Have fun!

### Get help

Shoot [me an email](mailto:chris@mccormickit.com) if you use Frock or if you want some help using it.

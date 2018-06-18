Frock is Clojure-flavoured PHP.

```
$ curl https://chr15m.github.io/frock/frock.php > frock.php
$ chmod 700 frock.php
$ echo '(print "hello world!")' > hello.mal
$ ./frock.php hello.mal > hello.php
$ php hello.php
hello world!
```

Then do `php -S 0.0.0.0:8000` to view your script at http://localhost:8000/hello.php

In case it's not obvious, this is a hack and probably shouldn't be used in production. PRs most welcome.


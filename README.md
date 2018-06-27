Frock is Clojure-flavoured PHP.

```clojure
(print (php/json_encode {:hello "world"))
```

### Get

```sh
curl https://chr15m.github.io/frock/frock.php > frock.php
chmod 700 frock.php
```

### Use

```sh
$ echo '(print "hello world!")' > hello.clj
$ ./frock.php hello.clj > hello.php
$ php hello.php
hello world!
```

Then `php -S 0.0.0.0:8000` & browse to <http://localhost:8000/hello.php>.

### Examples

See the [examples](./examples) folder for example code using this.

### About

 * In case it's not obvious, this is a hack and probably shouldn't be used in production.
 * This is only superficially Clojure. Most things won't work.
 * PRs most welcome.

Most of the code here comes from the [Make-a-LISP project](https://github.com/kanaka/mal). The project and the PHP port were both built by [Kanaka](https://github.com/kanaka/).

The only original thing this repository brings is a system for building mal-php code into a distributable artifact.


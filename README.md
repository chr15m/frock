Frock is Clojure-flavoured PHP.

<p align="center">
    <img src="https://cdn.rawgit.com/chr15m/frock/master/screencast.svg"/>
</p>

> "If you want to produce free-as-in-whatever code that runs on virtually every server in the world with zero friction or configuration hassles, PHP is damn near your only option. If that doesn't scare you, then check your pulse, because you might be dead." [Jeff Atwood, 2012](https://blog.codinghorror.com/the-php-singularity/)

### Get

```sh
curl https://chr15m.github.io/frock/frock.php > frock.php
chmod 700 frock.php
```

### Use

```sh
$ echo '(println "hello world!")' > hello.clj
$ ./frock.php hello.clj > hello.php
$ php hello.php
hello world!
```

Then `php -S 0.0.0.0:8000` & browse to <http://localhost:8000/hello.php>.

### Examples

See the [examples folder](./examples).

### Security

Please see [this note about security](https://github.com/chr15m/frock/issues/4).

### About

 * In case it's not obvious, this is a hack and probably shouldn't be used in production.
 * This is only superficially Clojure. Most things won't work.
 * PRs most welcome.

Most of the code here comes from the [Make-a-LISP project](https://github.com/kanaka/mal). The project and the PHP port were both built by [Kanaka](https://github.com/kanaka/).

The only original thing this repository brings is a system for building mal-php code into a distributable artifact.

### Why?

<https://chr15m.github.io/frock-clojure-flavoured-php.html>

Frock might be interesting to you if:

 * You want to build a single artifact that almost anybody can upload to commodity hosting.
 * You need some minimal server side code to support your front-end web application.
 * You find PHP's syntax onerous but enjoy wrangling LISP braces.

### Caveats & limitations

Too many to list here. :joy:


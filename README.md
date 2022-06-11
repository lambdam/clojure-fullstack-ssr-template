# Template for Clojure fullstack web project with server side rendering

One noticeable point is that shadow-cljs is used as a deps.edn library rather than a
standalone Node.js program.

## Dependencies

- Java (>= 11)
- Node.js and Yarn (for front dependencies only)
- Clojure CLI

## Installation

`make install-front-dependencies`

## Emacs usage

Open a Clojure file.

Then run `cider-jack-in-clj&cljs`.

In the clj REPL buffer, run `(start)`. This will start the whole system with [Clip](https://github.com/juxt/clip). You can also `(stop)` the system from the `user` namespace.

At any time you can run `cider-ns-refresh`. This will:

1. Stop the whole system.
2. Refresh the namespaces.
3. Start again the system.

This behaviour is built with Emacs hooks defined in the [.dir-locals.el](./.dir-locals.el) file.

## VSCode usage

To be done...

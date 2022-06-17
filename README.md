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

## VSCode usage with Calva

Open the project root folder.

In a terminal (integrated or external) start the project REPL:

```sh
clojure -Sdeps '{:deps {nrepl/nrepl {:mvn/version,"0.9.0"},cider/cider-nrepl {:mvn/version,"0.28.3"}}}' -M:dev:cljs -m nrepl.cmdline --middleware "[cider.nrepl/cider-middleware,shadow.cljs.devtools.server.nrepl/middleware]"
```

Then run **Calva: Connect to a running REPL in your Project** (`ctrl+alt+c ctrl+alt+c`).

Select the project type **Connect to server + client started with clojure**.

The system will start.

Open the frontend at http://localhost:4567

You can refresh and reload the system by running the project custom command: `Calva: Run Custom REPL Command`
followed by `Refresh and restart Clojure project`.

### Joyride convenience included

If you have the [Joyride](https://github.com/BetterThanTomorrow/joyride) VS Code extension installed, you can benefit from the Joyride scripts and functions included in this project.

Run **Joyride: Run Workspace Script..** and select `start_project.cljs`, to start the project REPL.

To get a keyboard shortcut for refreshing namespaces embedded between a system stop and start, add this to your `keybindings.json`:

```json
  {
      "key": "cmd+ctrl+n",
      "command": "joyride.runCode",
      "args": "(helpers/refresh-and-restart)",
  },
```
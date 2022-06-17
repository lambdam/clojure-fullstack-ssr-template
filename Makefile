default:
	@echo "Choose a task"

install-front-dependencies:
	yarn install
	mkdir -p resources/public
	cp ./node_modules/bootstrap/dist/css/bootstrap.min.css resources/public
	cp ./node_modules/bootstrap/dist/css/bootstrap.min.css.map resources/public

launch-clojure-repl:
	clojure -Sdeps '{:deps {nrepl/nrepl {:mvn/version,"0.9.0"},cider/cider-nrepl {:mvn/version,"0.28.3"}}}' -M:dev:cljs -m nrepl.cmdline --middleware "[cider.nrepl/cider-middleware,shadow.cljs.devtools.server.nrepl/middleware]"
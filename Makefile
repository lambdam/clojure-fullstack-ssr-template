default:
	@echo "Choose a task"

install-front-dependencies:
	yarn install
	mkdir -p resources/public
	cp ./node_modules/bootstrap/dist/css/bootstrap.min.css resources/public
	cp ./node_modules/bootstrap/dist/css/bootstrap.min.css.map resources/public
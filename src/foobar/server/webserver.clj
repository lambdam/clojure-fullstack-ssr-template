(ns foobar.server.webserver
  (:require [foobar.server.routes :as r]
            [ring.adapter.jetty :as jetty]))

(defn start-webserver! [options]
  (println "Starting server with options:" (pr-str options))
  (-> (r/make-ring-reitit-router)
      (jetty/run-jetty options)))

(defn stop-webserver! [server]
  (.stop server))
(ns foobar.server.webserver
  (:require [foobar.server.routes :as r]
            [ring.adapter.jetty :as jetty]
            [clojure.spec.alpha :as s]))

(defn start-webserver! [options]
  (-> (r/make-ring-reitit-router)
      (jetty/run-jetty options)))

(defn stop-webserver! [server]
  (.stop server))

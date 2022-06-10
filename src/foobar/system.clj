(ns foobar.system
  (:require [foobar.server.webserver :as webserver]))

(defn get-system-config []
  {:components
   {::webserver {:start `(webserver/start-webserver! {:port 4567
                                                      :join? false})
                 :stop `webserver/stop-webserver!}}})

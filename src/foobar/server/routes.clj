(ns foobar.server.routes
  (:require [foobar.server.handlers.public :as public-h]
            [reitit.ring :as reitit-ring]
            [ring.middleware.defaults :as ring-defaults]))

;; To be able to recompile handlers on the fly on dev
;; h for handler
(defmacro h [handler-sym]
  (let [env (System/getenv "FOOBAR_ENV")]
    (if (contains? #{"prod" "local-prod" "testing"} env)
      handler-sym
      `(fn ~(-> handler-sym name symbol) [req#]
         (~handler-sym req#)))))

(defn make-routes [_config]
  ["" ;; {:middleware [[my-middleware config]]}
   ["/" {:name :home-page
         :get (h public-h/home-page)}]
   ["/app-1" {:name :webapp-page-1
              :get (h public-h/webapp-page-1)}]
   ["/app-2" {:name :webapp-page-2
              :get (h public-h/webapp-page-2)}]])

(defn make-ring-reitit-router []
  (-> (make-routes {})
      reitit-ring/router
      ;; https://github.com/metosin/reitit/blob/master/doc/ring/slash_handler.md
      (reitit-ring/ring-handler
        (reitit-ring/routes
          (reitit-ring/redirect-trailing-slash-handler {:method :strip})
          (reitit-ring/create-default-handler
            {:not-found (fn not-found [_req]
                          {:status  404
                           :headers {"Content-Type" "text/html"}
                           :body "<h1>404 not found</h1>"})})))
      (ring-defaults/wrap-defaults ring-defaults/site-defaults)))

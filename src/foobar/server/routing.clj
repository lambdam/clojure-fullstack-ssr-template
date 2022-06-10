(ns foobar.server.routing
  (:require [clojure.spec.alpha :as s]
            [reitit.core :as reitit]))

(s/def ::req map?)

(s/def ::name keyword?)
(s/def ::path string?)
(s/def ::path-params (s/map-of keyword? string?))
(s/def ::query-params (s/map-of string? string?))

;; ---

(s/fdef name->path
  :args (s/cat :m (s/keys :req [::req ::name]
                          :opt [::path-params ::query-params]))
  :ret string?)

(defn name->path [m]
  (-> (::req m)
      ::reitit/router
      (reitit/match-by-name (::name m) (::path-params m))
      (reitit/match->path (::query-params m))))

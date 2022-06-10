(ns foobar.server.lib.cljs-builds
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(def ^:const cljs-folder "public/cljs")

(defmacro inject-cljs-resource [resource-id]
  (let [env (System/getenv "FOOBAR_ENV")
        cljs-dir "public/cljs"
        asset-path "/cljs"]
    (if (contains? #{"prod" "local-prod" "testing"} env)
      (->> (str cljs-dir "/assets.edn")
           io/resource
           slurp
           edn/read-string
           (some (fn [entry]
                   (when (= (:name entry) resource-id)
                     entry)))
           :output-name
           (str asset-path "/"))
      (str asset-path "/" (name resource-id) ".js"))))

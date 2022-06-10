(ns user
  (:require [juxt.clip.repl :as clip-repl]
            [shadow.cljs.devtools.api :as shadow]
            [foobar.system :as sys]
            [clojure.tools.namespace.repl :as tools-repl]
            [shadow.cljs.devtools.server :as shadow-server]))

(set! *warn-on-reflection* true)

(tools-repl/set-refresh-dirs "env/dev" "src")

(clip-repl/set-init! sys/get-system-config)

(defn start []
  (clip-repl/start)
  (println "Clip DEV system started")
  nil)

(defn stop []
  (clip-repl/stop)
  (println "Clip DEV system stopped")
  nil)

(defn reset []
  (stop)
  (start))

(defn cljs-repl
  ([]
   (cljs-repl :app))
  ([build-id]
   (shadow-server/start!)
   (shadow/watch build-id)
   (shadow/nrepl-select build-id)))

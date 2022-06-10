(ns foobar.shared.util
  (:require [clojure.string :as str]))

(defn clean-whitespaces [s]
  (-> s
      str/trim
      (str/split #"\s+")
      (->> (str/join " "))))

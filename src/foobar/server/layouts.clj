(ns foobar.server.layouts
  (:require [uix.dom.alpha :as uixd]))

(defn public-layout [req body]
  (str
    "<!DOCTYPE html>\n"
    (uixd/render-to-static-markup
      [:html {:lang "en"}
       [:head
        [:meta {:charset "UTF-8"}]
        [:meta
         {:name "viewport",
          :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
        [:meta {:http-equiv "x-ua-compatible", :content "ie=edge"}]
        [:title "Foobar"]
        [:link {:type "text/css"
                :href "/bootstrap.min.css"
                :rel "stylesheet"}]]
       [:body
        [:main
         body]
        (when-let [deps (::extra-js-deps req)]
          (for [dep deps]
            [:script {:type "text/javascript" :src dep}]))]])))

(defn render-public-layout [req body]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (public-layout req body)})

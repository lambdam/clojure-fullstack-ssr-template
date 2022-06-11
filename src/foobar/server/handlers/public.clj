(ns foobar.server.handlers.public
  (:require [foobar.server.layouts :as layouts]
            [foobar.server.lib.cljs-builds :as cljs-builds]
            [foobar.server.routing :as r]
            [foobar.shared.views.page-1 :as sv-page-1]
            [foobar.shared.views.page-2 :as sv-page-2]))

(defn home-page [req]
  (layouts/render-public-layout
    req
    [:div.container.text-center.py-5
     [:h1.mb-4 "Home page"]
     [:p
      [:a {:href (r/name->path {::r/req req
                                ::r/name :webapp-page-1})}
       "Go to webapp page 1"]]
     [:p
      [:a {:href (r/name->path {::r/req req
                                ::r/name :webapp-page-2})}
       "Go to webapp page 2"]]]))

(defn webapp-page-1 [req]
  (layouts/render-public-layout
    (assoc req ::layouts/extra-js-deps [(cljs-builds/inject-cljs-resource :react-shared-deps)
                                        (cljs-builds/inject-cljs-resource :page-1)])
    [:div#react-root
     [sv-page-1/root {}]]))

(defn webapp-page-2 [req]
  (layouts/render-public-layout
    (assoc req ::layouts/extra-js-deps [(cljs-builds/inject-cljs-resource :react-shared-deps)
                                        (cljs-builds/inject-cljs-resource :page-2)])
    [:div#react-root
     [sv-page-2/root {}]]))

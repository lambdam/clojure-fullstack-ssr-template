(ns foobar.shared.views.page-2
  (:require [uix.core.alpha :as uix]))

(defn root [_props]
  (let [loading?* (uix/state true)]
    #?(:cljs
       (uix/with-effect []
         (js/setTimeout
           #(reset! loading?* false)
           1500)))
    [:div.container.text-center.text-danger.py-5
     (if @loading?*
       [:div.spinner-border {:style {:width "3rem"
                                     :height "3rem"}
                             :role "status"}
        [:span.visually-hidden "Loading..."]]
       [:h2 "Hello from React - page 2"])]))

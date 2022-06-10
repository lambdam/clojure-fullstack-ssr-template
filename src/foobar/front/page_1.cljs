(ns foobar.front.page-1
  (:require [goog.dom :as dom]
            [foobar.shared.views.page-1 :as sv-page-1]
            [uix.dom.alpha :as uixd]))


(defn ^:dev/after-load render []
  (uixd/hydrate
    [sv-page-1/root {}]
    (dom/getRequiredElement "react-root")))

(defn main! []
  (render))

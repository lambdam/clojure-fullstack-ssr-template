(ns foobar.front.page-2
  (:require [foobar.shared.views.page-2 :as sv-page-2]
            [goog.dom :as dom]
            [uix.dom.alpha :as uixd]))

(defn ^:dev/after-load render []
  (uixd/hydrate
    [sv-page-2/root {}]
    (dom/getRequiredElement "react-root")))

(defn main! []
  (render))

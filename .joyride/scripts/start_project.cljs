(ns helpers
  (:require ["vscode" :as vscode]
            [joyride.core :as joyride]
            [promesa.core :as p]))

(def oc (joyride.core/output-channel))

(defn main []
  (p/let [terminal (vscode/window.createTerminal
                    #js {:name "Run project"})]
    (.show terminal true)
    (.sendText terminal "clojure -Sdeps '{:deps {nrepl/nrepl {:mvn/version,\"0.9.0\"},cider/cider-nrepl {:mvn/version,\"0.28.3\"}}}' -M:dev:cljs -m nrepl.cmdline --middleware \"[cider.nrepl/cider-middleware,shadow.cljs.devtools.server.nrepl/middleware]\"")))

(when (= (joyride/invoked-script) joyride/*file*)
  (main))

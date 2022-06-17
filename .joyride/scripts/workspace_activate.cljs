(ns workspace-activate
  (:require [joyride.core :as joyride]
            [promesa.core :as p]
            ["vscode" :as vscode]))

(defonce !db (atom {:disposables []}))

;; To make the activation script re-runnable we dispose of
;; event handlers and such that we might have registered
;; in previous runs.
(defn- clear-disposables! []
  (run! (fn [disposable]
          (.dispose disposable))
        (:disposables @!db))
  (swap! !db assoc :disposables []))

;; Pushing the disposables on the extension context's
;; subscriptions will make VS Code dispose of them when the
;; Joyride extension is deactivated.
(defn- push-disposable [disposable]
  (swap! !db update :disposables conj disposable)
  (-> (joyride/extension-context)
      .-subscriptions
      (.push disposable)))

(defn- my-main []
  (clear-disposables!)
  ;; Then you can `(push-disposable ...)` all you like
  
  ;; Activate Calva and require our library
  (-> (vscode/extensions.getExtension "betterthantomorrow.calva")
      ;; Force the Calva extension to activate 
      (.activate)
      ;; The promise will resolve with the extension's API as the result
      (p/then (fn [_api]
                (.appendLine (joyride/output-channel) "Calva activated. Requiring dependent namespaces.")
                ;; In `helpers` the Calva extension
                ;; is required, which will work fine since now Calva is active.
                (require '[helpers])
                ;; Code in your keybindings can now use the `helpers` namespace
                ))
      (p/catch (fn [error]
                 (vscode/window.showErrorMessage (str "Requiring Calva failed: " error)))))
  )

(when (= (joyride/invoked-script) joyride/*file*)
  (my-main))

(ns helpers
  (:require ["vscode" :as vscode]
            ["ext://betterthantomorrow.calva$v0" :as calva]
            [joyride.core :as joyride]
            [promesa.core :as p]))

(def oc (joyride.core/output-channel))

(defn evaluate-in-session+ [session-key code]
  (p/let [result (calva/repl.evaluateCode
                  session-key
                  code
                  #js {:stdout #(.append oc %)
                       :stderr #(.append oc (str "Error: " %))})]
    (.-result result)))

(defn clj-evaluate+ [code]
  (evaluate-in-session+ "clj" code))

(defn cljs-evaluate+ [code]
  (evaluate-in-session+ "cljs" code))

(defn evaluate+
  "Evaluates `code` in whatever the current session is."
  [code]
  (evaluate-in-session+ (calva/repl.currentSessionKey) code))

;; Utils for REPL-ing Joyride code, when connected to a project REPL.

(defn joyride-eval-current-form+ []
  (vscode/commands.executeCommand "joyride.runCode" (second (calva/ranges.currentForm))))

(defn joyride-eval-top-level-form+ []
  (vscode/commands.executeCommand "joyride.runCode" (second (calva/ranges.currentTopLevelForm))))

;; Bind to some nice keyboard shortcuts, e.g. like so:
;;  {
;;      "key": "cmd+ctrl+enter",
;;      "command": "joyride.runCode",
;;      "args": "(z-joylib.calva-api/joyride-eval-current-form)",
;;  },
;;  {
;;      "key": "cmd+alt+enter",
;;      "command": "joyride.runCode",
;;      "args": "(z-joylib.calva-api/joyride-eval-top-level-form)",
;;  },

;; Convenience function for stopping and starting the server when
;; refreshing namespaces. (In waiting for Calva supporting this with
;; before/after refresh hooks.)

(defn refresh-and-restart []
  (p/do (clj-evaluate+ "(user/stop)")
        (.appendLine oc "Refreshing namespaces (result output in Calva says, sorry) ...")
        (vscode/commands.executeCommand "calva.refreshAll")
        (clj-evaluate+ "(user/start)")))

;; Bind to some keyboard shortcut, e.g. like so:
;;  {
;;      "key": "cmd+ctrl+n",
;;      "command": "joyride.runCode",
;;      "args": "(helpers/refresh-and-restart)",
;;  },

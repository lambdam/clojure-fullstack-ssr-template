((nil . ((cider-ns-refresh-before-fn . "user/stop")
         (cider-ns-refresh-after-fn  . "user/start")
         (cider-clojure-cli-global-options . "-A:dev:cljs")
         ;; https://github.com/clojure-emacs/cider/issues/2812
         (cider-default-cljs-repl . custom)
         (cider-custom-cljs-repl-init-form . "(do (user/cljs-repl))")
         (eval . (progn
                   (make-variable-buffer-local 'cider-jack-in-nrepl-middlewares)
                   (add-to-list 'cider-jack-in-nrepl-middlewares "shadow.cljs.devtools.server.nrepl/middleware")))
         )))

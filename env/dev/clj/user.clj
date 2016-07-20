(ns user
  (:require [mount.core :as mount]
            cuarenta-game.core))

(defn start []
  (mount/start-without #'cuarenta-game.core/repl-server))

(defn stop []
  (mount/stop-except #'cuarenta-game.core/repl-server))

(defn restart []
  (stop)
  (start))



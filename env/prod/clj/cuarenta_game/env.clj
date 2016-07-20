(ns cuarenta-game.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[cuarenta_game started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[cuarenta_game has shut down successfully]=-"))
   :middleware identity})

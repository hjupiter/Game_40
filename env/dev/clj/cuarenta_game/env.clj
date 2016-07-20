(ns cuarenta-game.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [cuarenta-game.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[cuarenta_game started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[cuarenta_game has shut down successfully]=-"))
   :middleware wrap-dev})

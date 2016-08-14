(ns cuarenta-game.routes.home
  (:require [cuarenta-game.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [taoensso.carmine :as car :refer (wcar) ]))

(def server-connection {:pool {} :spec {:host "127.0.0.1" :port 6379 }})
(def server-connection-1 {:pool {} :spec {:host "127.0.0.1" :port 6379 }})
(def server-connection-2 {:pool {} :spec {:host "127.0.0.1" :port 6379 }})

(defmacro redis-subscribe [& body] `(car/wcar server-connection ~@body))
(defmacro redis-publish [& body] `(car/wcar server-connection-1 ~@body))
(defmacro redis [& body] `(car/wcar server-connection-2 ~@body))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defn board-page [] 
  (layout/render "tablero.html")
)

(defn subscribe-room [name]
  (redis-subscribe (car/subscribe name))
)
(defn set-users-room [room name]
  (redis (car/rpush (str "room:" room) name ))
)

(defroutes home-routes
  (GET "/home" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/testing" request (interpose "," (keys request))) 
  (GET "/login" [] (layout/render "login.html"))
  (GET "/json" [] ( fn [req] {:body {:name "Carlos Mauricio" :last_name "Gualan LLanos" :data { :card 40 }}}))
  (GET "/redis" [] ( fn [req] (redis (car/publish "foobar" "Hello to foobar!"))))
  (GET "/room" [] ( fn [req] (set-users-room "testing-room" "Carlos" ) (subscribe-room "testing-room1")
                             
                             {:body {:name "Carlos Mauricio" :last_name "Gualan LLanos" :data { :card 40 }}}
                  )
  )
)


 
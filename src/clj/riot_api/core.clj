(ns riot-api.core
  (:require [compojure.core     :refer [defroutes GET]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defroutes routes
  (GET "/" [] "Hello World"))

(def app
  (-> routes))

(defn -main [& args]
  (run-jetty app {:port 4040}))

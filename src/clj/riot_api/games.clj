(ns riot-api.games
  (:require [cheshire.core   :refer [parse-string]]
            [clj-http.client :as client]
            [clojure.set     :refer [union]]
            [riot-api.core   :refer [url generate-url]]))

(def default
  {:api-version "v1.3"
   :region "na"
   :api-key nil})

(defn- games [data]
  (:games
    (parse-string
      (:body
        (client/get
          (generate-url :games data))) true)))

(defn recent [data]
  (games (union default data)))

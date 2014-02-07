(ns riot-api.games
  (:require [cheshire.core   :refer [parse-string]]
            [clojure.set     :refer [union]]
            [riot-api.util   :refer [generate-url request]]))

(def default
  {:api-version "v1.3"
   :region "na"
   :api-key nil
   :summoner-id nil})

(defn recent [api-key id]
  (:games
    (request
      :games
      (union default {:api-key api-key :summoner-id id}))))

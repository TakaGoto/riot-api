(ns riot-api.league
  (:require [riot-api.util :refer [request]]
            [clojure.set   :refer [union]]))

(def default
  {:api-version "v2.3"
   :region "na"
   :api-key nil
   :summoner-id nil})

(defn by-id [data]
  (request :league (union default data)))

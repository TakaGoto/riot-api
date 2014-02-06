(ns riot-api.summoners
  (:require [cheshire.core   :refer [parse-string]]
            [clojure.set     :refer [union]]
            [riot-api.util   :refer [generate-url request]]))

(def default
  {:api-version "v1.3"
   :region "na"
   :api-key nil
   :summoner-names nil})

(defn by-names [data]
  (request :summoners-by-names (union default data)))

(defn by-ids [data]
  (request :summoners-by-ids (union default data)))

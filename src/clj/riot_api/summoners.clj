(ns riot-api.summoners
  (:require [cheshire.core   :refer [parse-string]]
            [clojure.set     :refer [union]]
            [riot-api.util   :refer [generate-url request]]))

(def default
  {:api-version "v1.3"
   :region "na"
   :api-key nil
   :summoner-names nil})

(defn by-name [data]
  (request :summoners (union default data)))

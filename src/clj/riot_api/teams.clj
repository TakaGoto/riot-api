(ns riot-api.teams
  (:require [cheshire.core   :refer [parse-string]]
            [clojure.set     :refer [union]]
            [riot-api.util   :refer [request]]
            [riot-api.url-generator.core :refer [generate-url]]))

(def default
  {:api-version "v2.2"
   :region "na"
   :api-key nil
   :summoner-id nil})

(defn by-id [api-key id]
  (request
    :teams
    (union default {:api-key api-key :summoner-id id})))


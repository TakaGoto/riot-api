(ns riot-api.summoners
  (:require [cheshire.core               :refer [parse-string]]
            [clojure.set                 :refer [union]]
            [riot-api.util               :refer [request]]
            [riot-api.url-generator.core :refer [generate-url]]))

(def default
  {:api-version "v1.3"
   :region "na"
   :api-key nil
   :summoner-names nil})

(defn by-names [api-key names]
  (request
    :summoners-by-names
    (union default {:api-key api-key :summoner-names names})))

(defn by-ids [api-key ids]
  (request
    :summoners-by-ids
    (union default {:api-key api-key :summoner-ids ids})))

(defn names-by-ids [api-key ids]
  (map
    #(:name %) (vals (by-ids api-key ids))))

(defn ids-by-names [api-key names]
  (map
    #(:id %) (vals (by-names api-key names))))



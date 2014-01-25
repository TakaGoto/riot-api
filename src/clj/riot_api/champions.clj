(ns riot-api.champions
  (:require [cheshire.core      :refer [parse-string]]
            [clj-http.client    :as client]
            [clojure.set        :refer [union]]
            [riot-api.core      :refer [url generate-url]]))

(def default
  {:api-version "v1.1"
   :region "na"
   :api-key nil
   :name nil})

(defn- champions [data]
  (:champions
    (parse-string
      (client/get
        (generate-url :champions data)) true)))

(defn by-name [data]
  (let [champions (champions (union default data))]
    (map
      #(if (= (:name data) (:name %)) %)
      champions)))

(defn get-all []
  (champions default))

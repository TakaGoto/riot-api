(ns riot-api.champions
  (:require [cheshire.core      :refer [parse-string]]
            [clj-http.client    :as client]
            [clojure.set        :refer [union]]
            [clojure.string     :refer [capitalize]]
            [riot-api.core      :refer [url generate-url]]))

(def default
  {:api-version "v1.1"
   :region "na"
   :api-key nil})

(defn- champions [data]
  (:champions
    (parse-string
      (:body
        (client/get
          (generate-url :champions data))) true)))

(defn- match-champion-name [data response]
  (=
   (capitalize (:name data))
   (:name response)))

(defn- union-data [data]
  (union default data))

(defn by-name [data]
  (let [champions (champions (union-data data))]
    (first
      (keep
        #(if (match-champion-name data %) %)
        champions))))

(defn get-all [data]
  (champions (union-data data)))

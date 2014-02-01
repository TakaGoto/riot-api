(ns riot-api.champions
  (:require [cheshire.core      :refer [parse-string]]
            [clojure.set        :refer [union]]
            [clojure.string     :refer [capitalize]]
            [riot-api.util      :refer [generate-url request]]))

(def default
  {:api-version "v1.1"
   :region "na"
   :api-key nil})

(defn- champions [data]
  (:champions
    (request :champions (union default data))))


(defn- name-matches-champion [name champion]
  (=
   (capitalize name)
   (:name champion)))

(defn by-name [data]
  (let [champions (champions data)]
    (first
      (keep
        #(if (name-matches-champion (:name data) %) %)
        champions))))

(defn get-all [data]
  (champions data))

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
          (generate-url :champions (union default data)))) true)))

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

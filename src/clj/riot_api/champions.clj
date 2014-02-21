(ns riot-api.champions
  (:require [cheshire.core               :refer [parse-string]]
            [clojure.set                 :refer [union]]
            [clojure.string              :refer [capitalize]]
            [riot-api.util               :refer [request]]
            [riot-api.url-generator.core :refer [generate-url]]))

(def default
  {:api-version "v1.1"
   :region "na"
   :api-key nil})

(defn- champions [api-key]
  (:champions
    (request
      :champions
      (assoc default :api-key api-key))))

(defn- name-matches-champion [name champion]
  (=
   (capitalize name)
   (:name champion)))

(defn by-name [api-key name]
  (let [champions (champions api-key)]
    (first
      (keep
        #(if (name-matches-champion name %) %)
        champions))))

(defn get-all [api-key]
  (champions api-key))

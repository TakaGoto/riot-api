(ns riot-api.core
  (:require [cheshire.core      :refer [parse-string]]
            [clj-http.client    :as client]
            [clojure.set        :refer [union]]))

(def url "https://prod.api.pvp.net/api/lol/")

(defn generate-url [type data]
  (if
    (= :champions type)
    (str url (:api-version data) "/champion?api_key=" (:api-key data))))

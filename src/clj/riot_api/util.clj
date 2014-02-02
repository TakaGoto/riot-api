(ns riot-api.util
  (:require [cheshire.core      :refer [parse-string]]
            [clj-http.client    :as client]
            [clojure.set        :refer [union]]))

(def url "https://prod.api.pvp.net/api/lol/")

(defn- ensure-summoner-id [data]
  (if (nil? (:summoner-id data))
    (throw (Exception. "Summoner ID is needed for this api call"))))

(defn- ensure-api-key [data]
  (if (nil? (:api-key data))
    (throw (Exception. "API key must be included"))))

(defn- ensure-summoner-name [data]
  (if (nil? (:summoner-names data))
    (throw (Exception. "summoner name list cannot be empty"))))

(defn- get-api-url [type data]
  (case type
    :champions "/champion"
    :teams (do (ensure-summoner-id data)
               (str "/team/by-summoner/" (:summoner-id data)))
    :games (do (ensure-summoner-id data)
               (str "/game/by-summoner/" (:summoner-id data) "/recent"))
    :summoners (do (ensure-summoner-name data)
                   (str "/summoner/by-name/" (:summoner-name data)))))

(defn generate-url [type data]
  (ensure-api-key data)
  (str
    url
    (:region data) "/"
    (:api-version data)
    (get-api-url type data)
    "?api_key=" (:api-key data)))

(defn request [api-type data]
  (parse-string
    (:body
      (client/get
        (generate-url api-type data))) true))

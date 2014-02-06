(ns riot-api.util
  (:require [cheshire.core      :refer [parse-string]]
            [clj-http.client    :as client]
            [clojure.set        :refer [union]]
            [clojure.string     :refer [join]]))

(def url "https://prod.api.pvp.net/api/lol/")

(defn- ensure-not-nil [key data]
  (if (nil? (key data))
    (throw (Exception. (str key " cannot be nil")))))

(defn- generate-teams-url [data]
  (do (ensure-not-nil :summoner-id data)
      (str "/team/by-summoner/" (:summoner-id data))))

(defn- generate-games-url [data]
  (do (ensure-not-nil :summoner-id data)
      (str "/game/by-summoner/" (:summoner-id data) "/recent")))

(defn- generate-summoners-by-names-url [data]
  (do (ensure-not-nil :summoner-names data)
      (str "/summoner/by-name/"
           (join "," (:summoner-names data)))))

(defn- generate-summoners-by-ids-url [data]
  (do (ensure-not-nil :summoner-ids data)
      (str "/summoner/"
           (join "," (:summoner-ids data)))))

(defn- get-api-url [type data]
  (case type
    :champions "/champion"
    :teams (generate-teams-url data)
    :games (generate-games-url data)
    :summoners-by-names (generate-summoners-by-names-url data)
    :summoners-by-ids (generate-summoners-by-ids-url data)))

(defn generate-url [type data]
  (ensure-not-nil :api-key data)
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

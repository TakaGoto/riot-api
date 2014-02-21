(ns riot-api.url-generator.league
  (:require [riot-api.url-generator.util :refer [ensure-not-nil]]))

(defn generate-league-url-by-id [data]
  (do (ensure-not-nil :summoner-id data)
      (str "/league/by-summoner/" (:summoner-id data) "/entry")))

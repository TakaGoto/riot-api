(ns riot-api.url-generator.game
  (:require [riot-api.url-generator.util :refer [ensure-not-nil]]))

(defn generate-game-url-by-id [data]
  (do (ensure-not-nil :summoner-id data)
      (str "/game/by-summoner/" (:summoner-id data) "/recent")))

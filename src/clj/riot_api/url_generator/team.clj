(ns riot-api.url-generator.team
  (:require [riot-api.url-generator.util :refer [ensure-not-nil]]))

(defn generate-team-url-by-id [data]
  (do (ensure-not-nil :summoner-id data)
      (str "/team/by-summoner/" (:summoner-id data))))

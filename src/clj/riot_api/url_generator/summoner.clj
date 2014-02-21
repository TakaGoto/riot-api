(ns riot-api.url-generator.summoner
  (:require [riot-api.url-generator.util     :refer [ensure-not-nil]]
            [clojure.string                  :refer [join]]))

(defn generate-summoner-url-by-names [data]
  (do (ensure-not-nil :summoner-names data)
      (str "/summoner/by-name/"
           (join "," (:summoner-names data)))))

(defn generate-summoner-url-by-ids [data]
  (do (ensure-not-nil :summoner-ids data)
      (str "/summoner/"
           (join "," (:summoner-ids data)))))

(ns riot-api.url-generator.core
  (:require [clojure.set                     :refer [union]]
            [riot-api.url-generator.league   :refer [generate-league-url-by-id]]
            [riot-api.url-generator.summoner :refer [generate-summoner-url-by-ids
                                                     generate-summoner-url-by-names]]
            [riot-api.url-generator.team     :refer [generate-team-url-by-id]]
            [riot-api.url-generator.game     :refer [generate-game-url-by-id]]
            [riot-api.url-generator.util     :refer [ensure-not-nil]]))

(def url "https://prod.api.pvp.net/api/lol/")

(defn- get-api-url [type data]
  (case type
    :champions          "/champion"
    :teams              (generate-team-url-by-id data)
    :games              (generate-game-url-by-id data)
    :summoners-by-names (generate-summoner-url-by-names data)
    :summoners-by-ids   (generate-summoner-url-by-ids data)
    :league             (generate-league-url-by-id data)))

(defn generate-url [type data]
  (ensure-not-nil :api-key data)
  (str
    url
    (:region data) "/"
    (:api-version data)
    (get-api-url type data)
    "?api_key=" (:api-key data)))

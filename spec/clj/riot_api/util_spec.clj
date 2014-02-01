(ns riot-api.util-spec
  (:require [clojure.set                  :refer [union]]
            [riot-api.util       :refer [generate-url]]
            [riot-api.games               :as games]
            [riot-api.champions           :as champions]
            [riot-api.teams               :as teams]
            [speclj.core                  :refer :all]))


(describe "champions url"
  (with champions-url "https://prod.api.pvp.net/api/lol/na/v1.1/champion?api_key=api-key")
  (with games-url "https://prod.api.pvp.net/api/lol/na/v1.3/game/by-summoner/123456/recent?api_key=api-key")
  (with teams-url "https://prod.api.pvp.net/api/lol/na/v2.2/team/by-summoner/123456?api_key=api-key")

  (it "generates the url for champions api"
    (should= @champions-url
      (generate-url :champions (union champions/default {:api-key "api-key"}))))

  (it "generates the url for games api"
    (should= @games-url
      (generate-url :games (union games/default {:api-key "api-key" :summoner-id "123456"}))))

  (it "generates the url for teams api"
    (should= @teams-url
      (generate-url :teams (union teams/default {:api-key "api-key" :summoner-id "123456"}))))

  (it "should throw Exception if api key is nil"
    (should-throw Exception
      (generate-url :champions champions/default)))

  (it "should throw Exception if summoner id is needed and is nil"
    (should-throw Exception
      (generate-url :games (union games/default {:api-key "api-key"})))))

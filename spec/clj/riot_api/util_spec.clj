(ns riot-api.util-spec
  (:require [clojure.set                       :refer [union]]
            [riot-api.url-generator.core       :refer [generate-url]]
            [riot-api.games                    :as games]
            [riot-api.champions                :as champions]
            [riot-api.teams                    :as teams]
            [riot-api.league                   :as league]
            [riot-api.summoners                :as summoners]
            [speclj.core                       :refer :all]))

(describe "util"
  (describe "champions url"
    (with champions-url "https://prod.api.pvp.net/api/lol/na/v1.1/champion?api_key=api-key")
    (with games-url "https://prod.api.pvp.net/api/lol/na/v1.3/game/by-summoner/123456/recent?api_key=api-key")
    (with teams-url "https://prod.api.pvp.net/api/lol/na/v2.2/team/by-summoner/123456?api_key=api-key")

    (with summoners-by-names-url "https://prod.api.pvp.net/api/lol/na/v1.3/summoner/by-name/fuuterror,nanaterror?api_key=api-key")
    (with summoners-by-ids-url "https://prod.api.pvp.net/api/lol/na/v1.3/summoner/123456,654321?api_key=api-key")

    (with league-by-id "https://prod.api.pvp.net/api/lol/na/v2.3/league/by-summoner/123456?api_key=api-key")

    (it "generates the url for champions"
      (should= @champions-url
        (generate-url :champions (union champions/default {:api-key "api-key"}))))

    (it "generates the url for games"
      (should= @games-url
        (generate-url :games (union games/default {:api-key "api-key" :summoner-id "123456"}))))

    (it "generates the url for teams"
      (should= @teams-url
        (generate-url :teams (union teams/default {:api-key "api-key" :summoner-id "123456"}))))

    (it "generates the url for league"
      (should= @league-by-id
        (generate-url :league (union league/default {:api-key "api-key" :summoner-id "123456"}))))

    (it "generates the url for summoners by ids"
      (should= @summoners-by-ids-url
        (generate-url :summoners-by-ids (union summoners/default {:api-key "api-key" :summoner-ids ["123456" "654321"]}))))

    (it "generates the url for summoners by names"
      (should= @summoners-by-names-url
        (generate-url :summoners-by-names (union summoners/default {:api-key "api-key" :summoner-names ["fuuterror" "nanaterror"]})))))

  (describe "throw exception for nil"
    (it "should throw Exception if api key is nil"
      (should-throw Exception
        (generate-url :champions champions/default)))

    (it "should throw Exception if summoner id is needed and is nil"
      (should-throw Exception
        (generate-url :games (union games/default {:api-key "api-key"}))))))

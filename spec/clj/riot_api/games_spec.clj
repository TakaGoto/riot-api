(ns riot-api.games-spec
  (:require [riot-api.games  :as games]
            [cheshire.core   :refer [generate-string]]
            [clj-http.client :as client]
            [speclj.core     :refer :all]))

(def games
  (read-string
    (slurp
      "spec/clj/riot_api/fixtures/games.clj")))

(describe "recent games"
  (it "retrieves 10 games"
    (with-redefs [client/get (fn [_] {:body (generate-string games)})]
      (let [games (games/recent {:api-key "api-key"})]
      (should= 10
        (count games)))))

  (it "retrieves game mode"
    (with-redefs [client/get (fn [_] {:body (generate-string games)})]
      (let [games (games/recent {:api-key "api-key"})]
        (should= "MATCHED_GAME"
          (:gameType (first games)))))))


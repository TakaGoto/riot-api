(ns riot-api.games-spec
  (:require [riot-api.games         :as games]
            [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def games
  (fixture "games"))

(describe "recent games"
  (it "retrieves 10 games"
    (with-redefs [client/get (fn [_] {:body (generate-string games)})]
      (let [games (games/recent "api-key" "123456")]
      (should= 10
        (count games)))))

  (it "retrieves game mode"
    (with-redefs [client/get (fn [_] {:body (generate-string games)})]
      (let [games (games/recent "api-key" "123456")]
        (should= "MATCHED_GAME"
          (:gameType (first games)))))))


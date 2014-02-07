(ns riot-api.league-spec
  (:require [riot-api.league        :as league]
            [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def league
  (fixture "league"))

(describe "league"
  (it "retrieves league of a given summoner id"
    (with-redefs [client/get (fn [_] {:body (generate-string league)})]
      (let [summoner-id ((first league) "participantId")
            tier ((first league) "tier")
            league (league/by-id "api-key" summoner-id)]
        (should= tier
          (:tier (first league)))))))

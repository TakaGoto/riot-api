(ns riot-api.league-spec
  (:require [riot-api.league        :as league]
            [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def league
  (fixture "league"))

(describe "league"
  (context "by summoner id"
    (it "retrieves league"
      (with-redefs [client/get (fn [_] {:body (generate-string league)})]
        (let [summoner-id (:playerOrTeamId league)
              tier (:tier league)
              result (league/by-id "api-key" summoner-id)]
          (should= tier
            (:tier result))
          (should= (:playerOrTeamName league)
            (:playerOrTeamName result)))))

    (it "retrieves the summoner's tier/division"
      (with-redefs [client/get (fn [_] {:body (generate-string league)})]
        (let [summoner-id (:playerOrTeamId league)
              tier (:tier league)
              result (league/get-division-by-id "api-key" summoner-id)]
          (should=
            tier
            result))))))

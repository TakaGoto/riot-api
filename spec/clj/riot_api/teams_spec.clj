(ns riot-api.teams-spec
  (:require [riot-api.teams         :as teams]
            [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def team
  (fixture "teams"))

(describe "summoner's team"
  (it "retrieves the summoner's team list and info"
    (with-redefs [client/get (fn [_] {:body (generate-string team)})]
      (let [teams (teams/by-summoner-id {:api-key "api-key" :summoner-id "123456"})]
        (should= "Fall Out Boy"
          (:name (first teams)))))))

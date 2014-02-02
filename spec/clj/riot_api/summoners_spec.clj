(ns riot-api.summoners-spec
  (:require [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.summoners     :as summoners]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def summoners
  (fixture "multiple_summoner_summary"))

(def summoner
  (fixture "summoner_summary"))

(describe "summoner"
  (describe "summoner summary"
    (it "retrieves a champion"
      (with-redefs [client/get (fn [_] {:body (generate-string summoner)})]
        (let [summoner (summoners/by-name {:api-key "api-key" :summoner-names "summonerOne"})]
          (should= "summonerblah"
            (:name (:summonerOne summoner))))))))

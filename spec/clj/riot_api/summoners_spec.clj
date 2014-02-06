(ns riot-api.summoners-spec
  (:require [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.summoners     :as summoners]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def summoners
  (fixture "summoner_by_names"))

(describe "summoner"
  (describe "summoner summary"
    (it "retrieves a summoner"
      (with-redefs [client/get (fn [_] {:body (generate-string summoners)})]
        (let [summoner (summoners/by-names {:api-key "api-key" :summoner-names ["summonerOne"]})]
          (should= "summonerblah"
            (:name (:summonerOne summoner))))))

    (it "retrieves multiple summoners"
      (with-redefs [client/get (fn [_] {:body (generate-string summoners)})]
        (let [summoners (summoners/by-names {:api-key "api-key" :summoner-names ["summonerOne" "summonerTwo"]})]
          (should= 2 (count summoners))
          (should= "summonerblah"
            (:name (:summonerOne summoners)))
          (should= "summonerblah1"
            (:name (:summonerTwo summoners)))))))

  )

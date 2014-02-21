(ns riot-api.summoners-spec
  (:require [cheshire.core          :refer [generate-string]]
            [clj-http.client        :as client]
            [riot-api.summoners     :as summoners]
            [riot-api.spec-helper   :refer [fixture]]
            [speclj.core            :refer :all]))

(def summoner-by-names
  (fixture "summoner_by_names"))

(def summoner-by-ids
  (fixture "summoner_by_summoner_id"))

(describe "summoner summary"
  (it "retrieves a summoner"
    (with-redefs [client/get (fn [_] {:body (generate-string summoner-by-names)})]
      (let [summoner-name (first (keys summoner-by-names))
            summoner (summoners/by-names "api-key" [summoner-name])]
        (should= "summonerblah"
          (:name (:summonerOne summoner))))))

  (it "retrieves multiple summoners"
    (with-redefs [client/get (fn [_] {:body (generate-string summoner-by-names)})]
      (let [summoner-names (keys summoner-by-names)
            summoners (summoners/by-names "api-key" (vec summoner-names))]
        (should= 2 (count summoners))
        (should= "summonerblah"
          (:name (:summonerOne summoners)))
        (should= "summonerblah1"
          (:name (:summonerTwo summoners))))))

  (it "by summoner id"
    (with-redefs [client/get (fn [_] {:body (generate-string summoner-by-ids)})]
      (let [summoner-id (first (keys summoner-by-ids))
            summoner-name ((summoner-by-ids summoner-id) "name")
            summoner (summoners/by-ids "api-key" [summoner-id])]
        (should= "Syrdas"
          (:name ((keyword summoner-id) summoner))))))

  (it "retrieves multiple summoners by summoner ids"
    (with-redefs [client/get (fn [_] {:body (generate-string summoner-by-ids)})]
      (let [summoner-ids (keys summoner-by-ids)
            summoners (summoners/by-ids "api-key" (vec summoner-ids))]
        (should= 2
          (count summoners))
        (should= "Syrdas"
          (:name ((keyword (first summoner-ids)) summoners)))
        (should= "fuuTERROR"
          (:name ((keyword (second summoner-ids)) summoners))))))

  (it "retrieves the names from a list of summoner ids"
    (with-redefs [client/get (fn [_] {:body (generate-string summoner-by-ids)})]
      (let [summoner-ids (keys summoner-by-ids)
            names (summoners/names-by-ids "api-key" (vec summoner-ids))]
        (should= 2
          (count names))
        (should-contain "fuuTERROR" names)
        (should-contain "Syrdas" names))))

  (it "retrieves the ids from a list of names"
    (with-redefs [client/get (fn [_] {:body (generate-string summoner-by-ids)})]
      (let [summoner-names (keys summoner-by-names)
            names (summoners/ids-by-names "api-key" (vec summoner-names))]
        (should= 2
          (count names))
        (should-contain 22478026 names)
        (should-contain 22478025 names)))))


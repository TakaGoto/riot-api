(ns riot-api.champions-spec
  (:require [clj-http.client      :as client]
            [cheshire.core        :refer :all]
            [speclj.core          :refer :all]
            [riot-api.spec-helper :refer [fixture]]
            [riot-api.champions   :as champions]))

(def champions
  (fixture "champions"))


(describe "champions"

  (it "returns a champion by name"
    (with-redefs [client/get (fn [_] {:body (generate-string champions)})]
      (let [champion (champions/by-name {:name "Zyra" :api-key "api-key"})]
        (should= "Zyra"
          (:name champion)))))

  (it "returns all champions"
    (with-redefs [client/get (fn [_] {:body (generate-string champions)})]
      (let [champions (champions/get-all {:api-key "api-key"})]
        (should= 2 (count champions)))))

  (it "beautifies champions name"
    (with-redefs [client/get (fn [_] {:body (generate-string champions)})]
      (let [champion (champions/by-name {:name "zyra" :api-key "api-key"})]
        (should= "Zyra"
          (:name champion))))))

(ns riot-api.champions-spec
  (:require [clj-http.client      :as client]
            [cheshire.core        :refer :all]
            [speclj.core          :refer :all]
            [riot-api.champions   :as champions]))

(def response {:champions [{:name "taka"} {:name "foo"}]})

(with-redefs [client/get (fn [_] {:body (generate-string response)})]
  (describe "champions"

    (it "returns a champion by name"
      (let [champion (champions/by-name {:name "taka" :api-key "api-key"})]
        (should= "taka"
          (:name (first champion)))))

    (it "returns all champions"
      (let [champions (champions/get-all {:api-key "api-key"})]
        (should= 2 (count champions))))))

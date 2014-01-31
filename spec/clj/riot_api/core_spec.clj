(ns riot-api.core-spec
  (:require [clojure.set     :refer [union]]
            [riot-api.core   :refer [generate-url]]
            [speclj.core     :refer :all]))

(def champions-data
  {:api-version "v1.1"
   :region "na"
   :api-key nil
   :name "taka"})

(describe "champions url"
  (with url "https://prod.api.pvp.net/api/lol/na/v1.1/champion?api_key=api-key")

  (it "generates the url champions api"
    (should= @url
      (generate-url :champions (union champions-data {:api-key "api-key"}))))

  (it "should throw Exception if api key is nil"
    (should-throw Exception
      (generate-url :champions champions-data))))

(ns riot-api.util
  (:require [cheshire.core               :refer [parse-string]]
            [clj-http.client             :as client]
            [riot-api.url-generator.core :refer [generate-url]]))

(defn request [api-type data]
  (parse-string
    (:body
      (client/get
        (generate-url api-type data))) true))

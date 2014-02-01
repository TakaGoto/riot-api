(ns riot-api.spec-helper)

(defn fixture [file-name]
  (read-string
    (slurp
      (str "spec/clj/riot_api/fixtures/" file-name ".clj"))))

(defproject riot-api "0.1.0"
  :description "Clojure wrapper for Riot API"
  :dependencies [[cheshire "5.3.1"]
                 [clj-http "0.7.8"]
                 [org.clojure/clojure "1.5.1"]
                 [speclj "2.8.0"]]

  :plugins [[speclj "2.8.0"]]

  :test-paths ["spec/clj"]
  :source-paths ["src/clj"])

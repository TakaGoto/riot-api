(defproject riot-api "0.1.0"
  :description "Clojure wrapper for Riot API"
  :dependencies [[compojure "1.1.6"]
                 [org.clojure/clojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.2.0"]]

  :profiles {
    :dev {
      :main riot-api.core }}

  :source-paths ["src/clj"]
  )

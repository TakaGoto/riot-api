(ns riot-api.games-spec
  (:require [riot-api.games :refer :all]
            [speclj.core    :refer :all]))

(describe "recent games"
  (with response {:recentGames [{:game {:championId 1}} {:game {:championId 2}}]})

  (it "is true"
    (should= true true)))


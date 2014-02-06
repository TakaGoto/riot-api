(ns riot-api.core
  (:require [riot-api.games               :as games]
            [riot-api.champions           :as champions]
            [riot-api.teams               :as teams]
            [riot-api.league              :as league]
            [riot-api.summoners           :as summoners]))

(defn games-by-id [data]
  (games/recent data))

(defn champions-by-name [data]
  (champions/by-name))

(defn all-champions [data]
  (champions/get-all))

(defn league-by-id [data]
  (league/by-id data))

(defn summoners-by-ids [data]
  (summoners/by-ids data))

(defn summoners-by-names [data]
  (summoners/by-names data))

(defn teams-by-id [data]
  (teams/by-id data))

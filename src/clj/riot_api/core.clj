(ns riot-api.core
  (:require [riot-api.games               :as games]
            [riot-api.champions           :as champions]
            [riot-api.teams               :as teams]
            [riot-api.league              :as league]
            [riot-api.summoners           :as summoners]))

(defn games-by-id [api-key id]
  (games/recent api-key id))

(defn champion-by-name [api-key name]
  (champions/by-name api-key name))

(defn all-champions [api-key]
  (champions/get-all api-key))

(defn league-by-id [api-key id]
  (league/by-id api-key id))

(defn summoners-by-ids [api-key ids]
  (summoners/by-ids api-key ids))

(defn summoners-by-names [api-key names]
  (summoners/by-names api-key names))

(defn teams-by-id [api-key id]
  (teams/by-id api-key id))

(ns waffle-solver.factories
  (:require [clojure.string :refer [upper-case]])
  (:require [waffle-solver.game-types :refer [game-types]])
  (:gen-class))

(defn create-letter
  "Creates an empty letter"
  [letter type]
  {:pre [(re-matches #"[A-Za-z\.]" letter)
         (some #{type} [:correct :maybe :incorrect])]}
  {:letter (upper-case letter) :type type})

(defn create-game "Creates an empty game board"
  ([] (create-game :waffle))
  ([type]
  {:pre (contains? game-types type)}
  (let [element-count (get game-types type)]
    (repeat element-count (create-letter "." :incorrect)))))
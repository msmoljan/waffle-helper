;; Presentation code for the game board

(ns waffle-solver.display
  (:require [waffle-solver.game-types :refer [game-types]])
  (:gen-class))

(defn print-board [game game-type]
  (let ))

(defn- letter-count-in-first-row [game-type]
  (case game-type
    :waffle 5
    :deluxe-waffle 7))

(defn- print-board-based-on-first-row [game count-of-letters-in-first-row])
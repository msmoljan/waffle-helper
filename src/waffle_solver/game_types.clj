(ns waffle-solver.game-types
  (:gen-class))

;; Defines the game types by name and the number of letters each contains
(def game-types
  {:waffle        {:total-letters        21
                   :letters-in-first-row 5}
   :deluxe-waffle {:total-letters        40
                   :letters-in-first-row 7}})
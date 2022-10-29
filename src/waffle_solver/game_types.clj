(ns waffle-solver.game-types
  (:gen-class))

;; Defines the game types by name and the number of letters each contains
(def game-types
  {:waffle        {:total-squares        21
                   :squares-in-first-row 5}
   :deluxe-waffle {:total-squares        40
                   :squares-in-first-row 7}})
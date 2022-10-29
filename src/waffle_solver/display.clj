;; Presentation code for the game board

(ns waffle-solver.display
  (:require [waffle-solver.game-types :refer [game-types]])
  (:gen-class))

(declare
  count-letters-in-even-rows
  partition-game
  take-two-rows
  print-odd-row
  print-even-row)

(defn print-game [game game-type]
  (let [letters-in-odd-rows (get-in game-types [game-type :squares-in-first-row])
        letters-in-even-rows (count-letters-in-even-rows letters-in-odd-rows)
        partitioned-game (partition-game game letters-in-odd-rows letters-in-even-rows)]
    (for [[odd-row even-row] (partition 2 partitioned-game)]
      (do
        (print-odd-row odd-row)
        (print-even-row even-row)))))

(defn- count-letters-in-even-rows [letters-in-odd-rows]
  (Math/round (float (/ letters-in-odd-rows 2))))

(defn- partition-game
  "Partitions the game into a list of lists, each internal list being a row of the game"
  [game letters-in-odd-rows letters-in-even-rows]
  (if (empty? game)
    '()
    (concat
      (take-two-rows game letters-in-odd-rows letters-in-even-rows)
      (partition-game
        (drop (+ letters-in-odd-rows letters-in-even-rows) game)
        letters-in-odd-rows
        letters-in-even-rows))))

(defn- take-two-rows
  "Creates a list of lists of the first two rows in the game segment"
  [game-segment letters-in-odd-rows letters-in-even-rows]
  (list
    (take letters-in-odd-rows game-segment)
    (take letters-in-even-rows (drop letters-in-odd-rows game-segment))))

(defn- print-odd-row [letters]
  (doseq [letter letters] (print (str letter " ")))
  (println))

(defn- print-even-row [letters]
  (doseq [letter letters] (print (str letter "   ")))
  (println))
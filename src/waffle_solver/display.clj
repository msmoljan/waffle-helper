;; Presentation code for the game board

(ns waffle-solver.display
  (:require
    [clojure.string :refer [join, trim]]
    [waffle-solver.game-types :refer [game-types]])
  (:gen-class))

(declare
  game-display-string
  count-letters-in-even-rows
  partition-squares
  take-two-rows
  row-string
  odd-row-string
  even-row-string)

(defn print-game [game]
  (print (game-display-string game)))

(defn game-display-string [game]
  (let
    [game-type (get game :type)
     squares (get game :squares)
     letters-in-odd-rows (get-in game-types [game-type :squares-in-first-row])
     letters-in-even-rows (count-letters-in-even-rows letters-in-odd-rows)
     partitioned-squares (partition-squares squares letters-in-odd-rows letters-in-even-rows)]
    (join
      (map
        (fn [row-pair]
          (str
            (odd-row-string (first row-pair))
            (even-row-string (second row-pair))))
        (partition-all 2 partitioned-squares)))))

(defn- count-letters-in-even-rows [letters-in-odd-rows]
  (Math/round (float (/ letters-in-odd-rows 2))))

(defn- partition-squares
  "Partitions the game into a list of lists, each internal list being a row of the game"
  [game letters-in-odd-rows letters-in-even-rows]
  (if (empty? game)
    nil
    (concat
      (take-two-rows game letters-in-odd-rows letters-in-even-rows)
      (partition-squares
        (drop (+ letters-in-odd-rows letters-in-even-rows) game)
        letters-in-odd-rows
        letters-in-even-rows))))

(defn- take-two-rows
  "Creates a list of lists of the first two rows in the game segment"
  [game-segment letters-in-odd-rows letters-in-even-rows]
  (let
    [first-row (take letters-in-odd-rows game-segment)
     second-row (take letters-in-even-rows (drop letters-in-odd-rows game-segment))]
    (if (empty? second-row)
      (list first-row)
      (list first-row second-row))))

(defn- odd-row-string [squares]
  (row-string squares " "))

(defn- even-row-string [squares]
  (row-string squares "   "))

(defn- row-string [squares separator-string]
  (if (or (nil? squares) (empty? squares))
    ""
    (str
      (trim
        (join
          (map
            (fn [square] (str (get square :letter) separator-string))
            squares)))
      "\n")))


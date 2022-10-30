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
    [squares (get game :squares)
     odd-row-letter-count (get-in game [:setup :squares-in-first-row])
     even-row-letter-count (count-letters-in-even-rows odd-row-letter-count)
     partitioned-squares (partition-squares squares odd-row-letter-count even-row-letter-count)]
    (join
      (map
        (fn [row-pair]
          (str
            (odd-row-string (first row-pair))
            (even-row-string (second row-pair))))
        (partition-all 2 partitioned-squares)))))

(defn- count-letters-in-even-rows [odd-row-letter-count]
  (Math/round (float (/ odd-row-letter-count 2))))

(defn- partition-squares
  "Partitions the game into a list of lists, each internal list being a row of the game"
  [game odd-row-letter-count even-row-letter-count]
  (if (empty? game)
    nil
    (concat
      (take-two-rows game odd-row-letter-count even-row-letter-count)
      (partition-squares
        (drop (+ odd-row-letter-count even-row-letter-count) game)
        odd-row-letter-count
        even-row-letter-count))))

(defn- take-two-rows
  "Creates a list of lists of the first two rows in the game segment"
  [game-segment odd-row-letter-count even-row-letter-count]
  (let
    [first-row (take odd-row-letter-count game-segment)
     second-row (take even-row-letter-count (drop odd-row-letter-count game-segment))]
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


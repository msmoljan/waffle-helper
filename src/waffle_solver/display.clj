;; Presentation code for the game board

(ns waffle-solver.display
  (:require [lanterna.terminal :as t])
  (:gen-class))

(declare
  game-display-string
  count-letters-in-even-rows
  partition-squares
  take-two-rows
  print-row
  print-odd-row
  print-even-row)

(defn print-game [game terminal]
  (let [partitioned-squares (partition-squares game)]
    (doseq [row-pair (partition-all 2 partitioned-squares)]
      (print-odd-row (first row-pair) terminal)
      (print-even-row (second row-pair) terminal))))

(defn- count-letters-in-even-rows [odd-row-letter-count]
  (Math/round (float (/ odd-row-letter-count 2))))

(defn- partition-squares
  "Partitions the game into a list of lists, each internal list being a row of the game"
  ([game]
   (let
     [squares (get game :squares)
      odd-row-letter-count (get-in game [:setup :squares-in-first-row])
      even-row-letter-count (count-letters-in-even-rows odd-row-letter-count)]
     (partition-squares squares odd-row-letter-count even-row-letter-count)))
  ([squares odd-row-letter-count even-row-letter-count]
   (if (empty? squares)
     nil
     (concat
       (take-two-rows squares odd-row-letter-count even-row-letter-count)
       (partition-squares
         (drop (+ odd-row-letter-count even-row-letter-count) squares)
         odd-row-letter-count
         even-row-letter-count)))))

(defn- take-two-rows
  "Creates a list of lists of the first two rows in the game segment"
  [game-segment odd-row-letter-count even-row-letter-count]
  (let
    [first-row (take odd-row-letter-count game-segment)
     second-row (take even-row-letter-count (drop odd-row-letter-count game-segment))]
    (if (empty? second-row)
      (list first-row)
      (list first-row second-row))))

(defn- print-odd-row [squares terminal]
  (print-row squares " " terminal))

(defn- print-even-row [squares terminal]
  (print-row squares "     " terminal))

(defn- print-row [squares separator-string terminal]
  (if (not (or (nil? squares) (empty? squares)))
    (doseq [square squares]
      (t/set-bg-color
        terminal
        (case (get square :type)
          :correct :green
          :maybe :yellow
          :incorrect :default))
      (do
        (t/put-string terminal (str " " (get square :letter) " "))
        (t/set-bg-color terminal :default)
        (t/put-string terminal separator-string)))
    nil)
  (t/put-string terminal "\n"))


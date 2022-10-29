(ns waffle-solver.core
  (:require [waffle-solver.factories :refer [create-game]])
  (:require [waffle-solver.display :refer [print-game]])
  (:gen-class))

(defn -main
  [& args]
  (print-game (create-game) :waffle))

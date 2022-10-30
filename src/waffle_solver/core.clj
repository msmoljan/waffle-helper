(ns waffle-solver.core
  (:require [waffle-solver.factories :refer [create-game]])
  (:require [waffle-solver.display :refer [print-game]])
  (:require [lanterna.terminal :as t])
  (:gen-class))

(defn -main
  [& args]
  (let
    [terminal (t/get-terminal :unix)]
    (do
      (t/start terminal)
      (print-game (create-game) terminal))))

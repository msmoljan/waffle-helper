(ns waffle-solver.core
  (:require [lanterna.terminal :as t]
            [waffle-solver.display :refer [print-game]]
            [waffle-solver.factories :refer [create-game]])
  (:gen-class))

(defn- generate-example-game []
  (let
    [game (create-game :waffle)
     squares (vec (get game :squares))]
    (let
      [updated-squares (assoc squares
                         0 {:letter "A", :type :correct}
                         4 {:letter "T", :type :correct}
                         6 {:letter "E", :type :maybe})]
      (assoc game :squares updated-squares))))

(defn -main
  [& args]
  (let
    [terminal (t/get-terminal :unix)]
    (do
      (t/start terminal)

      (print-game (generate-example-game) terminal))))

(ns waffle-solver.core
  (:require [lanterna.terminal :as t]
            [waffle-solver.display :refer [print-game]]
            [waffle-solver.factories :refer [create-game]])
  (:gen-class))

(defn- generate-example-game []
  (let
    [game (create-game :deluxe-waffle)
     squares (assoc (vec (get game :squares))
               0 {:letter "P", :type :correct}
               1 {:letter "A", :type :correct}
               2 {:letter "E", :type :maybe}
               3 {:letter "R", :type :maybe}
               4 {:letter "N", :type :correct}
               5 {:letter "T", :type :correct}
               6 {:letter "S", :type :correct}
               8 {:letter "E", :type :maybe}
               23 {:letter "C", :type :maybe}
               39 {:letter "T", :type :correct})]
    (assoc game :squares squares)))

(defn -main
  [& args]
  (let
    [terminal (t/get-terminal :unix)]
    (do
      (t/start terminal)

      (print-game (generate-example-game) terminal))))

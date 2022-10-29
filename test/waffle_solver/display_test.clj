(ns waffle-solver.display-test
  (:require [clojure.test :refer :all])
  (:require [waffle-solver.factories :refer [create-game]])
  (:require [waffle-solver.display :refer [game-display-string]]))

(def expected-new-waffle-game-display
  ". . . . .\n.   .   .\n. . . . .\n.   .   .\n. . . . .\n")

(def expected-new-deluxe-waffle-game-display
  ". . . . . . .\n.   .   .   .\n. . . . . . .\n.   .   .   .\n. . . . . . .\n.   .   .   .\n. . . . . . .\n")

(deftest game-display-string-test
  (testing "New waffle game display string should be properly generated")
  (is (= (game-display-string (create-game :waffle) :waffle) expected-new-waffle-game-display)))

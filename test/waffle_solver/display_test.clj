(ns waffle-solver.display-test
  (:require [clojure.test :refer :all])
  (:require [waffle-solver.factories :refer [create-game]])
  (:require [waffle-solver.display :refer [game-display-string]]))

(def expected-new-waffle-game-display
  ". . . . .\n.   .   .\n. . . . .\n.   .   .\n. . . . .\n")

(def expected-new-deluxe-waffle-game-display
  ". . . . . . .\n.   .   .   .\n. . . . . . .\n.   .   .   .\n. . . . . . .\n.   .   .   .\n. . . . . . .\n")

(deftest waffle-game-display-string-test
  (testing "New Waffle game display string should be properly generated")
  (is (= (game-display-string (create-game :waffle) :waffle) expected-new-waffle-game-display)))

(deftest deluxe-waffle-game-display-string-test
  (testing "New Deluxe Waffle game display string should be properly generated")
  (is (= (game-display-string (create-game :deluxe-waffle) :deluxe-waffle) expected-new-deluxe-waffle-game-display)))

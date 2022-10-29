(ns waffle-solver.factories-test
  (:require [clojure.test :refer :all]
            [waffle-solver.factories :refer :all]
            [waffle-solver.game-types :refer [game-types]]))

(deftest create-letter-test
  (testing "A letter can be properly created."
    (is
      (and
        (= {:letter "A" :type :correct} (create-letter "A" :correct))
        (= {:letter "B" :type :incorrect} (create-letter "b" :incorrect))))))

(deftest create-letter-invalid-letter-test
  (testing "Creating a letter fails if the character is not a letter"
    (is (thrown? AssertionError (create-letter "3" :correct)))))

(deftest create-letter-invalid-type-test
  (testing "Creating a letter fails if the game type is invalid"
    (is (thrown? AssertionError (create-letter "4" :invalid-type)))))

(deftest create-game-without-parameters-test
  (testing "Creating a game without parameters will create a standard waffle game"
    (is (= (create-game) (create-game :waffle)))))

(deftest create-game-test
  (doseq [[game-type game-setup] game-types]
    (let [game (create-game game-type)]
      (is
        (and
          (= (count game) (get game-setup :total-letters))
          (every? #(= % {:letter "." :type :incorrect}) game))))))
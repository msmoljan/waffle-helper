(ns waffle-solver.factories-test
  (:require [clojure.test :refer :all]
            [waffle-solver.factories :refer :all]))

(deftest create-letter-test
  (testing "A letter can be properly created."
    (is (and 
      (= {:letter "A" :type :correct} (create-letter "A" :correct))
      (= {:letter "B" :type :incorrect} (create-letter "b" :incorrect))))))

(deftest create-letter-invalid-letter-test
  (testing "Creating a letter fails if the character is not a letter"
    (is (thrown? java.lang.AssertionError (create-letter "3" :correct)))))

(deftest create-letter-invalid-type-test
  (testing "Creating a letter fails if the character is not a letter"
    (is (thrown? java.lang.AssertionError (create-letter "4" :invalid-type)))))
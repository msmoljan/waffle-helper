(ns waffle-solver.factories
  (:gen-class))

(defn create-letter
  "Creates an empty letter"
  [letter type]
  {:pre [
    (re-matches #"[A-Za-z\.]" letter)
    (some #{type} [:correct :maybe :incorrect])
  ]}
  {:letter (clojure.string/upper-case letter) :type type})

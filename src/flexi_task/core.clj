(ns flexi-task.core
  (:require [clojure.string :as str]))


(defn sanitize
  "Sanitize input string"
  [x]
  (if (string? x)
    (str/trim x)
    x))

(defn validate
  "Validate input string"
  [x]
  (cond
    (nil? x) "No value was found"
    (not (string? x)) "Value is not a string"
    (not (re-matches #"\p{Ll}*" x)) "Value can be only lower-case letters"
    :else nil))

(defn scramble?
  "Checks if portion of A string characters can be rearranged to match B string.
  Performance notes:
  Has O(3N) complexity. Can be optimized for short-circuit cases and for O(2N) as well if needed."
  [a b]
  (let [b-freqs (atom (frequencies b))]
    (doseq [k     a
            :let  [v (get @b-freqs k 0)]
            :when (> v 0)]
      (swap! b-freqs update k dec))
    (empty? (filter (comp pos? val) @b-freqs))))

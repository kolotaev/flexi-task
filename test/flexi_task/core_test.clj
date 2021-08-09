(ns flexi-task.core-test
  (:require [clojure.test :refer :all]
            [flexi-task.core :refer :all]))


(deftest scramble?-test
  (testing "scramble? works correctly"
    (are [x y] [= x y]
      true (scramble? "" "")
      true (scramble? "a" "")
      false (scramble? "" "a")
      false (scramble? "" "ax")
      true (scramble? "a" "a")
      true (scramble? "aa" "a")
      true (scramble? "aa" "aa")
      true (scramble? "ca" "ac")
      false (scramble? "a" "aa")
      true (scramble? "aba" "aa")
      true (scramble? "ababb" "b")
      true (scramble? "ababb" "aba")
      true (scramble? "fdsa" "asdf")
      false (scramble? "fdsa" "asdfu")
      true (scramble? "строка" "строка")
      true (scramble? "рссокккат" "строка")
      false (scramble? "рссоккка" "строка")
      true (scramble? "rekqodlw" "world")
      true (scramble? "cedewaraaossoqqyt" "codewars")
      false (scramble? "katas" "steak"))))

(deftest sanitize-test
  (testing "sanitize works correctly"
    (are [x y] [= x y]
      nil (sanitize nil)
      ["d"] (sanitize ["d"])
      "" (sanitize "")
      "as" (sanitize "as")
      "as" (sanitize "as    ")
      "as" (sanitize "    as")
      "as" (sanitize "    as ")
      "as sd" (sanitize "as sd")
      "as sd" (sanitize "  as sd   "))))

(deftest validate-test
  (testing "validate works correctly"
    (are [x y] [= x y]
      "No value was found" (validate nil)
      "Value is not a string" (validate ["abc"])
      "Value can be only lower-case letters" (validate "abc1")
      "Value can be only lower-case letters" (validate "ASDF")
      "Value can be only lower-case letters" (validate "asdf qwerty")
      nil (validate "")
      nil (validate "asdf")
      nil (validate "строка")
      "Value can be only lower-case letters" (validate "Cтрока"))))
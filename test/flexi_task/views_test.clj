(ns flexi-task.views-test
  (:require [clojure.test :refer :all]
            [flexi-task.views :refer :all]))


(deftest index-page-contents-test
  (testing "index page should contain proper contents (just for sanity)"
    (is (.contains (index-page) "<title>FlexiTask</title>"))
    (is (.contains (index-page) "<h1>Let's play scramble!</h1>"))))

(deftest index-inputs-test
  (testing "index page should contain proper inputs"
    (is (.contains (index-page) "<input id=\"word1\""))
    (is (.contains (index-page) "<input id=\"word2\""))
    (is (.contains (index-page) "<input id=\"answer\""))
    (is (.contains (index-page) "<input id=\"send-btn\" onclick=\"flexi_task.core.check_scramble()\""))))

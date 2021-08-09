(ns flexi-task.web-test
  (:require [clojure.test :refer :all]
            [flexi-task.web :refer :all]
            [ring.mock.request :as mock]))


(deftest scramble-handler-test
  (testing "Scramble handler without expected errors"
    (is (= (scramble-handler (-> (mock/request :post "/scramble")
                                 (mock/json-body {:str1 "bar", :str2 "baz"})))
           {:status  200
            :body    {:errors [] :answer false}}))
    (is (= (scramble-handler (-> (mock/request :post "/scramble")
                                 (mock/json-body {:str1 " bar ", :str2 "ba "})))
           {:status  200
            :body    {:errors [] :answer true}})))
  (is (= (scramble-handler (-> (mock/request :post "/scramble")
                               (mock/json-body {:str1 "", :str2 ""})))
         {:status  200
          :body    {:errors [] :answer true}}))

  (testing "Scramble handler with expected errors"
    (is (= (scramble-handler (-> (mock/request :post "/scramble")
                                 (mock/json-body {})))
           {:status  400
            :body    {:errors ["No value was found" "No value was found"] :answer false}}))
    (is (= (scramble-handler (-> (mock/request :post "/scramble")
                                 (mock/json-body {:str1 " bar99 ", :str2 "ba "})))
           {:status  400
            :body    {:errors ["Value can be only lower-case letters"] :answer false}}))))

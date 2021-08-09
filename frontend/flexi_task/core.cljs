(ns flexi-task.core
  (:require [ajax.core :refer [POST]]))


(defn- extract-input-value
  "Get value from input"
  [id]
  (. (. js/document getElementById id) -value))

(defn get-inputs
  "Get values from input string fields as a map"
  []
  {:str1 (extract-input-value "word1")
   :str2 (extract-input-value "word2")})

(defn handle-error!
  "Simple error handler in the form of an alert dump"
  [errors]
  (js/alert errors))

(defn handle-result!
  "Checks for response contents and sets answer into answer field"
  [resp]
  (let [errors (get resp "errors")
        answer (get resp "answer")]
    (cond
      (not (empty? errors)) (handle-error! errors))
    (boolean? answer) (set! (. (. js/document getElementById "answer") -value) answer)))

(defn check-scramble
  "Check if two words scramble by calling a server API"
  []
  (POST "/scramble"
    {:format :json
     :params (get-inputs)
     :handler handle-result!
     :error-handler handle-error!}))

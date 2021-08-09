(ns flexi-task.views
  (:require
   [hiccup.page :refer [html5 include-js]]))


(defn index-page
  "Render index main page with data and scripts"
  []
  (html5
   [:head
    [:title "FlexiTask"]
    (include-js "/js/main.js")]
   [:body
    [:h1 "Let's play scramble!"]
    [:input {:type "text" :id "word1" :required true :placeholder "String #1 here"}]
    [:input {:type "text" :id "word2" :required true :placeholder "String #2 here"}]
    [:input {:type "text" :id "answer" :placeholder "Answer"}]
    [:input {:type "button" :id "send-btn" :onclick "flexi_task.core.check_scramble()" :value "Check scramble"}]]))

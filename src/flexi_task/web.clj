(ns flexi-task.web
  (:use compojure.core
        flexi-task.core
        flexi-task.views)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.json :refer [wrap-json-response json-body-request]]
            [compojure.route :as route]))


(defn scramble-handler
  "Handler for scramble action. Handles input and checks for a scramble match"
  [req]
  (let [body (get (json-body-request req {}) :body)
        inputs (map (comp sanitize (partial get body)) ["str1" "str2"])
        validation-errors (filter some? (map validate inputs))]
    (if (empty? validation-errors)
      {:status 200 :body {:errors [] :answer (apply scramble? inputs)}}
      {:status 400 :body {:errors validation-errors :answer false}})))

(defroutes app-routes
  "Application REST API routes definition"
  (GET "/" [] (index-page))
  (POST "/scramble" req (scramble-handler req))
  (route/resources "/")
  (route/not-found "Sorry, page was not found"))

(defn -main []
  (jetty/run-jetty (wrap-json-response app-routes) {:port 3000}))

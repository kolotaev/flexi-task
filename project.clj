(defproject flexi-task "Simple scramble Web App"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [compojure "1.6.2"]
                 [hiccup "1.0.5"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-jetty-adapter "1.9.4"]
                 [ring/ring-json "0.5.1"]
                 ; cljs deps
                 [org.clojure/clojurescript "1.10.866"]
                 [cljs-ajax "0.7.5"]
                 ; test deps
                 [ring/ring-mock "0.4.0"]]
  :repl-options {:init-ns flexi-task.core}
  :plugins [[lein-cljfmt "0.8.0"]
            [lein-cljsbuild "1.1.8"]]
  :cljsbuild {
    :builds [{:source-paths ["frontend"]
              :compiler {:output-to "resources/public/js/main.js"
                         :optimizations :whitespace
                         :pretty-print true}}]}
  :main flexi-task.web)

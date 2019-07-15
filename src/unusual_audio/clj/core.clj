(ns unusual-audio.clj.core
  (:gen-class)
  (:require [unusual-audio.clj.routes.core :as r]
            [unusual-audio.clj.utils.core :as u]
            [unusual-audio.clj.models.sql :as sql]
            [environ.core :as environ]
            [immutant.web :as server])
  (:import [com.opentable.db.postgres.embedded EmbeddedPostgres]))

(def host (environ/env :host))
(def port (environ/env :port))

(defn -main [& args]
  (sql/init!)
  (server/server
   (if (= "development" (environ/env :environment))
     (server/run-dmc r/app :host host :port port)
     (server/run r/app :host host :port port))))

(ns unusual-audio.clj.routes.core
  (:require [ring.middleware.json :as json]
            [ring.middleware.file :as file]
            [ring.middleware.content-type :as ct]
            [ring.middleware.resource :as resource]
            [unusual-audio.clj.roles.core :as roles]
            [unusual-audio.clj.utils.core :as u]
            [unusual-audio.clj.routes.middleware :as middleware]
            [unusual-audio.clj.routes.blog :as blog]
            [unusual-audio.clj.routes.account :as account]
            [unusual-audio.clj.views.core :as views]
            [unusual-audio.cljc.routes :as routing]
            [unusual-audio.cljc.validators :as v]
            [compojure.core :as r]
            [compojure.route :as route]
            [clojure.string :as s]
            [environ.core :as environ]))

(def api-views
  {:login account/login
   :logout account/logout
   :get-account-info account/get-account-info
   :blog blog/blog-entries
   :blog-entry blog/blog-entry
   :new-blog-entry blog/new-blog-entry
   :delete-blog-entry blog/delete-blog-entry})

(defn page-handler [request handler-name]
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    views/index})

(defn validation-errors [body route]
  (when-let [validator (v/validators route)]
    (->> validator
         (map
          (fn [[field field-validator]]
            [field (field-validator (body field))]))
         (remove (fn [[field result]] (nil? result)))
         (into {}))))

(defn api-view [{:keys [body] :as request} handler-name]
  (when-let [view-fn (api-views handler-name)]
    (let [errors (validation-errors body handler-name)]
      (if (empty? errors)
        (view-fn request)
        {:status 400
         :body {:errors errors}}))))

(r/defroutes routes
  (route/resources "/")
  (route/not-found {:status  404
                    :headers {"Content-Type" "text/html"}
                    :body    views/index}))

(def app
  (-> routes
      (middleware/wrap-bidi routing/page-routes page-handler)
      (middleware/wrap-bidi routing/api-routes api-view)
      (json/wrap-json-response)
      (json/wrap-json-body {:keywords? true})
      (roles/wrap-security)
      (ct/wrap-content-type)))

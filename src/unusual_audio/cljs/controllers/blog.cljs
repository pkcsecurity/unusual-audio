(ns unusual-audio.cljs.controllers.blog
  (:require [unusual-audio.cljc.routes :as routes]
            [unusual-audio.cljs.xhr :as xhr]
            [unusual-audio.cljs.models.blog :as m]))

(defn blog-entries []
      (xhr/send-get
        (routes/api :blog)
        :success-atom m/all-entries))

(defn blog-entry [{:keys [id]}]
      (reset! m/blog-entry nil)
      (xhr/send-get
        (routes/api :blog-entry :id id)
        :success-atom m/blog-entry))

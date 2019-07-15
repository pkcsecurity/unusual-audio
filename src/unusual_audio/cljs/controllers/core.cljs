(ns unusual-audio.cljs.controllers.core
  (:require [unusual-audio.cljs.controllers.blog :as blog]))

(def page-initializers
  {:blog-entry blog/blog-entry
   :blog blog/blog-entries})

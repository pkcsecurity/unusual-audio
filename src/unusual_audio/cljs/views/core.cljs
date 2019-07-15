(ns unusual-audio.cljs.views.core
  (:require [unusual-audio.cljc.routes :as routes]
            [unusual-audio.cljs.views.blog :as blog]
            [unusual-audio.cljs.views.account :as account]
            [unusual-audio.cljs.views.components :as c]
            [reagent.session :as session]
            [reagent.core :as r]))

(defn index [params]
  [:div
   [:h1 "unusual-audio"]
   [:p
    "Brevity's default styles are pretty basic.  To tailor them to your project, see "
    [:a {:href "https://tachyons.io/docs/"} "the tachyons documentation"] "."]])

(defn four-o-four [params]
  [:div
   [:h1 "404: Page not found"]
   [:p ":("]])

(def views
  {:index index
   :login account/login
   :four-o-four four-o-four
   :blog blog/blog
   :blog-entry blog/blog-entry})

(defn page-contents [route]
  (let [page (:current-page route)
        params (:route-params route)]
    [:div.mw7.pv3.ph5.center
     [(views page) params]]))

(defn layout-old []
  (fn []
    (let [route (session/get :route)]
      [:div
       [c/header]
       ^{:key route} [page-contents route]
       [c/footer]])))

(defn unusual-logo []
  [:div.navbar-logo
   [:div.navbar-logo__image
    [:img
     {:src "/img/assets/svg/logo-unusual-audio.svg"}]]])

(defn nav-button [title href & {:keys [on-hover on-click]}]
  (let [hover? (r/atom false)]
    (fn [title href & {:keys [on-hover on-click]}]
      [:div.navbar__button
       {:on-mouse-enter #(reset! hover? true)
        :on-mouse-leave #(reset! hover? false)
        :class (when @hover? :navbar__button--hover)}
       [:a {:href href}
        [:span title]]])))

(defn nav-button-circle [radius]
  [:div
   {:style {:width (str radius "px")
            :height (str radius "px")
            :position :absolute
            :bottom "0px"
            :right "-178px"}}
   [:div.navbar__circle]])

(defn unusual-nav-buttons []
  [:div.navbar__buttons
   [nav-button-circle 653]
   [nav-button "about"]
   [nav-button "features"]])

(defn nav []
  [:div.navbar
   [unusual-logo]
   [unusual-nav-buttons]])

(defn front-header []
  [:div.main-content__front-header-container
   [:span.main-content__front-header
    "everything headphones"]])

(defn front-buy-section-left []
  [:div.front-buy-section__left-container
   [:div.front-buy-section__left-dash-line]
   [:div.front-buy-section__left-description
    "They say everything happens for a reason."
    [:div.front-buy-section__left-description-detailed
     "Everything Headphones arose from a need to do (almost) everything."]]])

(defn button-pri [text & {:keys [class on-hover-class hover?]}]
  (let [hover? (or hover? (r/atom false))]
    (fn [text & {:keys [class on-hover-class hover?]}]
      [:button
       {:on-mouse-enter #(reset! hover? true)
        :on-mouse-leave #(reset! hover? false)
        :class (if @hover?
                 (str class " " on-hover-class)
                 class)}
       text])))

(defn front-buy-section-right []
  (let [hover? (r/atom false)]
    (fn []
      [:div.front-buy-section__right-container
       [button-pri
        [:div.order-button__text-container
         [:span.order-button__text "order now"]
         [:img {:src (if @hover?
                       "/img/assets/svg/arrow-right-red.svg"
                       "/img/assets/svg/arrow-right.svg")}]]
        :class "order-button"
        :on-hover-class "order-button--hover"
        :hover? hover?]])))

(defn front-buy-section []
  [:div.front-buy-section
   [front-buy-section-left]
   [front-buy-section-right]])

(defn main-content []
  [:div
   [front-header]
   [front-buy-section]])

(defn layout []
  (fn []
    (let [route (session/get :route)]
      [:div
       [:div.bg-photo-headphones
        [:div.bg-headphones-img
         [nav]
         [main-content]]]])))

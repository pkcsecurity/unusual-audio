(ns unusual-audio.clj.views.core
    (:require [environ.core :as environ]
              [hiccup.core :as html]
              [garden.core :as css]))

(def clearfix
  (css/css
   [[:.clearfix
     {:overflow :auto}]
    [:.clearfix:after
     {:content "\" \""
      :visibility :hidden
      :clear :both
      :height 0
      :display :block}]
    [:.clearfix:before
     {:content "\" \""
      :visibility :hidden
      :clear :both
      :height 0
      :display :block}]]))

(def bg-photo-container
  [[:.bg-photo-headphones {:width "100%"
                           :height "100%"
                           :object-fit :contain
                           :background-blend-mode :multiply
                           :background-image "linear-gradient(to bottom, #fff297, #ffffff)"}]
   [:.bg-headphones-img
    {:background-image "url('img/assets/png/photo-headphones.png')"
     :background-position :center
     :background-repeat :no-repeat
     :margin "0 auto"
     :width "1440px"
     :height "1000px"}]])

(def navbar-styles
  [[:.navbar
    {:display :flex
     :justify-content "space-around"
     :width "100%"
     :height "160px"}]
   [:.navbar-logo
    {:position :relative
     :height "100%"
     :width "200px"}]
   [:.navbar-logo__image
    {:width "111.7px"
     :height "100px"
     :margin-top "43px"
     :object-fit :contain}]
   [:.navbar-logo__image--to-bottom
    {:position :absolute
     :bottom 0}]
   [:.navbar__logo--to-bottom
    {:position :absolute
     :bottom 0}]
   [:.navbar__buttons
    {:display :flex
     :position :relative
     :margin "0 -25px"
     :margin-top "38px"}]
   [:.navbar__button
    {:text-transform "uppercase"
     :margin "0 25px"
     :padding-top "16px"
     :border-top-style :solid
     :border-width "5px"
     :border-color "rgba(0,0,0,0)"
     :font-family "Raleway"
     :font-size "20px"
     :font-weight "600"
     :font-style :normal
     :font-stretch :normal
     :line-height :normal
     :z-index 10
     :letter-spacing "2.4px"
     :color "#ffffff"}]
   [:.navbar__button--hover
    {:border-top-style :solid
     :border-width "5px"
     :border-color "#ffffff"
     :cursor :pointer}]
   [:.navbar__circle
    {:width "100%"
     :height "100%"
     :background-color "rgba(220, 122, 0, 0.25)"
     :border-radius "50%"}]])

(def main-content-styles
  [[:.main-content__front-header-container
    {:margin-left "17%"
     :margin-top "92px"}]
   [:.main-content__front-header
    {:text-transform :uppercase
     :width "874px"
     :height "65px"
     :font-family "Raleway"
     :font-size "55px"
     :font-weight "600"
     :font-style :normal
     :font-stretch :normal
     :line-height :normal
     :letter-spacing "6.6px"
     :color "#ffffff"}]
   [:.front-buy-section
    {:width "100%"
     :margin-top "120px"
     :display :flex
     :margin-left "171px"}]
   [:.front-buy-section__left-container
    {:position :relative
     :display :flex
     :margin-right "109px"}]
   [:.front-buy-section__left-dash-line
    {:width "80px"
     :margin-top "7px"
     :margin-right "20px"
     :border-top-style :solid
     :border-width "5px"
     :border-color "#ffffff"}]
   [:.front-buy-section__left-description
    {:width "470px"
     :height "160px"
     :font-family "Raleway"
     :font-size "40px"
     :font-weight "300"
     :font-style :normal
     :font-stretch :normal
     :line-height :normal
     :letter-spacing "2px"
     :color "#ffffff"}]
   [:.front-buy-section__left-description-detailed
    {:width "470px"
     :height "222px"
     :margin-top "0.6em"
     :font-family "Raleway"
     :font-size "25px"
     :font-weight "300"
     :font-style :normal
     :font-stretch :normal
     :line-height "1.6"
     :letter-spacing "1.9px"
     :color "#ffffff"}]
   [:.front-buy-section__right-container
    {}]])

(def order-button-style
  [[:.order-button
    {:outline :none
     :border :none
     :box-shadow :none
     :padding "0"
     :text-decoration :none
     :border-radius "5px"
     :width "340px"
     :height "75px"
     :background-color "#ff2828"
     :color "#ffffff"
     :transition "all 0.2s ease-in-out"
     :cursor :pointer}]
   [:.order-button--hover
    {:outline :none
     :border :none
     :box-shadow :none
     :padding "0"
     :text-decoration :none
     :border-radius "5px"
     :width "340px"
     :height "75px"
     :background-color "#ffffff"
     :color "#ff2828"
     :transition "all 0.2s ease-in-out"
     :cursor :pointer}]
   [:.order-button__text-container
    {:text-align :left
     :margin-left "30px"
     :display :flex
     :align-items :center}]
   [:.order-button__text
    {:width "182px"
     :height "30px"
     :font-family "Raleway"
     :font-size "25px"
     :font-weight "500"
     :font-style :normal
     :font-stretch :normal
     :line-height :normal
     :letter-spacing "3px"
     :margin-right "84px"
     :text-transform :uppercase}]
   [:.order-button :.button:focus {:outline :none}]])

(def core-css
  (css/css
    {:pretty-print? false}
    [:* {:box-sizing :border-box}]
    [:body {:font-size "16px"
            :line-height 1.5}]
    bg-photo-container
    navbar-styles
    main-content-styles
    clearfix
    order-button-style
    [:html :body
     {:color "#222"
      :font-weight 700}]))

(defn style [href & {:keys [integrity]}]
      [:link
       {:rel "stylesheet"
        :href href
        :integrity integrity
        :crossorigin :anonymous}])

(def index
  (html/html {:mode :html}
             [:head
              [:meta {:charset "utf-8"}]
              [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
              [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
              (style "/css/tachyons.min.css")
              (style "https://use.fontawesome.com/releases/v5.1.1/css/all.css"
                     :integrity "sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ")
              (style "https://fonts.googleapis.com/css?family=Raleway:300,400,500,600")
              [:style core-css]
              [:title "unusual-audio"]]
             [:body.avenir
              [:div#app]
              [:script {:src (if (= "development" (environ/env :environment)) "/js/development/index.js" "/js/release/index.js")}]]))


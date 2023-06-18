(ns example.views
  (:require
   [re-frame.core :as re-frame]
   [example.events :as events]
   [example.subs :as subs]))


(defn display-users [{:keys [id avatar email] first-name :first_name}]
  [:div.horizontal {:key id}
   [:img.pr-15 {:src avatar}]
   [:div
    [:h2 first-name]
    [:p (str "[" email "]")]]])





(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        loading (re-frame/subscribe [::subs/loading])
        users (re-frame/subscribe [::subs/users])]


    [:div
     [:h1 "Hello from " @name]
     (when @loading "Loading...")
     (map display-users @users)
     [:button {:on-click #(re-frame/dispatch [::events/fetch-users])} "Make API call"]
     [:button {:on-click #(re-frame/dispatch [::events/update-name "lala"])} "Update Name"]]))

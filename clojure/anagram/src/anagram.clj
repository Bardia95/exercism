(ns anagram
  (:require [clojure.string :as str]))

(defn is-anagram? [s t]
  (let [s (str/lower-case s)
        t (str/lower-case t)]
    (and (not= s t)
         (= (frequencies s) (frequencies t)))))

(defn anagrams-for [word candidates]
  (filter #(is-anagram? word %) candidates))

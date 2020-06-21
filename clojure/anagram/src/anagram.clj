(ns anagram
  (:require [clojure.string :as str]))

(def alphabet-map (zipmap (range 97 123) (repeat 26 0)))


(defn is-anagram? [s t]
  (if (not= (count s) (count t))
    false
    (loop [i 0
           c alphabet-map]
      (if (= i (count s))
        (= c alphabet-map)
        (let [d (update c (int (.charAt s i)) inc)
              e (update d (int (.charAt t i)) dec)]
          (recur (inc i) e))))))


(defn anagrams-for [word candidates]
  (let [word (str/lower-case word)]
    (filter #(and (is-anagram? word (str/lower-case %))
                  (not= word (str/lower-case %)))
            candidates)))

(ns anagram
  (:require [clojure.string :as str]))

(defn is-anagram? [s t freq]
  (and (not= s t)
       (= freq (frequencies t))))

(defn anagrams-for [word candidates]
  (let [word (str/lower-case word)
        freq (frequencies word)]
    (filter #(is-anagram? word (str/lower-case %) freq) candidates)))

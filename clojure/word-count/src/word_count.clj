(ns word-count
  (:require [clojure.string :as s]))


(defn word-count
  [s]
  (->> s
       s/lower-case
       (re-seq #"\w+")
       (frequencies)))

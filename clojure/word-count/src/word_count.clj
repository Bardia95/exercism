(ns word-count
  (:require [clojure.string :refer [lower-case]]))

(defn word-count
  [s]
  (->> s
       lower-case
       (re-seq #"\w+|\d+")
       (group-by identity)
       (reduce-kv #(assoc %1 %2 (count %3)) {})))

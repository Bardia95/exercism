(ns isbn-verifier
  (:require [clojure.string :as str]))

(defn isbn->nums
  [isbn]
  (let [isbn (str/replace isbn "-" "")
        check (last isbn)]
    (if (= check \X)
      (conj (mapv #(Character/digit % 10) (drop-last isbn)) 10)
      (mapv #(Character/digit % 10) isbn))))


(defn checksum?
  [nums]
  (zero? (mod (reduce + (map * nums (range 10 0 -1))) 11)))


(defn isbn?
  [s]
  (if-let [isbn (re-matches #"(?:\d-?){9}[\dX]" s)]
    (checksum? (isbn->nums isbn))
    false))

(ns isbn-verifier
  (:require [clojure.string :as str]))


(defn clean [isbn]
  (str/replace isbn "-" ""))


(defn valid-length? [isbn]
  (= (count (clean isbn)) 10))


(defn parse-ints [isbn]
  (let [check (last isbn)]
    (if (= check \X)
      (conj (mapv #(Character/digit % 10) (drop-last isbn)) 10)
      (mapv #(Character/digit % 10) (seq isbn)))))


(def parse-clean (comp parse-ints clean))


(defn valid-form [isbn]
  (re-matches #"(?:\d-?){9}[\dX]" isbn))


(def one-to-ten (reverse (range 1 11)))


(defn checksum? [nums]
  (= 0 (mod (reduce + (map * nums one-to-ten)) 11)))


(defn isbn? [isbn]
(if (valid-length? isbn)
  (if-let [isbn (valid-form isbn)]
    (checksum? (parse-clean isbn))
    false)
  false))

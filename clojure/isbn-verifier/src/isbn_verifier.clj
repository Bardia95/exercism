(ns isbn-verifier
  (:require [clojure.string :as str]))


(defn valid-length? [isbn]
  (= (count isbn) 10))


(defn clean-isbn [isbn]
  (str/replace isbn "-" ""))


(def valid-clean-isbn? (comp valid-length? clean-isbn))


(def valid-checks #{\X \1 \2\ \3 \4 \5 \6 \7 \8 \9})


(defn valid-check? [isbn]
  (let [check (last isbn)]
    (if (contains? valid-checks check)
      true
      false)))


(defn parse-ints [isbn]
  (let [check (last isbn)]
    (if (= check \X)
      (conj (mapv #(Character/digit % 10) (drop-last isbn)) 10)
      (mapv #(Character/digit % 10) (seq isbn)))))


(def parse-clean (comp parse-ints clean-isbn))


(defn no-letters? [isbn]
  (every? #(>= % 0) (drop-last (parse-clean isbn))))


(defn isbn? [isbn] ;; <- arglist goes here
  (if (and (valid-clean-isbn? isbn)
           (valid-check? isbn)
           (no-letters? isbn))
    (let [a (parse-clean isbn)
          b (reverse (range 1 11))
          c (map * a b)
          d (reduce + c)]
      (= (mod d 11) 0))
    false))

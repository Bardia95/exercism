(ns isbn-verifier)


(defn isbn->nums [isbn]
  (let [ch->num (fn [^Character c]
                  (cond (= \X c) 10
                        (Character/isDigit c) (Character/digit c 10)))]
    (keep ch->num isbn)))


(defn divisible-by? [d n] (zero? (mod n d)))


(defn checksum?
  [nums]
  (->> nums
       (map * (range 10 0 -1))
       (reduce +)
       (divisible-by? 11)))


(defn isbn?
  [s]
  (and (some? (re-matches #"(?:\d-?){9}[\dX]" s))
       (checksum? (isbn->nums s))))

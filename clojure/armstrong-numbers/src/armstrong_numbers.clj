(ns armstrong-numbers)

(defn armstrong? [num]
  (let [a (map #(Character/digit % 10) (str num))
        b (map #(reduce * (repeat (count (str num)) %)) a)
        c (reduce + b)]
    (= num c)))
;; => #'armstrong-numbers/armstrong?

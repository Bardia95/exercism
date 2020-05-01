(ns armstrong-numbers)

(defn armstrong? [num] ;; <- arglist goes here
  (= num (reduce + (map #(reduce * (repeat (count (str num)) %)) (map #(Character/digit % 10) (into [] (seq (str num)))))))
  )
;; => #'armstrong-numbers/armstrong?

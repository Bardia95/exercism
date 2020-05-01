(ns run-length-encoding)

;; My solution
(defn run-length-encode [plain-text]
  (let [grouped     (partition-by identity plain-text)
        interleaved (interleave
                     (map #(count %) grouped)
                     (map #(first %) grouped))
        encoded     (->>
                     interleaved
                     (map #(str %))
                     (filter #(not (= % 1)))
                     (clojure.string/join ""))]
    encoded))

;; More elegant solution
(defn run-length-encode [plain-text]
  (->> plain-text
       (partition-by identity)
       (map #(str (when-not (= 1 (count %)) (count %)) (first %)))
       (apply str)))

;; Even more elegant
(defn run-length-encode [s]
  (->> (partition-by identity s)
       (mapcat (juxt count first))
       (apply str)))

;; Elegant solution
(defn run-length-decode [s]
  (->> (re-seq #"(\d+)([a-zA-Z])" s)
       (mapcat (fn [[_ n ch]] (repeat (Integer/parseInt n) ch)))
       (apply str)))


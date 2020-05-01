(ns rna-transcription
  (:require [clojure.string :as s]))

(defn replace [nucleotide]
  (case nucleotide
    \C \G
    \G \C
    \A \U
    \T \A
    (assert false "Invalid sequence"))
  )

(defn to-rna [dna-seq]
  (apply str (map replace (seq dna-seq))))

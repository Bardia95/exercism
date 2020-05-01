(ns isbn-verifier)


(defn validate-string [isbn]
  (clojure.string/replace isbn "-" "")

  )

(defn isbn? [isbn] ;; <- arglist goes here
  (and (valid-length? isbn) (valid-checksum? isbn) (valid))

  )

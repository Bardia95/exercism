(ns reverse-string)

(defn reverse-string [s] ;; <- arglist goes here
  (apply str (into () s))
  )
;; => #'reverse-string/reverse-string

(reverse-string "1234")
;; => "4321"
(reverse-string "racecar")
;; => "racecar"
(reverse-string "here you are");; => "era uoy ereh"

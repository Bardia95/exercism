(ns bob (:require [clojure.string :as s]))

(declare silence? shout? question?)

(defn response-for [sentence]
  (cond
    (silence?  sentence) "Fine. Be that way!"
    (shout?    sentence) "Woah, chill out!"
    (question? sentence) "Sure."
    :else                "Whatever."))

(defn- silence?  [sentence] (s/blank? sentence))
(defn- question? [sentence] (= \? (last sentence)))
(defn- shout?    [sentence]
  (and (= sentence (s/upper-case sentence))
       (re-seq #"(?i) \p{L}" sentence)))

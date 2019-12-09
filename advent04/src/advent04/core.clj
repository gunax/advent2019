(ns advent04.core
  (:gen-class))

(defn split-digits
  "split a integer into a list of base-10 digits"
  [n]
  (if (< n 10)
    (list n)
    (concat (split-digits (quot n 10)) (list (mod n 10)))))

(defn has-adjacent-digits?
  ([n]
   (has-adjacent-digits? (rest n) (first n)))
  ([n last]
   (if (empty? n)
     false
     (if (= last (first n))
       true
       (has-adjacent-digits? (rest n) (first n))))))

(defn has-pair?
  ([n]
   (if (empty? n)
     false
     (has-pair? (first n) (rest n))))
  ([a n]
   (has-pair? a (first n) (rest n)))
  ([a b n]
   (if (= a b) ;found a pair (but could be a triple or more)
     (if (not= b (first n))
       true ;found a pair (and only a pair)!
       (has-pair? (drop-while #(= b %) n)))
     (has-pair? b (first n) (rest n)))))

(defn never-decrease?
  ([n]
   (never-decrease? (rest n) (first n)))
  ([n last]
   (if (empty? n)
     true
     (if (< (first n) (or last 0))
       false
       (never-decrease? (rest n) (first n))))))

(comment
  (split-digits 567)
  (has-adjacent-digits? '(1 2 3))
  (never-decrease? '(1 2 3))
  (has-pair? '(1 2 1 2 2 2 4))
  (->> (range 359282 820401) 
       (map split-digits) 
       (filter has-pair?)
       (filter never-decrease?)
       (count))
  )

(defn -main
  "Solve day4 part 2"
  [& args]
  (println   (->> (range (Integer/valueOf (first args)) (Integer/valueOf (second args)))
                  (map split-digits)
                  (filter has-pair?)
                  (filter never-decrease?)
                  (count))))

(comment
  (-main "123" "456"))

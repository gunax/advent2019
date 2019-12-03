(ns advent01.core
  (:gen-class))

(defn fuel
  "fuel required for given mass"
  [mass]
  (let [fuel_amount (- (quot mass 3) 2)]
    (if (<= fuel_amount 0)
      0
      (+ fuel_amount (fuel fuel_amount)))))

(defn -main
  "advent 2019 part 1-1"
  [& args]
  (println
   (apply +
   (map fuel
        (map read-string args)))))


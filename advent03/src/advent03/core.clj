(ns advent03.core
  (:gen-class))

(def origin '(0 0))

(defn add_to_point
  "add an origin to list of points"
  [o points]
  (let [add (fn [o p] `(~(+ (first o) (first p)) ~(+ (second o) (second p))))]
    (map (partial add o) points)))

(defn base
  "return list of points moving up from origin"
  [len]
  (for [y (range (+ 1 len))]
          `(~0 ~y)))

(defn negy
  [p]
  (let [[x y] p]
    `(~x ~(- y))))

(defn transpose
  [p]
  (let [[x y] p]
    `(~y ~x)))

(defn up
  [o len]
  (->> len base (add_to_point o)))

(defn right
  [o len]
  (->> len base (#(map transpose %)) (add_to_point o)))

(defn down
  [o len]
  (->> len base (#(map negy %)) (add_to_point o)))

(defn left
  [o len]
  (->> len base (#(map negy %)) (#(map transpose %)) (add_to_point o)))

(comment
  (up '(1 1) 5)
  (down '(-2 -2) 5)
  (right '(2 2) 5)
  (left '(2 2) 5)
  (transpose '(1 2))
  (map transpose '((1 2) (3 4)))
  (negy '(1 2))
  (add_to_point '(1 2)
                '((1 2) (3 4))))


(defn points_from_move
  [loc move]
  (let [dir (.charAt move 0)
        len (-> move (subs 1) Integer/parseInt)]
    (case dir
           \U (up loc len)
           \D (down loc len)
           \R (right loc len)
           \L (left loc len))))

(defn wire_points
  [origin moves]
  (let [move (first moves)]
    (if (empty? move)
      nil
      (let [new_moves (points_from_move origin move)]
        (concat new_moves (wire_points (last new_moves) (rest moves)))))))

(defn solve
[]
  (let [wire1 (-> "input1" slurp (.split ",") vec)
        wire2 (-> "input2" slurp (.split ",") vec)]
    (disj (clojure.set/intersection (set (wire_points origin wire1)) (set (wire_points origin wire2))) '(0 0))))

(defn dist
  [p]
  (let [[x y] p]
    (+ (Math/abs x) (Math/abs y))))

(comment
  (concat (points_from_move '(2 20) "D5") (points_from_move '(0 0) "U3"))
  (set (wire_points origin (-> "input_test1" slurp (.split ",") vec)))
  (clojure.set/intersection (set (wire_points '(0 0) ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]))
                            (set (wire_points '(0 1) ["U61" "R66","U55","R34","D71","R55","D58","R83"])))
  (apply min (map dist (solve))))

(defn -main
  "find crossings"
  [& args]
  (let [wire1 (-> "input1" slurp (.split ",") vec)
        wire2 (-> "input2" slurp (.split ",") vec)]
    '(wire1 wire2)))


(ns advent02.core
  (:gen-class))

(defn parse
 "split along commas and return vector of ints"
[arg]
(-> arg (.split ",") (#(map read-string %)) vec))

(defn operation
  "do the opcode at pos n"
  [ops n]
  (let [op (get ops n)
        v1 (get ops (get ops (+ n 1)))
        v2 (get ops (get ops (+ n 2)))
        p3 (+ n 3)]
    (case op
      99 ops
      1 (operation (assoc ops (get ops p3) (+ v1 v2)) (+ 4 n))
      2 (operation (assoc ops (get ops p3) (* v1 v2)) (+ 4 n))
      )))

(defn part2
 "First result of operation"
 [ops noun verb]
 (first (operation (assoc ops 1 noun 2 verb) 0)))

(defn -main
  "Advent 2019 problem 2"
  [& args]
  (let [noun (Integer/valueOf (first args))
        verb (Integer/valueOf (second args))]
  (-> "input" slurp parse (part2 noun verb) print)))

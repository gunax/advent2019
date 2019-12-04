(ns advent02.core-test
  (:require [clojure.test :refer :all]
            [advent02.core :refer :all]))

(deftest test-1
  (testing "[1 0 0 0 99]"
  (is (= [2 0 0 0 99] (operation [1 0 0 0 99] 0)))))

  (deftest test-2
  (testing "[2 3 0 3 99]"
  (is (= [2 3 0 6 99] (operation [2 3 0 3 99] 0)))))

  (deftest test-3
  (testing "[2 4 4 5 99 0]"
  (is (= [2 4 4 5 99 9801] (operation [2 4 4 5 99 0] 0)))))

  (deftest test-4
  (testing "[1 1 1 4 99 5 6 0 99]"
  (is (= [30 1 1 4 2 5 6 0 99] (operation [1 1 1 4 99 5 6 0 99] 0)))))

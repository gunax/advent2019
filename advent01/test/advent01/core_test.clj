(ns advent01.core-test
  (:require [clojure.test :refer :all]
            [advent01.core :refer :all]))

(deftest test-14
  (testing "mass of 14"
    (is (= (fuel 14) 2))))

(deftest test-1969
  (testing "mass of 1969"
    (is (= (fuel 1969) 966))))

(deftest test-100756
  (testing "mass of 100756"
    (is (= (fuel 100756) 50346))))

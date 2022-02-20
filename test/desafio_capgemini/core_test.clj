(ns desafio-capgemini.core-test
  (:require [clojure.test :refer :all]
            [desafio-capgemini.core :refer :all]))

(deftest teste-escada
  (is (= "     *\n    **\n   ***\n  ****\n *****\n******\n"
         (escada 6)))
  (is (= "  *\n **\n***\n"
         (escada 3))))

(deftest teste-segura
  (is (= 3 (segura "Ya3")))
  (is (= 0 (segura "stoheueeu"))))

(deftest teste-anagrama
  (is (= 2 (anagrama "ovo")))
  (is (= 3 (anagrama "ifailuhkqq"))))

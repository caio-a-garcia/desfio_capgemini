(ns desafio-capgemini.core
  (:require [clojure.string :refer [join split]]))

(defn escada
  "Desafio 1. Recebe um argumento n inteiro e retorna uma escada com n degraus no caractere *"
  [n]
  (loop [degraus 1 espacos (- n 1) escada ""]
    (let [escada (str escada
                      (join (repeat espacos " "))
                      (join (repeat degraus "*"))
                      "\n")]
      (if (zero? espacos)
        escada
        (recur
         (inc degraus)
         (dec espacos)
         escada)))))

(defn mostrar-escada
  [n]
  (println (escada n)))

(defn segura
  "Desafio 2. Recebe uma string e retorna quantos caracteres devem ser adicionados para que ela tenha ao menos 6 caracteres."
  [senha]
  (let [diferenca (- 6 (count senha))]
    (if (> diferenca 0)
      diferenca
      0)))

(defn equivalente?
  "Checa se duas strings cont?m os mesmos characteres, o mesmo n?mero de vezes cada, em qualquer ordem."
  [substring teste]
  (= (->> (split substring #"")
          (sort))
     (->> (split teste #"")
          (sort))))

(defn anagrama
  "Desafio 3. Checa cada substring do argumento passado contra todas as outras. Usa o predicado equivalente? para conferir se dois argumentos contituem um anagrama como descrito no desafio."
  [palavra]
  (loop [total 0
         tamanho 1]
    (if (= (count palavra) (- tamanho 1))
         total
         (recur
          (+ total
             (loop [counter 0
                    substring (subs palavra 0 tamanho)
                    total 0]
               (if (= (count palavra) (- (+ tamanho counter) 1))
                 total
                 (recur
                  (inc counter)
                  (subs palavra counter (+ counter tamanho))
                  (+ total
                     (loop [inicio counter
                            counter 1
                            total 0]
                       (if (= (count palavra) (- (+ inicio tamanho counter) 1))
                         total
                         (let [teste (subs palavra
                                           (+ inicio counter)
                                           (+ inicio tamanho counter))]
                           (if (equivalente? substring teste)
                             (recur
                              inicio
                              (inc counter)
                              (inc total))
                             (recur
                              inicio
                              (inc counter)
                              total))))))))))
         (inc tamanho)))))

(defn -main [function argument]
  #_(if (not (nil? (some #{function} '("escada" "segura" "anagrama")))))
  (escada 9))

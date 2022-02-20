# desafio_capgemini

Implementa��o de solu��es para o desafio da Academia Capgemini na linguagem Clojure.

## Como rodar

Se voc� ainda n�o tem um ambiente de desenvolvimento Clojure, instale o Leiningen seguindo as [instru��es](https://leiningen.org) no site oficial.

Com o ambiente pronto acesse o REPL. Para fazer isso com o Leiningen, v� � linha de comando e d� o comando `lein repl`.

Copie o c�digo abaixo, cole no repl e execute.

'''
(defn escada
  "Desafio 1. Recebe um argumento n inteiro e retorna uma escada com n degraus no caractere *"
  [n]
  (loop [degraus 1 espacos (- n 1)]
    (println (clojure.string/join (repeat espacos " "))
             (clojure.string/join (repeat degraus "*")))
    (if (zero? espacos)
      nil
      (recur
       (inc degraus)
       (dec espacos)))))

(defn segura
  "Desafio 2. Recebe uma string e retorna quantos caracteres devem ser adicionados para que ela tenha ao menos 6 caracteres."
  [senha]
  (let [diferenca (- 6 (count senha))]
    (if (> diferenca 0)
      diferenca
      0)))

(defn equivalente?
  "Checa se duas strings cont�m os mesmos characteres, o mesmo n�mero de vezes cada, em qualquer ordem."
  [substring teste]
  (= (->> (clojure.string/split substring #"")
          (sort))
     (->> (clojure.string/split teste #"")
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
'''

Com isso o ambiente ter� tres fun��es definidas que resolvem os tres desafios descritos.

Para o primeiro desafio, digite no REPL '(escada <n>)', substituindo <n> por um numero inteiro a sua escolha (funciona para n�meros menores que a largura da janela do ambiente de execu��o em caracteres).

Para o segundo desafio, use a fun��o '(segura <string>)', substituindo <string> por qualquer sequencia de caracteres "entre aspas". A fun��o checa apenas o tamanho da string como ilustrado nos exemplos do desafio. Checagem das outras regras de seguran�a discutidas ficaram para desenvolvimento futuro.

Para o terceiro desafio use a fun��o '(anagrama <string>)' substituindo <string> da mesma forma que no desafio anterior.

## Proximos passos

Pendente implementar testes unit�rios e uma forma de compilar o projeto como um todo.

Extra seria complementar as fun��es dos desafios. Por exemplo, conferir as outras condi��es para uma senha segura.

## License

Copyright © 2022

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

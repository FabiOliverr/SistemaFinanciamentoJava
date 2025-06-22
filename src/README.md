# 🏠 Meu Projeto de Financiamentos Imobiliários

Este é o meu projeto de sistema de simulação de financiamentos imobiliários, feito em Java para a disciplina de Programação Orientada a Objetos. Confesso que algumas coisas ainda tenho duvidas, porém, agradeço as mentorias, aulas ao vivo e suport que me foi dado por colegas de classe!.

---

## 🎯 O que eu aprendi e fiz em cada etapa.(Fiz um resumão!)

* **Semana 1-4**:
    * Comecei criando as classes **`Financiamento`** e **`InterfaceUsuario`**.
    * Aprendi a definir **atributos**, fazer **construtores** e os métodos básicos pra calcular as parcelas e o valor total.
    * Organizei tudo em **pacotes** (`modelo`, `util`, `main`) e usei os **modificadores de acesso** (`private` e `public`) pra deixar o código mais seguro.
    * Coloquei os **getters** em tudo e adicionei umas validações nas entradas pra ninguém digitar coisa errada.

* **Semana 5 (Herança e Polimorfismo na Prática)**:
    *  Criei as classes **`Casa`**, **`Apartamento`** e **`Terreno`**, que "herdam" de `Financiamento`.
    * Isso me permitiu usar o **polimorfismo** pra calcular as parcelas de jeitos diferentes pra cada tipo de imóvel (tipo, a casa tem um seguro fixo, o apartamento usa a tabela PRICE, e o terreno tem um acréscimo) Alguns momentos fiquei travado por estar confuso..

* **Semana 6 (Classes Abstratas e Detalhes Específicos)**:
    * Transformei a classe **`Financiamento` em abstrata**, o que me obrigou a implementar os cálculos e a exibição em cada tipo de financiamento ( Essa parte eu sofri bastante, pois algumas modificações deram errado e eu tive que procurar o problema e as vezes eu ficava HORAS procurando o problema e era somente uma "," :D).
    * Adicionei uns **atributos específicos** pra cada um: área e tamanho do terreno pra casa, vagas e andar pro apartamento, e tipo de zona pro terreno. Ficou bem mais completo!

* **Semana 7 (Tratando Erros como um Profissional)**:
    *  Usei blocos **`try-catch`** nos métodos de entrada (`InterfaceUsuario`) pra não bugar se o usuário digitar texto em vez de número.
    *  E a parte mais legal: criei uma **exceção personalizada**, a **`AumentoMaiorDoQueJurosException`**. Se o acréscimo de R$ 80,00 na casa for muito alto comparado aos juros, o programa avisa com essa exceção, mostrando que entendi a regra de negócio!

---

## ⚠️ Atenção! (Dica importante para os testes)

Na hora de digitar valores decimais, tipo a taxa de juros (ex: 8% viraria `0,08`), meu programa está configurado pra usar a **vírgula (`,`) como separador decimal**.

Então, se você digitar `0.10` (ponto), **não vai funcionar**, ele vai dar erro de entrada! Mas se você usar `0,10` (vírgula), aí sim ele aceita de boa! Isso acontece por causa da configuração de "localidade" do ambiente de execução. 😉

OBS - Coloquei pois eu apaguei meu codigo e voltei pensando que estava errado, mas, eu só não tinha me atentado que aqui no Brasil usamos a vírgula e não o ponto.
---

## 👨‍💻 Autor

Fábio Augusto de Oliveira Filho - [https://github.com/FabiOliverr](https://github.com/FabiOliverr)
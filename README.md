# Como usar:
Existem duas formas de executar o projeto:
* Executar o arquivo .jar que está no projeto através do java, utilizando o comando "java -jar classificadorhumor-0.0.1-SNAPSHOT.jar".
* Abrindo o projeto em uma IDE e dar start na classe "ClassificadorHumorApplication"

# Endpoints:

**Tickets:**
* Listar todos os Tickets:
GET http://localhost:8080/api/tickets

* Pesquisar a partir de uma data:
GET http://localhost:8080/api/tickets/startDate=ddMMyyyy

* Pesquisar até uma data:
GET http://localhost:8080/api/tickets/endDate=ddMMyyyy

* Pesquisar entre datas:
GET http://localhost:8080/api/tickets/startDate=ddMMyyyy&endDate=ddMMyyyy

* Pesquisar uma determinada prioridade:
GET http://localhost:8080/api/tickets/priority={string}

* Pesquisar uma determinada página (Inicia na página 0):
GET http://localhost:8080/api/tickets/page={int}

* Adicionar um limite a quantidade de registros exibidos na pesquisa (Padrão 10):
GET http://localhost:8080/api/tickets/quantity={int}

# Observações:
* Poderia ser utilizado machine learning para identificar as palavras mais utilizadas para determinar prioridade, porém acredito que não daria tempo de treinar a IA para ter uma boa acertividade.
* Na questão de quantos dias eu iria considerar um ticket com prioridade alta, poderia ter realizado um método para identificar um prazo médio de resposta, porém acredito que pensando em produto isso não seria uma boa prática, uma vez que quanto mais tempo demora a resposta, maior a média e isso não significa que estar dentro da média é uma coisa boa. Estipulei então acima de 30 dias como um motivo de ser um ticket com prioridade alta.

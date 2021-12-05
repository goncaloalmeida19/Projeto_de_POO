# Projeto_de_POO
Projeto realizado no âmbito da displicina de Programação Orientada aos Objetos (LEI 2º ano)

Enunciado:

Uma cadeia de supermercados pretende desenvolver uma aplicação para a venda online dos seus produtos.

A aplicação deverá gerir clientes, produtos, promoções e vendas. Os clientes são caracterizados pelo nome, morada, e-mail, telefone e data de nascimento. Os produtos são descritos pelo identificador, nome, preço unitário e stock existente. Existem três categorias de produtos: alimentares, limpeza e mobiliário. Os produtosalimentares são ainda caracterizados pelo nº de calorias/100g, e pela percentagem de gordura. Nos produtos de limpeza há a considerar o grau de toxicidade (escala de 0 a 10) e nos produtos de mobiliário o peso e a dimensão (altura × largura × profundidade).
Para aumentar as vendas a empresa faz promoções temporárias, estando cada promoção associada apenas a um único produto. Existem dois tipos de promoções: a modalidade pague-três-leve-quatro e a modalidade pague-menos. Na primeira modalidade, os clientes pagam três em cada quatro dos itens encomendados. Por exemplo, se o cliente comprar 9 unidades de produtos terá de pagar apenas 7. Na modalidade pague-menos, uma unidade é paga a 100%, decrescendo o custo em 5% por cada unidade adicional, até se atingir um desconto máximo de 50% do valor total da compra. Cada compra pode ter associados vários produtos e em diferentes quantidades. Para os clientes frequentes o transporte ao domicílio é gratuito em compras acima de 40 Euros. Abaixo deste valor o transporte tem um custo de 15 Euros. Para os restantes clientes o custo é fixo tendo o valor de 20 Euros. Os produtos de mobiliário com peso superior a 15 kg têm um custo de transporte de 10 Euros. Este custo é aplicável a todos os clientes. 

A aplicação deverá permitir efetuar as seguintes operações:
  1) Realizar o login.
  2) Realizar uma compra.
  3) Consultar as compras realizadas.
	
A interação com o utilizador deverá ser realizada através da consola.

A aplicação deverá ser disponibilizada com ficheiros de texto contendo dados relativos a clientes regulares, clientes frequentes, produtos e promoções, (pelo menos 5 itens de cada categoria). A estrutura dos ficheiros de texto deverá ser definida de modo a permitir a fácil e rápida edição dos ficheiros, mas também simplificar o seu parsing pela aplicação. Após o primeiro arranque da aplicação, todos os dados devem ser guardados em ficheiros de objetos e carregados sempre que a aplicação for iniciada.

Para simplificar o teste da aplicação deverá ser possível alterar a data corrente.

O login deverá ser efetuado apenas através do endereço de e-mail.

Implementação

A aplicação deverá ser implementada na linguagem Java e ter em conta os seguintes aspetos:
	1. Elaboração de um diagrama de classes (UML) antes de iniciar a implementação, para prever a estrutura do projeto.
	2. Cada classe deverá gerir internamente os seus dados, pelo que deverá cuidar da proteção das suas variáveis e métodos.
	3. Cada classe deverá ser responsável por uma tarefa ou objetivo específico, não lhe devendo ser atribuídas funções indevidas.
	4. Utilize a keyword static apenas quando tal se justifique e não para contornar erros do compilador.
	5. Comente as classes e os métodos públicos segundo o formato Javadoc. Isto permitirá gerar automaticamente uma estrutura de ficheiros HTML. Comente o restante código sempre que a leitura dos algoritmos não seja óbvia.
	6. Evite o uso abusivo de variáveis e métodos public.
	7. Não são aceites soluções que utilizem instanceof e class.getName().
	8. Na escolha de nomes para variáveis, classes e métodos, devem ser seguidas as convenções adotadas na linguagem Java.
	9. Na organização das classes deverá ser evitada a redundância do código.
	
Entrega

O projeto deve ser realizado em grupos de dois estudantes da mesma turma prática.

Devem ser entregues os seguintes ficheiros (sem compressão):
• Diagrama de classes em UML (pdf).
• Todas as classes .java.
• Ficheiros de dados para teste.
• Javadoc (HTML).
• Relatório (pdf).

Avaliação do trabalho

Para a avaliação do trabalho são considerados fatores de dois tipos:
	• Caixa preta (tal como é percecionado pelo utilizador):
		o Conjunto de funcionalidades implementadas.
		o Robustez do programa.
		o Qualidade da interface.
	• Caixa branca (a forma como está construído):
		o Qualidade das soluções técnicas encontradas para os problemas.
		o Estruturação do código.
		o Qualidade dos comentários.
	

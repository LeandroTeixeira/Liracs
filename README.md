# Liracs
## Resumo
Repositório referente ao software desenvolvido pelos estudantes Leandro Luiz Duarte Teixeira, Laura Galvão Coelho, Ian Carlos Afonso da Silva e Rafael Martins Silva como TCC para o curso de Informática na instituição CEFET-MG no ano de 2015. Trata-se de um software desenvolvido em Java para executar instruções no computador a partir de comandos de voz.

O objetivo do software era, acima de tudo, desenvolver uma ferramenta de acessibilidade para pessoas com mobilidade reduzida que poderiam ter dificuldade de usar o computador. O nome do projeto deriva de um jogo de palavras envolvendo as iniciais dos integrantes.

## Tecnologias Utilizadas:
- Java
- Bancos de dados implementados em Postgres
- Conceitos de MVC, incluindo mas não se limitando a Stubs, Skeletons e DAOs
- Interface gráfica desenvolvida com as ferramentas nativas de Java
- Gerenciamento de usuários via banco de dados
- Manipulação de áudio usando o pacote javax.sound

## [Demonstração de Utilização](https://www.youtube.com/watch?v=ENGiv5iqogE)
![Main Page](/Image.png "Main Page")

Narrado por Ian Carlos Afonso da Silva

## Utilização do Programa
Existem três projetos incluídos neste repositório. Liracs Shared Resources é composto das classes utilizadas por ambos Liracs Controller e Liracs. O projeto Liracs é referente à aplicação principal. Liracs Controller é o banco de dados. Na versão atual, para executar o software é necessário executar o Liracs Controller para inicializar o servidor e então executar o Liracs e utilizá-lo normalmente.

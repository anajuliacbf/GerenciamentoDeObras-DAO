# Modelagem de Objetos para Banco de Dados

## Exercício 2: Sistema de Gerenciamento de Obras <br/>
Contexto: Uma empresa de construção precisa gerenciar seus projetos de obras. Para cada obra, há um projeto associado, que envolve diversos engenheiros, operários, equipamentos e materiais. A empresa deseja controlar quais engenheiros e operários estão alocados em cada obra, quais equipamentos estão sendo utilizados e quais materiais foram consumidos.

Tarefas:
1. Modele as classes **Projeto**, **Engenheiro**, **Operario**, **Equipamento** e **Material**, respeitando as seguintes relações:
    - _Um projeto pode ter vários engenheiros e operários alocados.
    - _Um engenheiro e um operário podem participar de vários projetos.
    - _Um projeto pode utilizar vários equipamentos, e um equipamento pode ser utilizado em vários projetos.
    - _Um projeto pode consumir vários materiais, e um material pode ser utilizado em vários projetos.
2. Implemente o padrão DAO para as classes **ProjetoDAO**, **EngenheiroDAO**, **OperarioDAO**, **EquipamentoDAO** e **MaterialDAO**, utilizando a API JDBC para realizar as operações de CRUD no banco de dados.
3. Crie uma classe **ConexaoBD** que utiliza o padrão Singleton para gerenciar a conexão com o banco de dados.
4. Implemente métodos nas classes DAO para:
- Inserir, atualizar, excluir e listar projetos.
- Inserir, atualizar, excluir e listar engenheiros e operários.
- Inserir, atualizar, excluir e listar equipamentos e materiais.
- Listar todos os engenheiros e operários alocados em um determinado projeto.
- Listar todos os equipamentos e materiais utilizados em um determinado projeto.

**Objetivo:** Os alunos devem aplicar conceitos de modelagem de relações muitos para muitos (N) entre entidades, reforçando o uso do padrão DAO para diferentes classes e a implementação do padrão Singleton para gerenciar conexões com o banco de dados.

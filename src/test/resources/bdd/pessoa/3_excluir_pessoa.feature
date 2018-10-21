# language: pt

Funcionalidade: Excluir pessoa
    Eu como administrador
    Desejo excluir uma pessoa existente
    A fim de eliminar a mesma do sistema

    Esquema do Cenário: Garantir que uma pessoa existente possa ser excluida
        Dado o identificador da pessoa <identificador>
        Quando tento realizar a exclusão
        Então <resultado>
        Exemplos:
            | identificador     |                   resultado                           |
            |       1           |   Operação realizada com sucesso                      |
            |       12          |   Não existe pessoa com o identificador fornecido     |
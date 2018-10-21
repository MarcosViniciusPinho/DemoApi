# language: pt

Funcionalidade: Editar pessoa
    Eu como usuario
    Desejo gerenciar minhas informações
    A fim de obter minhas informações atualizadas

    Esquema do Cenário: Garantir que uma pessoa existente possa alterar suas informações
        Dado o identificador da pessoa <identificador>
        E uma pessoa com o nome <nome>
        E com a idade <idade>
        Quando tento realizar a alteração
        Então <resultado>

        Exemplos:
            | identificador     |     nome          |   idade     |                         resultado                           |
            |       1           |                   |     0       |       Informações obrigatórias ainda não foram informadas   |
            |       1           |     Marcos        |     0       |       Informações obrigatórias ainda não foram informadas   |
            |       1           |                   |    32       |       Informações obrigatórias ainda não foram informadas   |
            |       9           |                   |             |       Não existe pessoa com o identificador fornecido       |
            |       1           |     Marcos        |    32       |       Operação realizada com sucesso                        |

# language: pt
# CRUD de Pessoa
# Atores: Administrador e Usuario

Funcionalidade: Manter pessoa
    Eu como administrador ou usuario
    Desejo gerenciar as informações de pessoas
    A fim de obter suas informações atualizadas

    Esquema do Cenário: Garantir que uma nova pessoa possa ser cadastrada no sistema
        Dado uma pessoa com o nome <nome>
        E com a idade <idade>
        Quando tento realizar o cadastro
        Então <resultado>

        Exemplos:
            |     nome          |   idade     |                         resultado                           |
            |                   |     0       |       Informações obrigatórias ainda não foram informadas   |
            |     Marcos        |     0       |       Informações obrigatórias ainda não foram informadas   |
            |                   |    32       |       Informações obrigatórias ainda não foram informadas   |
            |     Marcos        |    32       |       Operação realizada com sucesso                        |


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


    Esquema do Cenário: Garantir que uma pessoa existente possa ser excluida
        Dado o identificador da pessoa <identificador>
        Quando tento realizar a exclusão
        Então <resultado>
        Exemplos:
            | identificador     |                   resultado                           |
            |       1           |   Operação realizada com sucesso                      |
            |       12          |   Não existe pessoa com o identificador fornecido     |
# language: pt

Funcionalidade: Cadastrar pessoa
    Eu como administrador
    Desejo cadastrar uma nova pessoa
    A fim de obter suas informações no sistema

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
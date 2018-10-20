# language: pt

Funcionalidade: Cadastrar uma nova pessoa
    Eu logado no sistema
    Desejo cadastrar uma nova pessoa
    A fim de obter suas informações para futuras tomadas de decisão

    Esquema do Cenário: Garantir que uma nova pessoa possa ser cadastrada no sistema
        Dado uma pessoa com o nome <nome>
        E com a idade <idade>
        Quando tento cadastrá-la
        Então <resultado>

        Exemplos: 
            |     nome          |   idade     |                         resultado                           |
            |                   |     0       |       Informações obrigatórias ainda não foram informadas   |
            |     Marcos        |     0       |       Informações obrigatórias ainda não foram informadas   |
            |                   |    32       |       Informações obrigatórias ainda não foram informadas   |
            |     Marcos        |    32       |       Pessoa cadastrada com sucesso                         |

    
    
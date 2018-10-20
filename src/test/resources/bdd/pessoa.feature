# language: pt

Funcionalidade: Cadastrar uma nova pessoa
    Eu logado no sistema
    Desejo cadastrar uma nova pessoa
    A fim de obter suas informações para futuras tomadas de decisão
     
    Esquema do Cenário: Garantir que uma nova pessoa só possa ser cadastrada caso seu nome seja informado
        Dado uma pessoa com o nome <nome>
        Quando tento cadastrá-la
        Então <resultado>
            
        Exemplos: 
            |     nome          |                resultado                  |
            |     ""            |    Falha: Nome da pessoa é obrigatória    |
            |     null          |    Falha: Nome da pessoa é obrigatória    |
            |     Marcos        |    Pessoa cadastrada com sucesso          |

    
    
# challenge-conversor-de-moedas

## 📋 Índice
- [📢 Descrição](#-Descrição)
- [📌 Principais Características](#-Principais-Características)
- [⚙️ Como executar o projeto](#-Como-executar-o-projeto)
- [💻 Preview](#-Preview)
- [🎖️ Badge](#-🎖️Badge)

## 📢 Descrição
Este projeto é minha solução para o desafio da formação **Java e Orientação a Objetos G7 - ONE** do programa ONE - Oracle Next Education! O objetivo é criar um conversor de moedas que oferece múltiplas opções de conversão, utilizando taxas de câmbio atualizadas por meio de uma API externa, onde os usuários podem escolher entre as 161 opções de moedas e realizar suas conversões de forma rápida.

## 📌 Principais Características:
- **Interação via Console**: O programa apresenta um menu com opções simples e interativas, permitindo que o usuário realize suas conversões de maneira simples pelo console.
- **Múltiplas Opções de Conversão**: Além da conversão entre as principais moedas disponíveis no menu inicial, é possível escolher entre 161 moedas disponibilizadas pela API.
- **Uso de API Externa**: O programa se conecta à [Exchange Rate API](https://www.exchangerate-api.com//) para pegar as taxas de câmbio atualizadas.
- **Histórico de conversões**: O usuário pode visualizar seu histórico com a data e horário de cada conversão realizada.

## ⚙️ Como executar o projeto

1. Clone o repositório

    ```bash
    git clone https://github.com/pablwoAraujo/challenge-conversor-de-moedas
    ```

2. Crie uma conta e obtenha a chave de acesso da API [Exchange Rate API](https://www.exchangerate-api.com//)

3. Configure a chave de acesso: Você pode criar uma variável de ambiente chamada **API_KEY** ou substituir o valor do atributo **APIKEY** na classe **HttpService**.

4. Abra o projeto em sua IDE preferida e execute o método **main** na classe **Main**

## 💻 Preview
Demonstração da aplicação |  
:-------------------------:|
![](./demo.gif) |


## 🎖️Badge
Insígnia do desafio |  
:-------------------------:|
![badge](./Badge-Conversor.png) |
Insígnia de conclusão do desafio.|

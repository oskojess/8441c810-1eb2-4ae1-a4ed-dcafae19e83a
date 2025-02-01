# Serviço de Autorização de Cartões 

## Contexto 
Serviço de autorização de transações responsável por validar e processar transações realizadas com cartão de crédito e benefícios. 

## Requisitos
* JDK 17
* Docker
* Ferramenta para visualização dos dados na base de dados

## Base de dados local
Acessar o diretório `docker` por linha de comando e em seguida: `docker-compose up -d` para subir o container da base de dados.

## CURL
```
curl --request POST \
  --url http://localhost:7000/transactions/v1/authorize \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.3.0' \
  --data '{
	"transactionId": "173",
	"accountId": "01JJZT7FHTK3JAZNDFZWTQM7P2",
	"totalAmount": 100.00,
	"mcc": "5812",
	"merchant": "PADARIA DO ZE               SAO PAULO BR"
}'
```
<hr> 
## HDL - Simplificada

(imagem)

## Modelagem de dados

(imagem)

# L4 - Arquitetura de solução solicitada 

- Transações simultâneas: dado que o mesmo cartão de crédito pode ser utilizado em diferentes serviços online, existe uma pequena mas existente probabilidade de ocorrerem duas transações ao mesmo tempo. O que você faria para garantir que apenas uma transação por conta fosse processada em um determinado momento? 

Esteja ciente do fato de que todas as solicitações de transação são síncronas e devem ser processadas rapidamente (menos de 100 ms), ou a transação atingirá o timeout.

## Sugestões de soluções:

# Componentes:
- Cliente(envia a transação);
- Serviço de autorização;
- Mensageria(Apache Kafka, AWS ...);
- Ferramenta de cache (Redis ou outra) para bloqueio distribuído;
- Banco de dados;

## Mensageria:

Processar as transações em ordem FIFO ao consumir mensagens de um único tópico com uma única partição. Garantindo que as mensagens sejam processadas na ordem em que foram enviadas. 

# Rest: 
Mecanismo de bloqueio distribuído, otimização de consultas SQL, garantir a consistências dos dados para reduzir o tempo de bloqueio de recursos, utilização de cache para melhorar o tempo de respostas. 


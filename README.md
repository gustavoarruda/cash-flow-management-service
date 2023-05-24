# Serviço de Lançamentos de Fluxo de Caixa

Serviço de lançamentos de fluxo de caixa com a finalidade de estudos.

O projeto utiliza um serviço responsavel por receber as requisições de debito e credito via api rest e salvar os dados
no banco de dado e enviar dados para o kafka atuando como producer, para que outro serviço (consolidate-data-provider) 
gere os saldo atuando como consumer.

## Desenho de Arquiteura de Solução

### Fluxo de Lançamentos:


### Fluxo de Relatório Consolidado:


### Como inciar o serviço:

```
docker-compose up -d
```
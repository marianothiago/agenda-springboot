# Testando a API com JWT

## 1. Primeiro, faça login para obter o token:

```bash
curl -X POST http://localhost:8080/agenda/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@unifan.br",
    "senha": "passwd"
  }'
```

**Resposta esperada:**
```json
{
  "tokenJWT": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "admin@unifan.br"
}
```

## 2. Teste sem token (deve retornar 401):

```bash
curl -X POST http://localhost:8080/agenda/contato \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "telefone": "11999999999"
  }'
```

**Resposta esperada:** Status 401 com mensagem de erro.

## 3. Teste com token válido (deve retornar 201):

```bash
curl -X POST http://localhost:8080/agenda/contato \
  -H "Content-Type: application/json" \
  -H "Authorization: SEU_TOKEN_AQUI" \
  -d '{
    "nome": "João Silva", 
    "email": "joao@email.com",
    "telefone": "11999999999"
  }'
```

**Resposta esperada:** Status 201 com mensagem de sucesso.

## 4. Teste com token inválido (deve retornar 401):

```bash
curl -X POST http://localhost:8080/agenda/contato \
  -H "Content-Type: application/json" \
  -H "Authorization: token_invalido" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com", 
    "telefone": "11999999999"
  }'
```

**Resposta esperada:** Status 401 com mensagem de token inválido.

## Observações:

- Substitua `SEU_TOKEN_AQUI` pelo token retornado no passo 1
- O token expira em 60 minutos por padrão
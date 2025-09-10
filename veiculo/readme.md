Sequência de criação - Java:
✅ - Entity (Cria as tabelas, relacionamentos, anotações, estrutura, relacionamentos);
✅ - DTO (camada que recebe dados do controller e trata);
✅ - mapper (camada de mapeamento, converte DTO em Entity e Entity em DTO);
✅ - repository (camada que persiste os dados, conversa com o banco);
✅ - service (recebe dado do controller, via dto, trata, chama regra de negócio, validação);
✅ - controller (API, gerencia interface com o usuário através do request, response, via JSon);
✅ - util (cria validação, cria regra de negócio, métodos).  

Convenção:
Constante - MAIÚSCULA
Variavel - minúscula
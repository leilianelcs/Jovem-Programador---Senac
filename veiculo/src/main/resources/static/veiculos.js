const criarTabelaVeiculo = function(dados) {
    const tabela = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");

    // Cabeçalho principal
    const trTitle = document.createElement("tr");
    const thTitle = document.createElement("th");
    thTitle.textContent = "Veículos";
    thTitle.colSpan = 11;
    trTitle.appendChild(thTitle);
    thead.appendChild(trTitle);

   
    // Cabeçalho das colunas placa cor valor ano descricao dataCadastro modelo
    const cabecalho = ["ID", "Placa", "Cor", "Valor", "Ano", "Descrição", "Data Cadastro", "Modelo", "Fabricante", "País de Origem", "Ação"];
    const trCabecalho = document.createElement("tr");
    cabecalho.forEach(function(campo) {
        const th = document.createElement("th");
        th.textContent = campo;
        trCabecalho.appendChild(th);
    });
    thead.appendChild(trCabecalho);

    // Estilização da tabela
    tabela.classList.add("tabela-dados");
    tabela.appendChild(thead);

    // Corpo da tabela
    dados.forEach(function(item) {
        const tr = document.createElement("tr");
       
        // ID
        const tdId = document.createElement("td");
        tdId.textContent = item.id;
        tr.appendChild(tdId);

        // Placa
        const tdPlaca = document.createElement("td");
        tdPlaca.textContent = item.placa;
        tr.appendChild(tdPlaca);

       // Cor
        const tdCor = document.createElement("td");
        tdCor.textContent = item.cor;
        tr.appendChild(tdCor);

       // Valor
       const tdValor = document.createElement("td");
        tdValor.textContent = item.valor;
        tr.appendChild(tdValor);

       // Ano
       const tdAno = document.createElement("td");
       tdAno.textContent = item.ano;
       tr.appendChild(tdAno);

       // Descrição
       const tdDescricao = document.createElement("td");
       tdDescricao.textContent = item.descricao;
       tr.appendChild(tdDescricao);

       // Data Cadastro
        const tdDataCadastro = document.createElement("td");
        tdDataCadastro.textContent = item.dataCadastro;
        tr.appendChild(tdDataCadastro);

        // Modelo
        const tdModelo = document.createElement("td");
        tdModelo.textContent = item.modelo.nome;
        tr.appendChild(tdModelo);

        // Fabricante
        const tdFabricante = document.createElement("td");
        tdFabricante.textContent = item.modelo.fabricante.nome;
        tr.appendChild(tdFabricante);

        // País de origem
        const tdPaisOrigem = document.createElement("td");
        tdPaisOrigem.textContent = item.modelo.fabricante.paisOrigem;
        tr.appendChild(tdPaisOrigem);

        // Ícones
        const deletar = document.createElement("td");
        deletar.innerHTML = '<button class="btn delete">Deletar</button>';
        deletar.addEventListener("click", async function () {
            const confirmacao = confirm(`Tem certeza que deseja deletar o veículo com ID ${item.id}?`);
            if (!confirmacao) return;
        
            try {
                const resposta = await fetch(`http://localhost:8080/api/veiculos/${item.id}`, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"                       
                    }
                });
        
                if (resposta.ok) {
                    tr.remove(); 
                    alert(`Veículo com ID ${item.id} deletado com sucesso.`);
                } else {
                    const erro = await resposta.json();
                    alert(`Erro ao deletar: ${erro.message || resposta.statusText}`);
                }
            } catch (erro) {
                alert(`Erro de conexão: ${erro.message}`);
            }
        });        
   
       tr.appendChild(deletar);      


        tbody.appendChild(tr);
    });

    tabela.appendChild(tbody);
    return tabela;
};

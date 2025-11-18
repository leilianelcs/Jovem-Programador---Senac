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
        tdPlaca.textContent = item.placa || "-";
        tr.appendChild(tdPlaca);

       // Cor
        const tdCor = document.createElement("td");
        tdCor.textContent = item.cor || "-";
        tr.appendChild(tdCor);

       // Valor
       const tdValor = document.createElement("td");
        tdValor.textContent = item.valor || "-";
        tr.appendChild(tdValor);

       

       // Ano
       const tdAno = document.createElement("td");
       tdAno.textContent = item.ano || "-";
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

        // Ícones - Botão Deletar
        const tdDeletar = document.createElement("td");
        tdDeletar.innerHTML = '<button class="btn delete">Deletar</button>';
        tdDeletar.addEventListener("click", async function () {
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
   
       tr.appendChild(tdDeletar);


        tbody.appendChild(tr);
    });

    tabela.appendChild(tbody);
    return tabela;
};

//Aula 17/11

// const carregarModelosVeiculo = async function(fabricanteId) {
//     const selectFabricante = document.getElementById("modelo-veiculo");
//     setRemoverElementos("#modelo-veiculo option");

//     if (!fabricanteId) {
//         const optionPadrao = document.createElement("option");
//     }

//     //busca e filtra os modelos
//     const dadosModelos = await getData("http://localhost:8080/api/modelos");
//     const modelosFiltrados = dadosModelos.filter(function(modelo){
//         return modelo.fabricante.id == fabricanteId;
//     });

//     //se não existe modelos para o fabricante, congela o select
//     if(modelosFiltrados.length === 0) {
//         const optionSemModelo = document.createElement("option");
//         optionSemModelo.value = "";
//         optionSemModelo.textContent = "Nenhum Modelo Cadastrado";
//         optionSemModelo.selected = ("fabricante");
//     };

//     //adiciona modelos filtrados
//     modelosFiltrados.forEach(function(modelo));

//     //Busca e filtra os modelos
//     const dadosModelos = await getData("http://localhost:8080/api/modelos");

// };

// const carregarFabricantesVeiculo = async function() {

//     // inicializa o select de modelo congelado
//     const optionModeloParado = document.getElementById("fabricante-veiculo");
//     const selectModelo = {}
// };

// const atualizarTabelaVeiculos 

// const validarPlaca = function(placa) {
//     placa = placa.trim().toUpperCase().replace(/-/g, '');
// }

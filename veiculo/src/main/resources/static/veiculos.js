





































const criarTabelaVeiculo = function(dados) {
    const tabela = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");

    // Cria o título da tabela
    const trTitle = document.createElement("tr");
    const thTitle = document.createElement("th");
    thTitle.textContent = "Veículos";
    thTitle.colSpan = 11;
    trTitle.appendChild(thTitle);
    thead.appendChild(trTitle);

   
    // Cabeçalho das colunas da tabela
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
    thead.appendChild(tr); // verificar depois se necessita
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

    //    // Valor
    //    const tdValor = document.createElement("td");
    //     tdValor.textContent = item.valor || "-";
    //     tr.appendChild(tdValor);
       
         // Valor formatado Real Brasileiro
       const tdValor = document.createElement("td");
       tdValor.textContent = formatarPreco(item.valor) || "-";
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

        // Coluna - Botão Deletar
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

//45000 para R$45.000,00
const formatarPreco = function(valor) {
    return new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    }).format(valor);
};



//Aula 17/11


const carregarFabricantesVeiculo = async function() {
    const selectFabricante = document.getElementById("fabricante-veiculo");
    const selectModelo = document.getElementById("modelo-veiculo");

setRemoverElementos("#fabricante-veiculo option");
setRemoverElementos("#modelo-veiculo option");

const dadosFabricantes = await getData("http://localhost:8080/api/fabricantes")

//adiciona opção padrão
const optionPadrao = document.createElement("option");
optionPadrao.value = "";
optionPadrao.textContent = "Selecione um fabricante";
selectFabricante.appendChild(optionPadrao);

//Adiciona os fabricantes
dadosFabricantes.forEach(function(fabricante) {
    const option = document.createElement("option");
    option.value = fabricante.id;
    option.textContent = fabricante.nome;
    selectFabricante.appendChild(option);
});

// inicializa o select de modelo congelado
const optionModeloParado = document.createElement("option");
optionModeloParado.value = "";
optionModeloParado.textContent = "Selecione um fabricante primeiro";
selectModelo.appendChild(optionModeloParado);
selectModelo.disabled = true;   //Congela o select
};


const carregarModelosVeiculo = async function(fabricanteId) {
    const selectModelo = document.getElementById("modelo-veiculo");
    setRemoverElementos("#modelo-veiculo option");

    if (!fabricanteId) {
        const optionPadrao = document.createElement("option");
        optionPadrao.value = "";
        optionPadrao.textContent = "Selecione um fabricante primeiro";
        selectModelo.appendChild(optionPadrao);
        selectModelo.disabled = true;   //Congela o select
        return;
    }

    //busca e filtra os modelos
    const dadosModelos = await getData("http://localhost:8080/api/modelos");
    const modelosFiltrados = dadosModelos.filter(function(modelo){
        return modelo.fabricante.id == fabricanteId;
    });

    //se não existe modelos para o fabricante, congela o select
    if(modelosFiltrados.length === 0) {
        const optionSemModelo = document.createElement("option");
        optionSemModelo.value = "";
        optionSemModelo.textContent = "Nenhum modelo para este fabricante";
       selectModelo.appendChild(optionSemModelo);
       selectModelo.disabled = true //congela o select
       return;
    };

    //Habilita o select e adiciona opção padrão
    selectModelo.disabled = false; //descongela o select
    const optionPadrao = document.createElement("option");
    optionPadrao.value = "";
    optionSemModelo.textContent = "Selecione um modelo";
    selectModelo.appendChild(optionPadrao);
    
      
    //adiciona modelos filtrados
    modelosFiltrados.forEach(function(modelo) {
        const option = document.createElement("option");
        option.value = modelo.id;
        option.textContent = modelo.nome;
        selectModelo.appendChild(option);
    });    

};

const atualizarTabelaVeiculos = async function() {
    setRemoverElementos('.tabela-dados');
    document.querySelector("#veiculos").style.display = "block";
    const dadosVeiculos = await getData("http://localhost:8080/api/veiculos");
    document.querySelector("#veiculos").appendChild(criarTabelaVeiculo(dadosVeiculos));       
}

const validarPlaca = function(placa) {
    placa = placa.trim().toUpperCase().replace(/-/g, '');
    const padraoAntigo = /^[A-Z]{3}[0-9]{4}$/;

    const padraoMercosul = /^[A-Z]{3}[0-9][A-Z][0-9]{2}$/;
    
    if (padraoAntigo.test(placa) || padraoMercosul.test(placa)) {
        return { valido: true, mensagem: "" };
    }
    return {
        valido: false,
        mensagem: "Placa inválida. Use o formato ABC1234 (antigo) ou ABC1D23 (Mercosul)."
    };

}

const validarVeiculo = function(veiculo) {
    const anoAtual = new Date().getFullYear();
    const selectModelo = document.getElementById("modelo-veiculo");
    const selectFabricante = document.getElementById("fabricante-veiculo");

    if (!selectFabricante.value) {
        return {valido: false, mensagem: "Por favor selecione um fabricante." };
    }

    if (selectModelo.disabled) {
        return {valido: false, mensagem: "Não há modelos disponíveis para o fabricante selecionado. Cadastre um modelo primeiro."};
    }

    if (!veiculo.modelo || veiculo.placa.trim() === "") {
        return {valido: false, mensagem: "Por favor selecione um modelo." };
    }

    if (!veiculo.ano || veiculo.ano < 1900 ||  veiculo.ano > anoAtual + 1) {
        return {valido: false, mensagem: `O ano deve estar entre 1900 e ${anoAtual + 1}.`};
}
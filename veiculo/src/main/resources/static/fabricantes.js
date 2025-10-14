const criarTabelaFabricante = function(dados) {
    const tabela = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");

    // Cabeçalho principal
    const trTitle = document.createElement("tr");
    const thTitle = document.createElement("th");
    thTitle.textContent = "Fabricantes";
    thTitle.colSpan = 2;
    trTitle.appendChild(thTitle);
    thead.appendChild(trTitle);

    // Cabeçalho das colunas
    const cabecalho = ["Fabricante", "País de Origem"];
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

        // Fabricante
        const tdFabricante = document.createElement("td");
        tdFabricante.textContent = item.nome;
        tr.appendChild(tdFabricante);

        // País de origem
        const tdPaisOrigem = document.createElement("td");
        tdPaisOrigem.textContent = item.paisOrigem;
        tr.appendChild(tdPaisOrigem);

        tbody.appendChild(tr);
    });

    tabela.appendChild(tbody);
    return tabela;
};

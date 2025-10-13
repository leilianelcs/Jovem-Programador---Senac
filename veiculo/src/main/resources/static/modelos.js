const criarTabelaModelo = function(dados) {
    const tabela = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");

    // cria o cabeçalho da tabela
    const trTitle = document.createElement("tr");
    const th = document.createElement("th");
    th.textContent = "Modelos";
    th.colSpan = 3;
    trTitle.appendChild(th);
    trHead.appendChild(trTitle);

    const cabecalho = ["Modelo", "Fabricante", "País de Origem"];
    const tr = document.createElement("tr");
    cabecalho.forEach(function(campo) {
        const th =document.createElement("th");
        th.textContent = campo;
        tr.appendChild(th);
    });

    //adiciona classe para estilizar a tabela
    tabela.classList.add("tabela-dados");

    thead.appendChild(tr);
    tabela.appendChild(thead);

    //cria o corpo da tabela
    dados.forEach(function(item) {
        const tr = document.createElement("tr");
        //modelo
        const tdModelo = document.createElement("td");
        tdModelo.textContent = item.nome;
        tr.appendChild(tdModelo);

        //nome do fabricante
        const tdFabricante = document.createElement("td");
        tdFabricante.textContent = item.fabricante.nome;
        tr.appendChild(tdFabricante);
        
        //País de origem
        const tdPaisOrigem = document.createElement("td");
        tdPaisOrigem.textContent = item.paisOrigem.nome;
        tr.appendChild(tdPaisOrigem);
        
        tbody.appendChild(tr);
    });
    
    tabela.appendChild(tbody);
        
    return table;

};
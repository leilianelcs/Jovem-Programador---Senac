    // Evento de clique no botão Fabricantes
    document.getElementById("bt-fabricantes").addEventListener("click", async function(event) {
        setShowHide(true, ".minha-section");
        const secaoFabricantes = document.querySelector("#fabricantes");
        secaoFabricantes.style.display = "block";

        // Limpa o conteúdo anterior da seção (exceto o título e parágrafo)
        secaoFabricantes.querySelectorAll("table").forEach(function(tabela) {
             tabela.remove();
        });

        const dadosFabricantes = await getData("http://localhost:8080/api/fabricantes"); 
        secaoFabricantes.appendChild(criarTabela(dadosFabricantes, "Fabricantes", "tabela-dados"));
    });     

    // Evento de clique no botão Modelos
    document.getElementById("bt-modelos").addEventListener("click", async function(event) {
        setShowHide(true, ".minha-section");
        const secaoModelos = document.querySelector("#modelos");
        secaoModelos.style.display = "block";

       // Limpa o conteúdo anterior da seção (exceto o título e parágrafo)
        secaoModelos.querySelectorAll("table").forEach(function(tabela) {
          tabela.remove();
        }); 

        const dadosModelos = await getData("http://localhost:8080/api/modelos");
        secaoModelos.appendChild(criarTabelaModelo(dadosModelos));
       
    });


    //    // Código professor:
    //    // Evento de clique no botão Modelos
    //   document.getElementById("bt-modelos").addEventListener("click", async function(event) {
    //     setShowHide(true, ".minha-section");
    //     setRemoverElementos(".tabela-dados");     
    //     document.querySelector("#modelos").style.display = "block";   
    //     const dadosModelo = await getData("http://localhost:8080/api/modelos");
    //     document.querySelector("#modelos").appendChild(criarTabelaModelo(dadosModelo)); 
    // });



    // Evento de clique no botão veículos
    document.getElementById("bt-veiculos").addEventListener("click", async function(event) {
        setShowHide(true, ".minha-section");
        const secaoVeiculos = document.querySelector("#veiculos");
        secaoVeiculos.style.display = "block";

        // Limpa o conteúdo anterior da seção (exceto o título e parágrafo)
        secaoVeiculos.querySelectorAll("table").forEach(function(tabela) {
             tabela.remove();
        }); 

        const dadosVeiculos = await getData("http://localhost:8080/api/veiculos");
        secaoVeiculos.appendChild(criarTabelaVeiculo(dadosVeiculos));
    });
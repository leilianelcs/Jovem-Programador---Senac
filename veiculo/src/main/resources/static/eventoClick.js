const MODAL = document.getElementById("modal");
const CLOSE_MODAL_BUTTON = document.getElementById("close-modal");

// Evento de clique no botão Fabricantes
document
  .getElementById("bt-fabricantes")
  .addEventListener("click", async function (event) {
    setShowHide(true, ".minha-section");
    const secaoFabricantes = document.querySelector("#fabricantes");
    secaoFabricantes.style.display = "block";

    // Limpa o conteúdo anterior da seção (exceto o título e parágrafo)
    secaoFabricantes.querySelectorAll("table").forEach(function (tabela) {
      tabela.remove();
    });

    const dadosFabricantes = await getData(
      "http://localhost:8080/api/fabricantes"
    );

    if (dadosFabricantes.ok === false) {
      document.querySelector("#fabricantes").innerHTML =
        "<p>Erro ao carregar dados dos fabricantes.</p>";
      document.querySelector("#fabricantes").style.color = "red";
      return;
    }

    secaoFabricantes.appendChild(criarTabelaFabricante(dadosFabricantes));
  });

CLOSE_MODAL_BUTTON.addEventListener("click", function (event) {
  MODAL.style.display = "none";
});

// Evento de click no botão Novo Fabricante
document
  .getElementById("novo-fabricante")
  .addEventListener("click", function (event) {
    setShowHide(true, ".modal-content");    
    MODAL.style.display = "block";
    setShowHide(false, ".modal-content-fabricante");  
  });

  // Evento de clique no botão Enviar - Novo Fabricante
  document.getElementById("form-fabricante").addEventListener("submit", async function (event) {
    event.preventDefault();
  
    const nome = document.getElementById("nome-fabricante").value.trim();
    const paisOrigem = document.getElementById("pais-fabricante").value;
  
    if (!nome || !paisOrigem) {
      alert("Preencha todos os campos.");
      return;
    }
  
    const novoFabricante = { nome, paisOrigem };
  
    try {
      const resposta = await fetch("http://localhost:8080/api/fabricantes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(novoFabricante)
      });
  
      if (resposta.ok) {
        const fabricanteCriado = await resposta.json();
        alert("Fabricante adicionado com sucesso!");
  
        // Fecha o modal
        MODAL.style.display = "none";
        document.getElementById("form-fabricante").reset();
  
        // Atualiza a tabela de fabricantes
        const secaoFabricantes = document.querySelector("#fabricantes");
        secaoFabricantes.querySelectorAll("table").forEach(function (tabela) {
          tabela.remove();
        });
  
        const dadosAtualizados = await getData("http://localhost:8080/api/fabricantes");
        secaoFabricantes.appendChild(criarTabelaFabricante(dadosAtualizados));
      } else {
        const erro = await resposta.json();
        alert(`Erro ao adicionar: ${erro.message || resposta.statusText}`);
      }
    } catch (erro) {
      alert(`Erro de conexão: ${erro.message}`);
    }
  });


// Evento de clique no botão Modelos
document
  .getElementById("bt-modelos")
  .addEventListener("click", async function (event) {
    setShowHide(true, ".minha-section");
    const secaoModelos = document.querySelector("#modelos");
    secaoModelos.style.display = "block";

    // Limpa o conteúdo anterior da seção (exceto o título e parágrafo)
    secaoModelos.querySelectorAll("table").forEach(function (tabela) {
      tabela.remove();
    });

    const dadosModelos = await getData("http://localhost:8080/api/modelos");

    if (dadosModelos.ok === false) {
      document.querySelector("#modelos").innerHTML =
        "<p>Erro ao carregar dados dos modelos.</p>";
      document.querySelector("#modelos").style.color = "red";
      return;
    }

    secaoModelos.appendChild(criarTabelaModelo(dadosModelos));
  });

// Evento de click para novo Modelo
document.getElementById("novo-modelo").addEventListener("click", async function (event) {
   setShowHide(true, ".modal-content");
   //carregar fabricantes no select
   const dadosFabricantes = await getData("http://localhost:8080/api/fabricantes");
   if(dadosFabricantes.status === 404 || dadosFabricantes.error) {
    alert("Erro ao caregar dados dos fabricantes. Erro: " + dadosFabricantes.message);
    return;
   }

   setRemoverElementos("#fabricante-modelo option");

   document.getElementById("fabricante-modelo").appendChild(new Option("Selecione um fabricante", ""));
   dadosFabricantes.forEach(function(fabricante) {
    const option = document.createElement("option");
    option.value = fabricante.id;
    option.textContent = fabricante.nome + " (" + fabricante.paisOrigem + ")";
    document.getElementById("fabricante-modelo").appendChild(option);
   });

   MODAL.style.display = "block";
   setShowHide(false, ".modal-content-modelo");
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
document
  .getElementById("bt-veiculos")
  .addEventListener("click", async function (event) {
    setShowHide(true, ".minha-section");
    const secaoVeiculos = document.querySelector("#veiculos");
    secaoVeiculos.style.display = "block";

    // Limpa o conteúdo anterior da seção (exceto o título e parágrafo)
    secaoVeiculos.querySelectorAll("table").forEach(function (tabela) {
      tabela.remove();
    });

    const dadosVeiculos = await getData("http://localhost:8080/api/veiculos");

    if (dadosVeiculos.ok === false) {
      document.querySelector("#veiculos").innerHTML =
        "<p>Erro ao carregar dados dos veículos.</p>";
      document.querySelector("#veiculos").style.color = "red";
      return;
    }

    secaoVeiculos.appendChild(criarTabelaVeiculo(dadosVeiculos));
  });

// Evento de clique no botão Novo Veículo
document
  .getElementById("novo-veiculo")
  .addEventListener("click", function (event) {
    alert("Função add veículo não implementada");
  });

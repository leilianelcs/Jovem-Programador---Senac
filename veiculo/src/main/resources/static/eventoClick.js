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

// Evento de clique no botão Novo Fabricante
document
  .getElementById("novo-fabricante")
  .addEventListener("click", function (event) {
    MODAL.style.display = "block";
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

// Evento de clique no botão Novo Modelo
document
  .getElementById("novo-modelo")
  .addEventListener("click", function (event) {
    alert("Função add modelo não implementada");
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

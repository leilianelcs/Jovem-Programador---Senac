const MODAL = document.getElementById("modal");
const CLOSE_MODAL_BUTTON = document.getElementById("close-modal");

// 👉 Evento de clique no botão "Fabricantes"
document
  .getElementById("bt-fabricantes")
  .addEventListener("click", async () => {
    setShowHide(true, ".minha-section");

    const secaoFabricantes = document.querySelector("#fabricantes");
    secaoFabricantes.style.display = "block";

    // Limpa tabelas antigas
    secaoFabricantes
      .querySelectorAll("table")
      .forEach((tabela) => tabela.remove());

    const dadosFabricantes = await getData(
      "http://localhost:8080/api/fabricantes"
    );

    if (!dadosFabricantes || dadosFabricantes.ok === false) {
      secaoFabricantes.innerHTML += `<p style="color:red;">Erro ao carregar dados dos fabricantes.</p>`;
      return;
    }

    secaoFabricantes.appendChild(criarTabelaFabricante(dadosFabricantes));
  });

// 👉 Fecha o modal ao clicar no botão de fechar
CLOSE_MODAL_BUTTON.addEventListener("click", () => {
  MODAL.style.display = "none";
});

let fabricanteEmEdicao = null;

// 👉 Evento de clique no botão "Novo Fabricante"
document
  .getElementById("novo-fabricante")
  .addEventListener("click", async () => {
    fabricanteEmEdicao = null; // limpa o modo de edição
    setShowHide(true, ".modal-content");

    // Atualiza o título do modal
    document.getElementById("modal-title").textContent =
      "Cadastro de Fabricante";

    // Carrega países do JSON
    const dadosPaises = await getData("http://localhost:8080/paises.json");
    const selectPais = document.getElementById("pais-fabricante");

    // Remove opções antigas
    setRemoverElementos("#pais-fabricante option");

    // Adiciona nova lista de países
    dadosPaises.forEach((pais) => {
      const option = document.createElement("option");
      option.value = pais.nome_pais;
      option.textContent = pais.nome_pais;
      selectPais.appendChild(option);
    });

    // Limpa os campos do formulário
    document.getElementById("nome-fabricante").value = "";
    document.getElementById("pais-fabricante").value = "";

    MODAL.style.display = "block";
    setShowHide(false, ".modal-content-fabricante");
  });

//Função para abrir o modal de edição de fabricante
async function abriModalEdicaoFabricante(fabricante) {
  fabricanteEmEdicao = fabricante.id; // aramzena o id do fabricante sendo editado
  setShowHide(true, ".modal-content");

  // Atualiza o título
  document.getElementById("modal-title").textContent = "Editar fabricante";

  // Carregar json países
  const dadosPaises = await getData("http://localhost:8080/paises.json");
  const selectPais = document.getElementById("pais-fabricante");
  setRemoverElementos("#pais-fabricante option");
  dadosPaises.forEach(function (pais) {
    const option = document.createElement("option");
    option.value = pais.nome_pais;
    option.textContent = pais.nome_pais;
    selectPais.appendChild(option);
  });

  //Preenche os campos com os dados do fabricante
  document.getElementById("nome-fabricante").value = fabricante.nome;
  document.getElementById("pais-fabricante").value = fabricante.paisOrigem;

  MODAL.style.display = "block";
  setShowHide(false, ".modal-content-fabricante");
}

// 👉 Evento de clique no botão "Salvar Fabricante"
document
  .getElementById("salvar-fabricante")
  .addEventListener("click", async (event) => {
    event.preventDefault();
    const nome = document.getElementById("nome-fabricante").value.trim();
    const paisOrigem = document.getElementById("pais-fabricante").value;
    const novoFabricante = { nome, paisOrigem };

    if (!nome || !paisOrigem) {
      alert("Preencha todos os campos.");
      return;
    }
//``''´´
    let resultado;
    if (fabricanteEmEdicao) { 
      resultado = await putData(`http://localhost:8080/api/fabricantes/$(fabricanteEmEdicao)`, novoFabricante);
    } else {
      resultado = await postData(
        "http://localhost:8080/api/fabricantes",
        novoFabricante
      );
    }

    if (resultado.error) {
      alert(
        `Erro ao adicionar fabricante: ${
          resultado.message || "Erro desconhecido."
        }`
      );
      return;
    }

    // Fecha o modal e limpa o formulário
    MODAL.style.display = "none";
    document.getElementById("form-fabricante").reset();

    // Atualiza a tabela de fabricantes
    const secaoFabricantes = document.querySelector("#fabricantes");
    secaoFabricantes
      .querySelectorAll("table")
      .forEach((tabela) => tabela.remove());

    const dadosAtualizados = await getData(
      "http://localhost:8080/api/fabricantes"
    );
    secaoFabricantes.appendChild(criarTabelaFabricante(dadosAtualizados));
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
document
  .getElementById("novo-modelo")
  .addEventListener("click", async function (event) {
    setShowHide(true, ".modal-content");
    //carregar fabricantes no select
    const dadosFabricantes = await getData(
      "http://localhost:8080/api/fabricantes"
    );
    if (dadosFabricantes.status === 404 || dadosFabricantes.error) {
      alert(
        "Erro ao carregar dados dos fabricantes. Erro: " +
          dadosFabricantes.message
      );
      return;
    }

    setRemoverElementos("#fabricante-modelo option");

    document
      .getElementById("fabricante-modelo")
      .appendChild(new Option("Selecione um fabricante", ""));
    dadosFabricantes.forEach(function (fabricante) {
      const option = document.createElement("option");
      option.value = fabricante.id;
      option.textContent = fabricante.nome + " (" + fabricante.paisOrigem + ")";
      document.getElementById("fabricante-modelo").appendChild(option);
    });

    MODAL.style.display = "block";
    setShowHide(false, ".modal-content-modelo");
  });

//Evento de clique botão Enviar - Novo Modelo
document
  .getElementById("salvar-modelo")
  .addEventListener("click", async function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome-modelo").value.trim();
    const fabricanteId = document.getElementById("fabricante-modelo").value;

    if (!nome || !fabricanteId) {
      alert("Preencha todos os campos.");
      return;
    }

    const novoModelo = {
      nome,
      fabricante: { id: parseInt(fabricanteId) },
    };

    // Console.log para inspecionar o que será enviado
    // console.log("Modelo a ser enviado:", novoModelo);

    const resultado = await postData(
      "http://localhost:8080/api/modelos",
      novoModelo
    );

    if (resultado.error) {
      alert(
        `Erro ao adicionar modelo: ${resultado.message || "Erro desconhecido."}`
      );
      return;
    }

    alert("Modelo adicionado com sucesso!");

    MODAL.style.display = "none";
    document.getElementById("form-modelo").reset();

    const secaoModelos = document.querySelector("#modelos");
    secaoModelos.querySelectorAll("table").forEach((tabela) => tabela.remove());

    const dadosAtualizados = await getData("http://localhost:8080/api/modelos");
    secaoModelos.appendChild(criarTabelaModelo(dadosAtualizados));
  });

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

// const MODAL = document.getElementById("modal");
// const CLOSE_MODAL_BUTTON = document.getElementById("close-modal");

// // 👉 Função genérica para carregar seção
// async function carregarSecao(endpoint, secaoId, criarTabelaFn) {
//   setShowHide(true, ".minha-section");
//   const secao = document.querySelector(secaoId);
//   secao.style.display = "block";

//   // Limpa tabelas antigas
//   secao.querySelectorAll("table").forEach(tabela => tabela.remove());

//   const dados = await getData(endpoint);

//   if (!dados || dados.error || dados.ok === false) {
//     secao.innerHTML = `<p style="color:red;">Erro ao carregar dados.</p>`;
//     return;
//   }

//   secao.appendChild(criarTabelaFn(dados));
// }

// // 👉 Função genérica para salvar entidade
// async function salvarEntidade(endpoint, dados, formId, secaoId, criarTabelaFn) {
//   const resultado = await postData(endpoint, dados);

//   if (!resultado || resultado.error) {
//     alert(`Erro ao salvar: ${resultado?.message || "Erro desconhecido."}`);
//     return;
//   }

//   alert("Registro salvo com sucesso!");
//   MODAL.style.display = "none";
//   document.getElementById(formId).reset();

//   // Atualiza tabela
//   const secao = document.querySelector(secaoId);
//   secao.querySelectorAll("table").forEach(tabela => tabela.remove());

//   const dadosAtualizados = await getData(endpoint);
//   if (!dadosAtualizados || dadosAtualizados.error || dadosAtualizados.ok === false) {
//     secao.innerHTML = `<p style="color:red;">Erro ao atualizar dados.</p>`;
//     return;
//   }
//   secao.appendChild(criarTabelaFn(dadosAtualizados));
// }

// // 👉 Fecha o modal
// CLOSE_MODAL_BUTTON.addEventListener("click", () => {
//   MODAL.style.display = "none";
// });

// // 👉 Botões de navegação
// document.getElementById("bt-fabricantes")
//   .addEventListener("click", () => carregarSecao("/api/fabricantes", "#fabricantes", criarTabelaFabricante));

// document.getElementById("bt-modelos")
//   .addEventListener("click", () => carregarSecao("/api/modelos", "#modelos", criarTabelaModelo));

// document.getElementById("bt-veiculos")
//   .addEventListener("click", () => carregarSecao("/api/veiculos", "#veiculos", criarTabelaVeiculo));

// // 👉 Novo Fabricante
// document.getElementById("novo-fabricante").addEventListener("click", async () => {
//   setShowHide(true, ".modal-content");

//   const dadosPaises = await getData("/paises.json");
//   const selectPais = document.getElementById("pais-fabricante");

//   setRemoverElementos("#pais-fabricante option");

//   dadosPaises.forEach(pais => {
//     const option = document.createElement("option");
//     option.value = pais.nome_pais;
//     option.textContent = pais.nome_pais;
//     selectPais.appendChild(option);
//   });

//   MODAL.style.display = "block";
//   setShowHide(false, ".modal-content-fabricante");
// });

// // 👉 Salvar Fabricante
// document.getElementById("salvar-fabricante").addEventListener("click", async (event) => {
//   event.preventDefault();

//   const nome = document.getElementById("nome-fabricante").value.trim();
//   const paisOrigem = document.getElementById("pais-fabricante").value;

//   if (!nome || !paisOrigem) {
//     alert("Preencha todos os campos.");
//     return;
//   }

//   await salvarEntidade("/api/fabricantes", { nome, paisOrigem }, "form-fabricante", "#fabricantes", criarTabelaFabricante);
// });

// // 👉 Novo Modelo
// document.getElementById("novo-modelo").addEventListener("click", async () => {
//   setShowHide(true, ".modal-content");

//   const dadosFabricantes = await getData("/api/fabricantes");
//   if (!dadosFabricantes || dadosFabricantes.error || dadosFabricantes.ok === false) {
//     alert("Erro ao carregar fabricantes.");
//     return;
//   }

//   setRemoverElementos("#fabricante-modelo option");

//   const selectFabricante = document.getElementById("fabricante-modelo");
//   selectFabricante.appendChild(new Option("Selecione um fabricante", ""));

//   dadosFabricantes.forEach(fabricante => {
//     const option = document.createElement("option");
//     option.value = fabricante.id;
//     option.textContent = `${fabricante.nome} (${fabricante.paisOrigem})`;
//     selectFabricante.appendChild(option);
//   });

//   MODAL.style.display = "block";
//   setShowHide(false, ".modal-content-modelo");
// });

// // 👉 Salvar Modelo
// document.getElementById("salvar-modelo").addEventListener("click", async (event) => {
//   event.preventDefault();

//   const nome = document.getElementById("nome-modelo").value.trim();
//   const fabricanteId = document.getElementById("fabricante-modelo").value;

//   if (!nome || !fabricanteId) {
//     alert("Preencha todos os campos.");
//     return;
//   }

//   const novoModelo = { nome, fabricante: { id: parseInt(fabricanteId) } };

//   await salvarEntidade("/api/modelos", novoModelo, "form-modelo", "#modelos", criarTabelaModelo);
// });

// // 👉 Novo Veículo (não implementado)
// document.getElementById("novo-veiculo").addEventListener("click", () => {
//   alert("Função add veículo não implementada");
// });

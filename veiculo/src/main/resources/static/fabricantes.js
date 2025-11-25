
// Função reutilizável para criar uma linha da tabela
function criarLinhaFabricante(item) {
  const tr = document.createElement("tr");

  const tdId = document.createElement("td");
  tdId.textContent = item.id;
  tr.appendChild(tdId);

  const tdNome = document.createElement("td");
  tdNome.textContent = item.nome;
  tr.appendChild(tdNome);

  const tdPais = document.createElement("td");
  tdPais.textContent = item.paisOrigem;
  tr.appendChild(tdPais);

  const tdAcao = document.createElement("td");

  // Botão Editar
  const btnEditar = document.createElement("button");
  btnEditar.classList.add("btn", "edit");
  btnEditar.textContent = "Editar";
  btnEditar.addEventListener("click", function () {
    // Abrir modal com dados preenchidos
    document.getElementById("nome-fabricante").value = item.nome;
    document.getElementById("pais-fabricante").value = item.paisOrigem;
    document.getElementById("modal").style.display = "block";

    // Alterar comportamento do botão salvar para atualizar
    const botaoSalvar = document.getElementById("salvar-fabricante");
    botaoSalvar.textContent = "Atualizar";
    botaoSalvar.onclick = async function (event) {
      event.preventDefault();
      const nome = document.getElementById("nome-fabricante").value.trim();
      const paisOrigem = document.getElementById("pais-fabricante").value;

      if (!nome || !paisOrigem) {
        alert("Preencha todos os campos.");
        return;
      }
      try {
        const resposta = await fetch(`http://localhost:8080/api/fabricantes/${item.id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ nome, paisOrigem })
        });

        if (resposta.ok) {
          const atualizado = await resposta.json();
          tdNome.textContent = atualizado.nome;
          tdPais.textContent = atualizado.paisOrigem;
          alert("Fabricante atualizado com sucesso!");
          document.getElementById("modal").style.display = "none";
          botaoSalvar.textContent = "Salvar";
        } else {
          const erro = await resposta.json();
          alert(`Erro ao atualizar: ${erro.message || resposta.statusText}`);
        }
      } catch (erro) {
        alert(`Erro de conexão: ${erro.message}`);
      }
    };
  });

  // Botão Deletar
  const btnDeletar = document.createElement("button");
  btnDeletar.classList.add("btn", "delete");
  btnDeletar.textContent = "Deletar";
  btnDeletar.addEventListener("click", async function () {
    const confirmacao = confirm(`Tem certeza que deseja excluir o fabricante com ID ${item.id}?`);
    if (!confirmacao) return;

    try {
      const resposta = await fetch(`http://localhost:8080/api/fabricantes/${item.id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
      });

      if (resposta.ok) {
        tr.remove();
        alert(`Fabricante com ID ${item.id} deletado com sucesso.`);
      } else {
        const erro = await resposta.json();
        alert(`Erro ao deletar: ${erro.message || resposta.statusText}`);
      }
    } catch (erro) {
      alert(`Erro de conexão: ${erro.message}`);
    }
  });

  // Adiciona os botões lado a lado
  tdAcao.appendChild(btnEditar);
  tdAcao.appendChild(btnDeletar);

  tr.appendChild(tdAcao);

  return tr;
}

// Função para criar a tabela completa
const criarTabelaFabricante = function (dados) {
  const tabela = document.createElement("table");
  const thead = document.createElement("thead");
  const tbody = document.createElement("tbody");

  const trTitle = document.createElement("tr");
  const thTitle = document.createElement("th");
  thTitle.textContent = "Fabricantes";
  thTitle.colSpan = 4;
  trTitle.appendChild(thTitle);
  thead.appendChild(trTitle);

  const cabecalho = ["ID", "Fabricante", "País de Origem", "Ação"];
  const trCabecalho = document.createElement("tr");
  cabecalho.forEach(function (campo) {
    const th = document.createElement("th");
    th.textContent = campo;
    trCabecalho.appendChild(th);
  });
  thead.appendChild(trCabecalho);

  tabela.classList.add("tabela-dados");
  tabela.appendChild(thead);

  dados.forEach(function (item) {
    const tr = criarLinhaFabricante(item);
    tbody.appendChild(tr);
  });

  tabela.appendChild(tbody);
  return tabela;
};

// Controle do modal
document.addEventListener("DOMContentLoaded", function () {
  const modal = document.getElementById("modal");
  const btnAbrir = document.getElementById("novo-fabricante");
  const btnFechar = document.getElementById("close-modal");

  btnAbrir.addEventListener("click", () => {
    modal.style.display = "block";
  });

  btnFechar.addEventListener("click", () => {
    modal.style.display = "none";
  });

  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  });
});

// Evento de clique no botão salvar
document.getElementById("salvar-fabricante").addEventListener("click", async function (event) {
  event.preventDefault();

  const nome = document.getElementById("nome-fabricante").value.trim();
  const paisOrigem = document.getElementById("pais-fabricante").value;
  const botaoSalvar = document.getElementById("salvar-fabricante");

  if (!nome || !paisOrigem) {
    alert("Preencha todos os campos.");
    return;
  }

  const novoFabricante = { nome, paisOrigem };

  botaoSalvar.disabled = true;
  botaoSalvar.textContent = "Salvando...";

  try {
    const resposta = await fetch("http://localhost:8080/api/fabricantes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(novoFabricante)
    });

    if (resposta.ok) {
      const fabricanteCriado = await resposta.json();
      alert("Fabricante adicionado com sucesso!");

      const tabela = document.querySelector(".tabela-dados");
      if (tabela) {
        tabela.querySelector("tbody").appendChild(criarLinhaFabricante(fabricanteCriado));
      }

      document.getElementById("form-fabricante").reset();
      document.getElementById("modal").style.display = "none";
    } else {
      const erro = await resposta.json();
      alert(`Erro ao adicionar: ${erro.message || resposta.statusText}`);
    }
  } catch (erro) {
    alert(`Erro de conexão: ${erro.message}`);
  } finally {
    botaoSalvar.disabled = false;
    botaoSalvar.textContent = "Salvar";
  }
});

// const criarTabelaFabricante = function (dados) {
//   const tabela = document.createElement("table");
//   const thead = document.createElement("thead");
//   const tbody = document.createElement("tbody");

//   // Cabeçalho principal
//   const trTitle = document.createElement("tr");
//   const thTitle = document.createElement("th");
//   thTitle.textContent = "Fabricantes";
//   thTitle.colSpan = 4;
//   trTitle.appendChild(thTitle);
//   thead.appendChild(trTitle);

//   // Cabeçalho das colunas
//   const cabecalho = ["ID", "Fabricante", "País de Origem", "Ação"];
//   const trCabecalho = document.createElement("tr");
//   cabecalho.forEach(function (campo) {
//     const th = document.createElement("th");
//     th.textContent = campo;
//     trCabecalho.appendChild(th);
//   });
//   thead.appendChild(trCabecalho); 

//   // Estilização da tabela
//   tabela.classList.add("tabela-dados");
//   tabela.appendChild(thead);

//   // Corpo da tabela
//   dados.forEach(function (item) {
//     const tr = document.createElement("tr");

//     // ID
//     const tdId = document.createElement("td");
//     tdId.textContent = item.id;
//     tr.appendChild(tdId);

//     // Fabricante
//     const tdFabricante = document.createElement("td");
//     tdFabricante.textContent = item.nome;
//     tr.appendChild(tdFabricante);

//     // País de origem
//     const tdPaisOrigem = document.createElement("td");
//     tdPaisOrigem.textContent = item.paisOrigem;
//     tr.appendChild(tdPaisOrigem);

//     // Ícones
//     const deletar = document.createElement("td");
//     deletar.innerHTML = '<button class="btn delete">Deletar</button>';
//     deletar.addEventListener("click", async function () {
//       const confirmacao = confirm(
//         `Tem certeza que deseja excluir o fabricante com ID ${item.id}?`
//       );
//       if (!confirmacao) return;

//       try {
//         const resposta = await fetch(
//           `http://localhost:8080/api/fabricantes/${item.id}`,
//           {
//             method: "DELETE",
//             headers: {
//               "Content-Type": "application/json",
//             },
//           }
//         );

//         if (resposta.ok) {
//           tr.remove();
//           alert(`Fabricante com ID ${item.id} deletado com sucesso.`);
//         } else {
//           const erro = await resposta.json();
//           alert(`Erro ao deletar: ${erro.message || resposta.statusText}`);
//         }
//       } catch (erro) {
//         alert(`Erro de conexão: ${erro.message}`);
//       }
//     });
//     tr.appendChild(deletar);

//     tbody.appendChild(tr);
//   });

//   tabela.appendChild(tbody);
//   return tabela;
// };

// document.addEventListener("DOMContentLoaded", function () {
//   const modal = document.getElementById("modal");
//   const btnAbrir = document.getElementById("novo-fabricante");
//   const btnFechar = document.getElementById("close-modal");

//   btnAbrir.addEventListener("click", () => {
//     modal.style.display = "block";
//   });

//   btnFechar.addEventListener("click", () => {
//     modal.style.display = "none";
//   });

//   window.addEventListener("click", (event) => {
//     if (event.target === modal) {
//       modal.style.display = "none";
//     }
//   });
// });

// document.getElementById("salvar-fabricante").addEventListener("click", async function (event) {
//     event.preventDefault();

//     const nome = document.getElementById("nome-fabricante").value.trim();
//     const paisOrigem = document.getElementById("pais-fabricante").value;

//     if (!nome || !paisOrigem) {
//       alert("Preencha todos os campos.");
//       return;
//     }

//     const novoFabricante = { nome, paisOrigem };

//     try {
//       const resposta = await fetch("http://localhost:8080/api/fabricantes", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(novoFabricante),
//       });

//       if (resposta.ok) {
//         const fabricanteCriado = await resposta.json();
//         alert("Fabricante adicionado com sucesso!");

//         // Atualiza a tabela se ela já estiver renderizada
//         const tabela = document.querySelector(".tabela-dados");
//         if (tabela) {
//           const novoTr = document.createElement("tr");

//           const tdId = document.createElement("td");
//           tdId.textContent = fabricanteCriado.id;
//           novoTr.appendChild(tdId);

//           const tdNome = document.createElement("td");
//           tdNome.textContent = fabricanteCriado.nome;
//           novoTr.appendChild(tdNome);

//           const tdPais = document.createElement("td");
//           tdPais.textContent = fabricanteCriado.paisOrigem;
//           novoTr.appendChild(tdPais);

//           const tdAcao = document.createElement("td");
//           tdAcao.innerHTML = '<button class="btn delete">Deletar</button>';
//           tdAcao
//             .querySelector("button")
//             .addEventListener("click", async function () {
//               const confirmacao = confirm(
//                 `Tem certeza que deseja excluir o fabricante com ID ${fabricanteCriado.id}?`
//               );
//               if (!confirmacao) return;

//               try {
//                 const resposta = await fetch(
//                   `http://localhost:8080/api/fabricantes/${fabricanteCriado.id}`,
//                   {
//                     method: "DELETE",
//                     headers: { "Content-Type": "application/json" },
//                   }
//                 );

//                 if (resposta.ok) {
//                   novoTr.remove();
//                   alert(
//                     `Fabricante com ID ${fabricanteCriado.id} deletado com sucesso.`
//                   );
//                 } else {
//                   const erro = await resposta.json();
//                   alert(
//                     `Erro ao deletar: ${erro.message || resposta.statusText}`
//                   );
//                 }
//               } catch (erro) {
//                 alert(`Erro de conexão: ${erro.message}`);
//               }
//             });

//           novoTr.appendChild(tdAcao);
//           tabela.querySelector("tbody").appendChild(novoTr);
//         }

//         // Limpa e fecha o modal
//         document.getElementById("form-fabricante").reset();
//         document.getElementById("modal").style.display = "none";
//       } else {
//         const erro = await resposta.json();
//         alert(`Erro ao adicionar: ${erro.message || resposta.statusText}`);
//       }
//     } catch (erro) {
//       alert(`Erro de conexão: ${erro.message}`);
//     }
//   });
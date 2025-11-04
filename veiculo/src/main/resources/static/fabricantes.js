const criarTabelaFabricante = function(dados) {
    const tabela = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");

    // Cabeçalho principal
    const trTitle = document.createElement("tr");
    const thTitle = document.createElement("th");
    thTitle.textContent = "Fabricantes";
    thTitle.colSpan = 4;
    trTitle.appendChild(thTitle);
    thead.appendChild(trTitle);

    // Cabeçalho das colunas
    const cabecalho = ["ID", "Fabricante", "País de Origem", "Ação"];
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

        // Fabricante
        const tdFabricante = document.createElement("td");
        tdFabricante.textContent = item.nome;
        tr.appendChild(tdFabricante);

        // País de origem
        const tdPaisOrigem = document.createElement("td");
        tdPaisOrigem.textContent = item.paisOrigem;
        tr.appendChild(tdPaisOrigem);

         // Ícones
         const deletar = document.createElement("td");
         deletar.innerHTML = '<button class="btn delete">Deletar</button>';
         deletar.addEventListener("click", async function () {
            const confirmacao = confirm(`Tem certeza que deseja excluir o fabricante com ID ${item.id}?`);
            if (!confirmacao) return;
        
            try {
                const resposta = await fetch(`http://localhost:8080/api/fabricantes/${item.id}`, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
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
        tr.appendChild(deletar);  

        tbody.appendChild(tr);
    });

    tabela.appendChild(tbody);
    return tabela;
};

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
  
        // Atualiza a tabela se ela já estiver renderizada
        const tabela = document.querySelector(".tabela-dados");
        if (tabela) {
          const novoTr = document.createElement("tr");
  
          const tdId = document.createElement("td");
          tdId.textContent = fabricanteCriado.id;
          novoTr.appendChild(tdId);
  
          const tdNome = document.createElement("td");
          tdNome.textContent = fabricanteCriado.nome;
          novoTr.appendChild(tdNome);
  
          const tdPais = document.createElement("td");
          tdPais.textContent = fabricanteCriado.paisOrigem;
          novoTr.appendChild(tdPais);
  
          const tdAcao = document.createElement("td");
          tdAcao.innerHTML = '<button class="btn delete">Deletar</button>';
          tdAcao.querySelector("button").addEventListener("click", async function () {
            const confirmacao = confirm(`Tem certeza que deseja excluir o fabricante com ID ${fabricanteCriado.id}?`);
            if (!confirmacao) return;
  
            try {
              const resposta = await fetch(`http://localhost:8080/api/fabricantes/${fabricanteCriado.id}`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" }
              });
  
              if (resposta.ok) {
                novoTr.remove();
                alert(`Fabricante com ID ${fabricanteCriado.id} deletado com sucesso.`);
              } else {
                const erro = await resposta.json();
                alert(`Erro ao deletar: ${erro.message || resposta.statusText}`);
              }
            } catch (erro) {
              alert(`Erro de conexão: ${erro.message}`);
            }
          });
  
          novoTr.appendChild(tdAcao);
          tabela.querySelector("tbody").appendChild(novoTr);
        }
  
        // Limpa e fecha o modal
        document.getElementById("form-fabricante").reset();
        document.getElementById("modal").style.display = "none";
      } else {
        const erro = await resposta.json();
        alert(`Erro ao adicionar: ${erro.message || resposta.statusText}`);
      }
    } catch (erro) {
      alert(`Erro de conexão: ${erro.message}`);
    }
  });
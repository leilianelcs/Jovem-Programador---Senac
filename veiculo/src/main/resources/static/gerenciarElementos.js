/*
        Função para mostrar ou ocultar elementos
        Parâmetros: 
        esconder: booleano - true para ocultar, false para mostrar
        elemento: string - classe ou id do elemento a ser manipulado
        Exemplo de uso:
        setShowHide(true, ".minha-section")
        */

const setShowHide = function (esconder, elemento) {
  document.querySelectorAll(elemento).forEach(function (section) {
    section.style.display = esconder ? "none" : "block";
  });
};

const setRemoverElementos = function (elemento) {
  document.querySelectorAll(elemento).forEach(function (item) {
    item.remove();
  });
};

// // Coluna ações com botões Editar
// const tdAcoes = document.createElement("td");
// tdAcoes.style.display = "flex";
// tdAcoes.style.gap = "5px";

// //Botão editar
// const btnEditar = document.createElement("button");
// btnEditar.textContent = "Editar";
// btnEditar.classList.add("btn", "edit");
// btnEditar.style.cursor = "pointer";
// btnEditar.addEventListener("click", async function (event) {
//   alert("Função de editar para o item ID: " + item.id);
// });

// //Botão excluir
// const btnExcluir = document.createElement("button");
// btnExcluir.textContent = "Excluir";
// btnExcluir.classList.add("btn", "delete");
// btnExcluir.style.cursor = "pointer";
// btnExcluir.addEventListener("click", async function (event) {
//   if (confirm("Tem certeza que deseja excluir este item?")) {
//     const resposta = await SetDelete(
//       `http://localhost:8080/api/fabricantes/${item.id}`
//     );
//     if (resposta.sucess) {
//       tr.remove();
//       alert(resposta.message);
//     } else {
//       mostraErro(resposta);
//     }
//   }
// });

// tdAcoes.appendChild(btnEditar);
// tddAcoes.appendChild(btnExcluir);
// tr.appendChild(tdAcoes);
// tbody.appendChild(tr);

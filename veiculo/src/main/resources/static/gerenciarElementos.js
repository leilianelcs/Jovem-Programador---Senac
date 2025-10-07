
        /* Função 
        */
        
        // cria tabela que seja preenchida com os dados de um array de objetos
function criarTabela(dados) {
    const table = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");
        
        // cria o cabeçalho da tabela
    const cabecalho = Object.keys(dados[0]);        
    const trHead = document.createElement("tr");
    cabecalho.forEach(function(campo) {
        const th = document.createElement("th");
            th.textContent = campo;
            trHead.appendChild(th);
        });        
    thead.appendChild(trHead);
    table.appendChild(thead);
        
            // cria o corpo da tabela
            dados.forEach(function(item) {
                const trBody = document.createElement("tr");
                cabecalho.forEach(function(campo) {
                    const td = document.createElement("td");
                    td.textContent = item[campo];
                    trBody.appendChild(td);
                });
                tbody.appendChild(trBody);
            });
        
            table.appendChild(tbody);   
            return table;
        }
        
        /*
        Função para mostrar ou ocultar elementos
        Parâmetros: 
        esconder: booleano - true para ocultar, false para mostrar
        elemento: string - classe ou id do elemento a ser manipulado
        Exemplo de uso:
        setShowHide(true, ".minha-section")
        */

    const setShowHide = function (esconder, elemento) {
        document.querySelectorAll(elemento).forEach(function(section) {
            section.style.display = esconder ? "none" : "block";
        });
    };         

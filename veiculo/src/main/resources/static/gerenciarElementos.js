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

const setRemoverElementos = function(elemento) {
  document.querySelectorAll(elemento).forEach(function(item) {
      item.remove();
  });
}
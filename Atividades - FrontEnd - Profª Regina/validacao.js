document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formulario");
  
    const campos = {
      nome: {
        input: document.getElementById("nome"),
        erro: document.getElementById("erro-nome"),
        validar: (valor) => valor.length >= 2,
        mensagem: "O nome deve ter pelo menos 2 caracteres."
      },
      email: {
        input: document.getElementById("email"),
        erro: document.getElementById("erro-email"),
        validar: (valor) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(valor),
        mensagem: "Digite um email válido."
      },
      telefone: {
        input: document.getElementById("telefone"),
        erro: document.getElementById("erro-telefone"),
        validar: (valor) => valor === "" || /^\(?\d{2}\)?[\s-]?\d{4,5}-?\d{4}$/.test(valor),
        mensagem: "Formato de telefone inválido. Ex: (99) 99999-9999"
      },
      mensagem: {
        input: document.getElementById("mensagem"),
        erro: document.getElementById("erro-mensagem"),
        validar: (valor) => valor.length >= 10,
        mensagem: "A mensagem deve ter pelo menos 10 caracteres."
      }
    };
  
    Object.values(campos).forEach(({ input, erro, validar, mensagem }) => {
      input.addEventListener("input", () => {
        if (!validar(input.value)) {
          erro.textContent = mensagem;
        } else {
          erro.textContent = "";
        }
      });
    });
  
    form.addEventListener("submit", (e) => {
      let valido = true;
      Object.values(campos).forEach(({ input, erro, validar, mensagem }) => {
        if (!validar(input.value)) {
          erro.textContent = mensagem;
          valido = false;
        }
      });
      if (!valido) e.preventDefault();
    });
  });
  
async function getData(url) {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      //   throw new Error(`Response status: ${response.status}`);
      return response;
    }
    const result = await response.json();
    return result;
  } catch (error) {
    return error;
  }
}

async function postData(url, data) {
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });

    if (!response.ok) {
      const contentType = response.headers.get("content-type");
      if (contentType && contentType.includes("aplication/json")) {
        return await response.json();
      } else {
        return await response.text();
      }
    } else {
      try {
        const error = await response.json();
        return { error: true, status: response.status, ...error };
      } catch {
        return {
          error: true,
          status: response.status,
          message: response.statusText,
        };
      }
    }
  } catch (error) {
    return { error: true, message: "Erro de conexão: " + error.message };
  }
}

async function setDelete(url) {
  try {
    const response = await fetch(url, { method: "DELETE" });

    if (response.ok) {
      return { success: true, message: "Excluído com sucesso" };
    } else {
      //erro de exception
      const error = await response.json();
      return { error: true, status: response.status, ...error }; //
      try {
      } catch (error) {}
    }
  } catch (error) {}
}


// verifica se a resposta é sucesso
function isSuccess(response) {
  return response && !response.error;
}

// function mostrarErro(response) {
//   if (response.message) {
//     let mensagem =

//   } else {

//   }

//}

const formatarPreco = function(valor) {

};

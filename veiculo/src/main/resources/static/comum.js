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
    // console.error(error.message);
    return error;
  }
}

async function setDelete(url) {
  try {
    const response = await fetch(url, { method: "DELETE"});

    if (response.ok) {
      return {success: true, message: "Excluído com sucesso"};      
    } else { 
      //erro de exception
      const error = await response.json();
      return {error: true}
      try {
        
      } catch (error) {
        
      }
      
    }
    
  } catch (error) {
    
  }
  
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


                    
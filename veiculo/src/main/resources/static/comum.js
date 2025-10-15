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

(() => {


    function formatProduct(product) {
        return '<div class="product col-md-2 mt-2 bg-product rounded ml-4 mr-4 mb-5">'
            + '<div class="text-center mt-3">'
            + `<a href="/products/details/${product.product.id}"><img src="${product.product.imageUrl}" 
class="product-image-home img-thumbnail px-auto" alt="Image not loaded..."/></a>`
            + '</div>'
            + `<h5 class="text-center font-weight-bold mt-3">Nombre: ${product.product.name}</h5>`
            + `<h5 class="text-center font-weight-bold" style="color: black">Precio: $${product.product.price.toFixed(2).strike()}</h5>`
            + `<h5 class="text-center font-weight-bold" style="color: orangered">Nuevo Precio: $${product.price.toFixed(2)}</h5>`
            + '</div>'
    }
    fetch('/fetch/sales/All')
        .then((response) => response.json())
        .then((json) => {
            $('.products-data').empty();
            if (json.length === 0) {
                $('.products-data').append(`<h1 class="text-center font-weight-bold">No hay productos en esta categoria: ${category} .</h1>`)
            } else {
                for (let i = 0; i < json.length; i++) {
                    $('.products-data').append(formatProduct(json[i]));
                }
            }
        })
        .catch((err) => console.log(err));
})();
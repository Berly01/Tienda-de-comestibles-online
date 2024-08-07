(() => {
    $('input[type=radio][name=inlineRadioOptions]').change(function () {
        $('#headline').text(this.value + " Orders");
        const table = $('#ordersTable').empty();
        let index = 1;
        if (this.value === "All") {
            table
                .append(
                    $('<thead>')
                        .append($('<tr id="headlineRow">')
                            .append('<th class="text-center" scope="col">#</th>')
                            .append('<th class="text-center" scope="col">Cliente</th>')
                            .append('<th class="text-center" scope="col">Emitido el</th>')
                            .append('<th class="text-center" scope="col">Precio Total</th>')
                            .append('<th class="text-center" scope="col">Estado del Pedido</th>')
                            .append('<th class="text-center" scope="col">Productos</th>')
                        )
                )
                .append($('<tbody>')
                );
        } else if (this.value === "Delivered") {
            table
                .append(
                    $('<thead>')
                        .append($('<tr id="headlineRow">')
                            .append('<th class="text-center" scope="col">#</th>')
                            .append('<th class="text-center" scope="col">Cliente</th>')
                            .append('<th class="text-center" scope="col">Entregado el</th>')
                            .append('<th class="text-center" scope="col">Precio Total</th>')
                            .append('<th class="text-center" scope="col">Estado del Pedido</th>')
                            .append('<th class="text-center" scope="col">Productos</th>')
                        )
                )
                .append($('<tbody>')
                );
        } else if (this.value === "Shipped"){
            table
                .append(
                    $('<thead>')
                        .append($('<tr id="headlineRow">')
                            .append('<th class="text-center" scope="col">#</th>')
                            .append('<th class="text-center" scope="col">Cliente</th>')
                            .append('<th class="text-center" scope="col">Enviado el</th>')
                            .append('<th class="text-center" scope="col">Precio Total</th>')
                            .append('<th class="text-center" scope="col">Estado del Pedido</th>')
                            .append('<th class="text-center" scope="col">Productos</th>')
                            .append('<th class="text-center" scope="col">Cambiar Estado</th>')
                        )
                )
                .append($('<tbody>')
                );
        } else {
            table
                .append(
                    $('<thead>')
                        .append($('<tr id="headlineRow">')
                            .append('<th class="text-center" scope="col">#</th>')
                            .append('<th class="text-center" scope="col">Cliente</th>')
                            .append('<th class="text-center" scope="col">Emitido el</th>')
                            .append('<th class="text-center" scope="col">Precio Total</th>')
                            .append('<th class="text-center" scope="col">Estado del Pedido</th>')
                            .append('<th class="text-center" scope="col">Productos</th>')
                            .append('<th class="text-center" scope="col">Cambiar Estado</th>')
                        )
                )
                .append($('<tbody>')
                );
        }

        fetch('/order/fetch/' + this.value)
            .then(res => res.json())
            .then(res => res.forEach((order) => {
                if (this.value === "All") {
                    let issuedOn = order.issuedOn.toString().replace("T", " ").slice(0, -3);
                    table.find('tbody')
                        .append($('<tr>')
                            .append($('<th class="text-center" scope="row">').text(index))
                            .append($('<td class="text-center">').text(order.customer.username))
                            .append($('<td class="text-center">').text(issuedOn))
                            .append($('<td class="text-center">').text(order.totalPrice))
                            .append($('<td class="text-center">').text(order.status))
                            .append($('<td class="text-center">')
                                .append($('<a class="btn btn-info mr-2">')
                                    .attr("href", `/order/all/details/${order.id}`)
                                    .text("Products")
                                )
                            )
                        )
                } else if (this.value === "Delivered") {
                    let statusDate = order.statusDate.toString().replace("T", " ").slice(0, -3);
                    table.find('tbody')
                        .append($('<tr>')
                            .append($('<th class="text-center" scope="row">').text(index))
                            .append($('<td class="text-center">').text(order.customer.username))
                            .append($('<td class="text-center">').text(statusDate))
                            .append($('<td class="text-center">').text(order.totalPrice))
                            .append($('<td class="text-center">').text(order.status))
                            .append($('<td class="text-center">')
                                .append($('<a class="btn btn-info mr-2">')
                                    .attr("href", `/order/all/details/${order.id}`)
                                    .text("Products")
                                )
                            )
                        )
                } else if (this.value === "Pending") {
                    let issuedOn = order.issuedOn.toString().replace("T", " ").slice(0, -3);
                    table.find('tbody')
                        .append($('<tr>')
                            .append($('<th class="text-center" scope="row">').text(index))
                            .append($('<td class="text-center">').text(order.customer.username))
                            .append($('<td class="text-center">').text(issuedOn))
                            .append($('<td class="text-center">').text(order.totalPrice))
                            .append($('<td class="text-center">').text(order.status))
                            .append($('<td class="text-center">')
                                .append($('<a class="btn btn-info mr-2">')
                                    .attr("href", `/order/all/details/${order.id}`)
                                    .text("Products")
                                )
                            )
                            .append($('<td class="text-center">')
                                .append($('<a class="btn btn-info mr-2 changeStatusButton">')
                                    .attr("href", `/order/change/status/${order.id}`)
                                    .text("Ship")
                                )
                            )
                        )
                } else if (this.value === "Shipped") {
                    let statusDate = order.statusDate.toString().replace("T", " ").slice(0, -3);
                    table.find('tbody')
                        .append($('<tr>')
                            .append($('<th class="text-center" scope="row">').text(index))
                            .append($('<td class="text-center">').text(order.customer.username))
                            .append($('<td class="text-center">').text(statusDate))
                            .append($('<td class="text-center">').text(order.totalPrice))
                            .append($('<td class="text-center">').text(order.status))
                            .append($('<td class="text-center">')
                                .append($('<a class="btn btn-info mr-2">')
                                    .attr("href", `/order/all/details/${order.id}`)
                                    .text("Products")
                                )
                            )
                            .append($('<td class="text-center">')
                                .append($('<a class="btn btn-info mr-2 changeStatusButton">')
                                    .attr("href", `/order/change/status/${order.id}`)
                                    .text("Deliver")
                                )
                            )
                        )
                }
                index++;
            })).catch(err => console.log(err));
    })
})();
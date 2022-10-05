var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?username=" + username);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getGlamping(false, "ASC");

        $("#ordenar-ubicacion").click(ordenarGlamping);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}
function getGlamping(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletglampingLista",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarglampings(parsedResult);
            } else {
                console.log("Error recuperando los datos de los glampings");
            }
        }
    });
}
function mostrarGlampings(glampings) {

    let contenido = "";

    $.each(glampings, function (index, glamping) {

        glamping = JSON.parse(glamping);
        let precio;

        if (glamping.cantidad > 0) {

            if (user.premium) {

                if (glamping.estado) {
                    precio = (2 - (2 * 0.1));
                } else {
                    precio = (1 - (1 * 0.1));
                }
            } else {
                if (glamping.estado) {
                    precio = 2;
                } else {
                    precio = 1;
                }
            }

            contenido += '<tr><th scope="row">' + glamping.id_glamping + '</th>' +
                    '<td>' + glamping.nombre + '</td>' +
                    '<td>' + glamping.ubicacion + '</td>' +
                    '<td>' + glamping.descripcion + '</td>' +
                    '<td>' + glamping.cantidad + '</td>' +
                    '<td>' + glamping.valor + '</td>' +
                    '<td><input type="checkbox" name="novedad" id="novedad' + glamping.id_glamping + '" disabled ';
            if (glamping.novedad) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="alquilarGlamping(' + glamping.id_glamping + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#glampings-tbody").html(contenido);
}

function ordenarGlampings() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getGlampings(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getGlampings(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getGlampings(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function alquilarGlamping(id_glamping, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletGlampingReservar",
        data: $.param({
            id: id,
            username: username

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la reserva del glamping");
            }
        }
    });
}


async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            username: username,
            saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}
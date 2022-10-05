var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        $("#user-saldo").html("$" + user.saldo.toFixed());

        getReservadas(user.username);
    });

    $("#reservar-btn").attr("href", `home.html?username=${username}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "index.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-username").val(parsedResult.username);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-apellido").val(parsedResult.apellido);
                $("#input-email").val(parsedResult.email);                
                $("#input-telefono").val(parsedResult.telefono);
                $("#input-saldo").val(parsedResult.saldo.toFixed(2));

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getReservadas(username) {


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAlquilerListar",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult)

            } else {
                console.log("Error recuperando los datos de las reservas");
            }
        }
    });
}

function mostrarHistorial(glampings) {
    let contenido = "";
    if (glampings.length >= 1) {
        $.each(glampings, function (index, glamping) {
            glamping = JSON.parse(glamping);

            contenido += '<tr><th scope="row">' + glamping.id_glamping + '</th>' +
                    '<td>' + glamping.nombre + '</td>' +
                    '<td>' + glamping.ubicacion + '</td>' +
                    '<td><input type="checkbox" name="novedad" id="novedad' + glamping.id_glamping 
                    + '" disabled ';
            if (glamping.novedad) {
                contenido += 'checked'
            }
            contenido += '></td><td>' + glamping.fechaAlquiler + '</td>' +
                    '<td><button id="devolver-btn" onclick= "devolverpelicula(' + glamping.id_glamping 
                    + ');" class="btn btn-danger">Devolver pelicula</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function devolverGlamping(id_glamping) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletGlampingDevolver",
        data: $.param({
            username: username,
            id_glamping: id_glamping,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error devolviendo el glamping");
            }
        }
    });

}

function modificarUsuario() {

    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let apellido = $("#input-apellido").val();
    let email = $("#input-email").val();
    let telefono = $("#input-telefono").val();
    let saldo = $("#input-saldo").val();
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            username: username,
            contrasena: contrasena,
            nombre: nombre,
            apellido: apellido,
            email: email,
            telefono: telefono,
            saldo: saldo,
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            username: username
        }),
        success: function (result) {

            if (result != false) {

                console.log("Usuario eliminado")

            } else {
                console.log("Error eliminando el usuario");
            }
        }
    });
}
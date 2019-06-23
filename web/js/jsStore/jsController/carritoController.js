(function () {

    new STORE.DOMObjectLook("op_verFacturas");
    new STORE.DOMObjectLook("op_verCarrito");
    var ajax = STORE.Ajax;
    var llamada;

    var arrayCarrito = [];
    var facturas =[];
    sessionStorage.setItem("pag", 0);
    sessionStorage.setItem("facturasGuardadas","false");

//llama al servidor mediante ajax para traer los datos en el carro del cliente actual en forma de json
    var cargarCarrito = function(estado){
        llamada = new ajax.CargadorContenidos("/getCarrito", function () {
            estado = JSON.parse(llamada.req.responseText);
            if (estado.length === 0) {
                alert("Nada en el carro");
            } else {
                console.log(estado.length + " articulos")
            }
            estado.forEach(carrito => {
                carrito.posicionArray = arrayCarrito.length;
                insertarEnArray(carrito);
            })
            sessionStorage.setItem("carritoCargado", "true");
        });
    }
    STORE.namespace('STORE.carritoController.insertarEnArray');
	//inserta en un array como json,  el objeto traido de la bbdd
    STORE.carritoController.insertarEnArray = function (model) {
        model.posicionArray = arrayCarrito.length;
        var encontrado = false;
        arrayCarrito.forEach(modelo => {
            if (model.IdModelo === modelo.IdModelo) {
                modelo.cantidadPedida++;
                encontrado = true;
            }
        })
        if (!encontrado) {
            model={
                IdModelo:model.IdModelo,
                actualPrecioModelo:model.actualPrecioModelo,
                cantidadPedida:model.cantidadPedida || 1,
                posicionArray:model.posicionArray,
                IdCliente:parseInt(sessionStorage.getItem("idClient"))
            }
            arrayCarrito.push(model);
        }
    }
    arrayCarrito = (function () {
        var estado = [];
        cargarCarrito(estado);
        return estado;
    })();
	//resta una unidad de la linea de carritos  actual 
    var quitarUnidadLineaCarrito = function (datosLineaVenta) {
        datosLineaVenta.cantidadPedida--;
        if(datosLineaVenta.cantidadPedida===0){
            borrarLineaCarrito(datosLineaVenta.posicionArray)
        }else{
            mostrarCarrito();
        }
    }
	//suma una unidad de la linea de carritos seleccionada 
    var addUnidadLineaCarrito = function (datosLineaVenta) {
        datosLineaVenta.cantidadPedida++;
        mostrarCarrito();
    }
	//borra la linea de carrito de la posicion dada
    var borrarLineaCarrito = function (posicion) {
        delete arrayCarrito[posicion];
        let arrayTemp =[];
        arrayCarrito.forEach(function (item) {
            if (item != null){
                arrayTemp.push(item)
            }
        })
        arrayCarrito=arrayTemp;
        mostrarCarrito();
    }
//genera un elemento html del tipo dado y con la clase dada
    function crearElementoHTML(tipo, clase) {
        let elemento = document.createElement(tipo);
        elemento.className = clase;
        return elemento;
    }
//crea dinamicamente una linea de carro por cada elemento que hay en el array y la rellena, tambien le añade los botones de + - y eliminar
    var mostrarCarrito = function () {
        $("cuerpo").innerHTML = STORE.ProductTemplate.carrito;
        let total = 0;
        let vacio = true;
        arrayCarrito.forEach(function (item) {
            let subtotal = item.cantidadPedida * item.actualPrecioModelo;
            const elementosLineaCarroText = [item.IdModelo, item.cantidadPedida, item.actualPrecioModelo, subtotal.toString(), "+", "-", "eliminar"];
            const lineaCarrito = crearElementoHTML("div", "etiqueta s11");
            for (let i = 0; i < elementosLineaCarroText.length; i++) {
                let elemento = crearElementoHTML("span", "etiqueta s11");
                lineaCarrito.appendChild(elemento);
                elemento.innerText = elementosLineaCarroText[i]
                if (i == 4) {
                    elemento.addEventListener("click", function () {
                        addUnidadLineaCarrito(item)
                    });
                } else if (i == 5) {
                    elemento.addEventListener("click", function () {
                        quitarUnidadLineaCarrito(item)
                    });
                } else if (i == 6) {
                    elemento.addEventListener("click", function () {
                        borrarLineaCarrito(item.posicionArray)
                    });
                }
            }
            total += subtotal;
            $("lineasCarrito").appendChild(lineaCarrito)
            vacio = false;
        });
        if (vacio) {
            let lineaVacia = crearElementoHTML("div","etiqueta s4 contenedorFila");
            lineaVacia.innerText = "No hay productos en el carro";
            $("lineasCarrito").appendChild(lineaVacia)
        }else{
            $("totalCarrito").innerText ="Total: "+total;
            $("guardarCarrito").addEventListener("click", guardarCarrito);
            $("comprarCarrito").addEventListener("click", comprarCarrito);
        }
    }
	//guarda el carro en la bbdd meidante ajax
    var guardarCarrito = function () {
            let json = JSON.stringify(arrayCarrito);
            llamada = new ajax.CargadorContenidos("/guardarCarrito", function () {
                JSON.parse(llamada.req.responseText);
                console.log("Carro guardado")
                sessionStorage.setItem("timeCarrito", Date.now().toPrecision());
            },json);
    }

	//envia la accion de comprar el carrito actual y reinicia el carro actual 
    var comprarCarrito = function () {
        guardarCarrito();
        llamada = new ajax.CargadorContenidos("/comprarCarrito", function () {
            JSON.parse(llamada.req.responseText);
            console.log("Carro guardado")
            sessionStorage.setItem("timeCarrito", Date.now().toPrecision());
        });
        sessionStorage.setItem("facturasGuardadas","false");
        arrayCarrito = [];
        cargarCarrito(arrayCarrito);
        mostrarCarrito();
    }

	//trae las facturas de la bbdd y muestrea las cabeceras, al clickar se mostrarian enteras
    var sacarFacturas = function () {
        return new Promise((resolve)=>{
            if(sessionStorage.getItem("facturasGuardadas")==="false") {
                llamada = new ajax.CargadorContenidos("/getFacturas", function () {
                    facturas = JSON.parse(llamada.req.responseText);
                    if (facturas.length === 0) {
                        alert("No tienes facturas");
                    } else {
                        console.log(facturas.length + " facturas")
                    }facturas.forEach(function (factura) {;
                        factura.lineas = JSON.parse(factura.lineas);
                    })
                    sessionStorage.setItem("facturasGuardadas","true");
                    resolve()
                });
            }else{
                resolve()
            }
        })
    }
	//cargaria las cabeceras de las facturas
    var verFacturas = function(){
        console.log("asd")
        sacarFacturas().then(()=>{
            $("cuerpo").innerHTML = "";
            facturas.forEach(function (factura) {
                var elementoFactura = crearElementoHTML("div", "etiqueta s4 contenedorFila");
                elementoFactura.innerText = factura.fecha;
                $("cuerpo").appendChild(elementoFactura);
                elementoFactura.addEventListener("click", function () {
                    mostrarFactura(factura)
                });
            })
        })

    }
	// muestra la factura seleccionada 
    var mostrarFactura = function(factura){
        console.log(factura)
        $("cuerpo").innerHTML = STORE.ProductTemplate.verFactura;
        let totalFactura =0;
        $("nombreEmpresa").innerText = factura.nombreEmpresaFactura;
        $("domicilioEmpresa").innerText = factura.domicilioEmpresaFactura;
        $("cif").innerText = factura.cifEmpresaFactura;
        $("domicilioCliente").innerText = factura.domicilioClienteFactura;
        $("nombreCliente").innerText = factura.nombreClienteFactura;
        $("nif").innerText = factura.dniClienteFactura;
        $("fechaFactura").innerText = factura.fecha;
        factura.lineas.forEach(function (linea) {
            var contenedor = crearElementoHTML("div", "contenedorFila s7");
            var idModelo = crearElementoHTML("span", "etiqueta s7");
            idModelo.innerText = linea.idModelo;
            contenedor.appendChild(idModelo);
            var nombreModelo = crearElementoHTML("span", "etiqueta s7");
            nombreModelo.innerText = linea.nombreModelo;
            contenedor.appendChild(nombreModelo);
            var cantidadComprada = crearElementoHTML("span", "etiqueta s7");
            cantidadComprada.innerText = linea.cantidadComprada;
            contenedor.appendChild(cantidadComprada);
            var precioModelo = crearElementoHTML("span", "etiqueta s7");
            precioModelo.innerText = linea.precioModelo;
            contenedor.appendChild(precioModelo)
            var total = crearElementoHTML("span", "etiqueta s7");
            let subtotal = parseInt(linea.precioModelo) * parseInt(linea.cantidadComprada);
            total.innerText = subtotal;
            contenedor.appendChild(total);
            totalFactura += subtotal;
            $("facturaDetalles").appendChild(contenedor);
        })
            $("subtotal").innerText = "TOTAL BRUTO: "+totalFactura;
            $("ivaDato").innerText = "I.V.A. "+factura.iva;
            $("total").innerText = "TOTAL NETO "+totalFactura * ((100+parseInt(factura.iva))/100);
    }
    $("op_verFacturas").addEventListener("click", verFacturas);
    $("op_verCarrito").addEventListener("click", mostrarCarrito);

}());
STORE.namespace('STORE.ProductTemplate.carrusel');
STORE.ProductTemplate.carrusel = "<contendorProductos>" +
    "   <div id='espacio'>" +
    "      <div id='giran' class= 'medio'>" +
    "       </div>" +
    "     </div>" +
    "  <div class='contenido01'> <div><a id='carruselmasRapido'> mas rapido </a></div> <div><a id='carruselmenosRapido'> menos </a></div></div>" +
    "  <div class='contenido01'> <div><a id='carruselmas'> siguiente </a></div><div id='contenedorPaginaNumeros' class = 'contenedorFila'></div> <div><a id='carruselmenos'> anterior </a></div></div>" +
    "</contendorProductos>" +
    "<div id='productoDetalle'></div>";

STORE.namespace('STORE.ProductTemplate.verProducto');
STORE.ProductTemplate.verProducto = "<div class='contenido01'>" +
    "           <div class='menu s3 caja03'>" +
    "                <h4>Movil</h4>" +
    "                <p hidden id='IdModelo'></p>" +
    "                <div class='cuadrosDeTexto s17'> " +
    "                       <span>Modelo:</span>" +
    "                       <p id='nombreModelo'> </p> " +
    "                 </div>" +
    "                <div class='cuadrosDeTexto s17'> " +
    "                       <p id='descripcionModelo'> </p> " +
    "                 </div>" +
    "                <div class='cuadrosDeTexto s17'> " +
    "                       <span>Precio: </span>" +
    "                       <p id='actualPrecioModelo'> </p> " +
    "                 </div>" +
    "                <div class='cuadrosDeTexto s17'> " +
    "                       <span>Stock: </span>" +
    "                       <p id='stockActualModelo'> </p> " +
    "                 </div>" +
    "            <button id='addCarrito' >Comprar</button>" +
    "</div>" +
    "      </div>" +
    "           </div>";
STORE.ProductTemplate.carrito = "" +
    "<div class='menu s7'>" +
    "   <h4>Carrito</h4>" +
    "       <div class='etiqueta s7 contenedorFila25'>" +
    "           <span class='etiqueta s7'>Model</span>" +
    "           <span class='etiqueta s7'>Unidades</span>" +
    "           <span class='etiqueta s7'>Precio</span>" +
    "           <span class='etiqueta s7'>Operacion</span>" +
    "       </div>"+
    "<div id='lineasCarrito' class = 'contenedorColumna s8'></div>"+
    "<span id='totalCarrito' class='etiqueta s1'></span>"+
    "<button id='guardarCarrito' >Guardar</button>" +
    "<button id='comprarCarrito' >Comprar</button>" +
    "</div>";


STORE.namespace('STORE.ProductTemplate.verFactura');
STORE.ProductTemplate.verFactura = "" +
    "<div class='contenedorColumna s7'>" +
    "   <div class='contenedorFila s7'>" +
    "       <div class='contenedorColumna s7'>" +
    "           <span id = 'fechaFactura' class='etiqueta s7'></span>" +
    "       <h4>Datos empresa</h4>" +
    "           <span id = 'nombreEmpresa' class='etiqueta s7'></span>" +
    "           <span id = 'domicilioEmpresa' class='etiqueta s7'></span>" +
    "           <span id = 'cif' class='etiqueta s7'></span>" +
    "       </div>" +
    "       <div class='contenedorColumna s7'>" +
    "       <h4>Datos Cliente</h4>" +
    "           <span id = 'nombreCliente' class='etiqueta s7'></span>" +
    "           <span id = 'domicilioCliente' class='etiqueta s7'></span>" +
    "           <span id = 'nif' class='etiqueta s7'></span>" +
    "       </div>" +
    "   </div>" +
    "   <div id = 'fechaFactura' class='contenedorFila s7'>" +
    "   </div>" +
    "   <div id = 'iva' class='contenedorFila s7'>" +
    "   </div>" +
    "   <div id='lineasFactura' class='contenedorColumna s7'>" +
    "<div class='contenedorColumna s7'>" +
    "   <div class='contenedorFila s7'>" +
    "        <span id = 'idModelo' class='etiqueta s7'>ID</span>" +
    "        <span id = 'nombreModelo' class='etiqueta s7'>Nombre Modelo</span>" +
    "        <span id = 'cantidadComprada' class='etiqueta s7'>Cantidad Comprada</span>" +
    "        <span id = 'precioModelo' class='etiqueta s7'>Precio Unitario</span>" +
    "        <span id = 'totalModelo' class='etiqueta s7'>Precio Total</span>" +
    "   </div>" +
    "   <div id= 'facturaDetalles' class='contenedorColumna s7'>" +
    "   </div>" +
    "</div>"+
    "   <div class='contenedorColumna s7'>" +
    "        <span id = 'subtotal' class='etiqueta s7'>subtotal</span>" +
    "        <span id = 'ivaDato' class='etiqueta s7'>iva</span>" +
    "        <span id = 'total' class='etiqueta s7'>total</span>" +
    "   </div>" +
    "</div>";

var id = sessionStorage.getItem("idClient");
if(typeof id === "undefined" || id == null){
    id = '../../fotoSin.png';
}
var dados = ['dado informatica','dado harnina','dadoCliente'];

var imagenes = ['../../img/escudo02.png','../../img/harni01.png', '../../img/fotoClient/'+ id + '.png?Math.random()'];
var clases = ['cara frontal','cara trasera','cara derecha','cara izquierda','cara arriba','cara abajo'];

//crea y retorna un elemento imagen con la ruta que le entra
var creaImagen = function(imag){
    var imagen = document.createElement("img");
    imagen.setAttribute('src', imag);
    imagen.className = 'Imagen';
    return imagen;
}

//crea la cara en el dado con la imagen que entra
var creaCara = function(dado, n, imagen){
    for (var i = 0; i < clases.length; i++){
        var cara = document.createElement('div');
        cara.className = clases[i];
        cara.appendChild(creaImagen(imagen));
        dado[n].appendChild(cara);
    }
}
for (var i = 0; i <= dados.length; i++) {
    var dado = document.getElementsByClassName(dados[i]);
    for (var j = 0; j < dado.length; j++){
        creaCara(dado, j, imagenes[i]);
    }
}

//id es la id del cliente actual
//dados son los nombres de los dados a mostrar (editable)
//imagenes son las imagenes que tendran los dados (editable)
//clases son las caras de los dados

//el for recorre el dado poniendole la imagen correspondiente en cada cara
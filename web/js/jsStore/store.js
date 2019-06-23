/**
 * Created by Luciano on 19/01/2019.
 */

function $(id) { return document.getElementById(id); };

var STORE = STORE || {};
// retorna el modulo del namespace especificado 
STORE.namespace = function(ns_string){
    var parts = ns_string.split('.');
    var parent = STORE;
    var i;
    if (parts[0] === "STORE") {
        parts = parts.slice(1);
    }
    for (i = 0; i < parts.length; i += 1) {

        if (typeof parent[parts[i]] === "undefined") {

            parent[parts[i]] = {};
        }
        parent = parent[parts[i]];
    }

    return parent;

};

// retorna el rango de valores limites de un nodo
STORE.getLimite = function(nodo){

    var limite = {};

    if(nodo.minLength){

        var limiteInferior = nodo.minLength;

        var limiteSuperior = nodo.maxLength;

        limite.limiteInferior = limiteInferior;

        limite.limiteSuperior = limiteSuperior;
    }

    return limite;

};
// comprueba una condicion y retorna un mensaje de error si la condicion no se cumple
STORE.assert = function(condition, message) {
    if (!condition) {
        message = message || "Assertion failed";
        if (typeof Error !== "undefined") {
            throw new Error(message);
        }
        throw message; // Fallback
    }
}
//introduce en un objeto un metodo delegado de otra clase
function funcDelegate(obj, methodName) {
    // {1}
    return function (e) {
        e = e || window.event;
        //alert(obj);
        return obj[methodName](this, e); // {2}
    };
}

//añade al elemento con el id dado, los eventos de mouse
STORE.DOMObjectLook= function(id) {

    this._element = document.getElementById(id);
    if (this._element) {
        this._element.onmouseover = funcDelegate( this, "customOnMouseOver");
        this._element.onmouseout = funcDelegate( this, "customOnMouseOut");
    }
}
//añade al cursos color y forma para cuando el rato se ponga encima de un elemento con el evento
STORE.DOMObjectLook.prototype.customOnMouseOver = function (obj, event) {
    obj.style.cursor = "help";
    obj.style.color = "red";
};

//añade al cursos color y forma para cuando el rato se salga de un elemento con el evento
STORE.DOMObjectLook.prototype.customOnMouseOut = function (obj, event) {
    obj.style.cursor = "pointer";
    obj.style.color = "blue";
};

STORE.namespace('DAM.Ventana');

//retorna el objeto API, que calcula las dimensiones de la ventana y el navegador en uso
STORE.Ventana = (function () {
    'use strict';
    var API = {};

    API.Calcular = function () {
        var dimension = [2];
        if( typeof( window.innerWidth ) == 'number' ) {
            //No-IE
            dimension[0] = window.innerWidth;
            dimension[1] = window.innerHeight;

        } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
            //IE 6+
            dimension[0] = document.documentElement.clientWidth;
            dimension[1] = document.documentElement.clientHeight;

        } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
            //IE 4 compatible
            dimension[0] = document.body.clientWidth;
            dimension[1] = document.body.clientHeight;
        }
        API.Ancho = dimension[0];
        API.Alto = dimension[1];

    };
    API.Navegador = function(){

        var ie = navigator.userAgent.toLowerCase().indexOf('msie') != -1;
        console.log(navigator.userAgent);

        if(ie) return true;
        else return false;
    };

    return API;

}());
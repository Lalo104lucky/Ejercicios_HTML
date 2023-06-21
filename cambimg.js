const boton_anterior = document.getElementById('boton_anterior');
const boton_siguiente = document.getElementById('boton_siguiente');
const gale_imag = document.getElementById('gale_imag');
const imagen = [
    './Imágenes/michi ingeniero de sistemas.jpg',
    './Imágenes/michi pizzero.jpg',
    './Imágenes/michi ingeniero.jpg',
    './Imágenes/michi maestro.jpg',
    './Imágenes/michi programador web.jpg'
];

let Imagen_Index = 0;

function cambiar_imagen(index) {
    gale_imag.src = imagen[index];
    gale_imag.alt = 'imagen ${index + 1}';
}

cambiar_imagen(Imagen_Index);

boton_siguiente.addEventListener('click', ()=> {
    Imagen_Index++;
    if(Imagen_Index === imagen.length) {
        Imagen_Index = 0;
    }
    cambiar_imagen(Imagen_Index);
})

boton_anterior.addEventListener('click', ()=> {
    Imagen_Index--;
    if(Imagen_Index < 0) {
        Imagen_Index = imagen.length - 1;
    }
    cambiar_imagen(Imagen_Index);
})
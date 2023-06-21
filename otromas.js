window.addEventListener('DOMContentLoaded', function() {
var imagenes = ["img1", "img2", "img3", "img4", "img5"];
var currentIndex = 0;

function verImagen() {
  var currentImage = document.getElementById(imagenes[currentIndex]);
  var nextIndex = (currentIndex + 1) % imagenes.length;
  var nextImage = document.getElementById(imagenes[nextIndex]);

  currentImage.classList.remove('transicion');
  nextImage.classList.add('transicion');

  currentIndex = nextIndex;
}

setInterval(verImagen, 3000);

verImagen();
});
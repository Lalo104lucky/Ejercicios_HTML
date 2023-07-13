const encenderButton = document.getElementById('encender');
const inclinacionButton = document.getElementById('inclinacion');
const velocidadButton = document.getElementById('velocidad');
const focoEncendido = document.getElementById('foco-encendido');
const focosInclinacion = document.getElementById('focos-inclinacion').querySelectorAll('.foco');
const focosVelocidad = document.getElementById('focos-velocidad').querySelectorAll('.foco');

encenderButton.addEventListener('click', () => {
  encenderButton.classList.toggle('apagado');
  encenderButton.classList.toggle('encendido');

  if (encenderButton.classList.contains('encendido')) {
    focoEncendido.classList.add('encendido');
    encenderPrimerFoco();
  } else {
    focoEncendido.classList.remove('encendido');
    apagarFocos(focosInclinacion);
    apagarFocos(focosVelocidad);
  }
});

inclinacionButton.addEventListener('click', () => {
  if (encenderButton.classList.contains('encendido')) {
    encenderSiguienteFocoCircular(focosInclinacion);
  }
});

velocidadButton.addEventListener('click', () => {
  if (encenderButton.classList.contains('encendido')) {
    encenderSiguienteFocoCircular(focosVelocidad);
  }
});

function encenderPrimerFoco() {
  focosInclinacion[0].classList.add('encendido');
  focosVelocidad[0].classList.add('encendido');
}

function encenderSiguienteFocoCircular(focos) {
  const focoActual = Array.from(focos).findIndex((foco) => foco.classList.contains('encendido'));

  if (focoActual !== -1) {
    focos[focoActual].classList.remove('encendido');
  }

  const siguienteFoco = (focoActual + 1) % focos.length;
  focos[siguienteFoco].classList.add('encendido');
}

function apagarFocos(focos) {
  for (let i = 0; i < focos.length; i++) {
    focos[i].classList.remove('encendido');
  }
}

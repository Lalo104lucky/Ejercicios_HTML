console.log("Hola mijs.js")

function declararVar() {
    var materia = "WEB";
    if (true) {
        var materia = "BD";
        console.log(materia);
    }
    console.log(materia)
}

function declararLet() {
    let materia = "WEB";
    if (true) {
        let materia = "BD";
        console.log(materia);
    }
    console.log(materia)
}

function declararConst() {
    const materia = "WEB";
    if (true) {
        const materia = "BD";
        console.log(materia);
    }
    console.log(materia)
}

function declararObj() {
    const persona = {
        nombre:"Eduardo",
        apellido:"Jaimez",
        edad:19,
        sexo:"H",
        direccion:{
            calle:"Av. Las Águilas",
            numero:208,
            municipio:"Cuernavaca"
        }
    }
    console.log(persona);
    console.log(persona.nombre);
    persona.nombre="Diego";
    console.log(persona);
    console.log(persona.direccion.municipio);
    console.log("Hola "+persona.nombre+" tienes "+persona.edad+ " y vives en "+ persona.direccion.municipio);

    const {nombre, 
        apellido, 
        edad, 
        sexo,
        direccion: {calle, numero, municipio},
    } = persona;
    //const {calle, numero, municipio} = direccion;
    console.log(municipio);
    console.log(`Hola ${nombre} tienes ${edad} \ny vives en ${municipio}`)

    let numeros = [1,2,3,4,5]
    console.log(numeros);
    console.log(...numeros)

    function sumar (a,b,c) {
        console.log("La suma es " + (a+b+c))
    }
    sumar(3,4,5);
    sumar(...numeros);

    let numero2 = [...numeros,4,5,6]
    console.log(numero2);
    
}
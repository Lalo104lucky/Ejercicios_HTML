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

function tradicional() {
    console.log("Hola Tradicional")
}
tradicional();

let tradicional2 = ()=> {
    console.log("Hola Tradicional2");
    console.log("Adios Tradicional2");
}
    tradicional2();

function x(a,b,d){
    console.log(a+b+c);
}

let x2=(a,b,d)=> console.log(a+b+d);

///////////////////////////ASYNC/////////////////////////

const peliculas = [
    {
        id:1,
        nombre:"Yo antes de ti",
        duracion:120,
        genero:"Romantica",
        descripcion:"Esta en silla de suellas"
    },
    {
        id:2,
        nombre:"shrek",
        duracion:120,
        genero:"Animada",
        descripcion:"Tiene capas :("
    },
    {
        id:3,
        nombre:"Moana",
        duracion:90,
        genero:"Animada",
        descripcion:"Hay un gallo loco"
    },
    {
        id:4,
        nombre:"Harry Potter",
        duracion:150,
        genero:"Fantasia",
        descripcion:"Le matarona sus jefes"
    }
]

const obtenerPeliculas = ()=> {
    return new Promise((resolve, reject)=>{
        setTimeout(()=> {
            resolve(peliculas);
        }, 3000);
    });
};

//console.log(obtenerPeliculas());
//obtenerPeliculas().then((peliculas)=>console.log(peliculas))

async function listarPelis() {
    const lista =  await obtenerPeliculas();
    console.log(lista);
}

listarPelis(); 
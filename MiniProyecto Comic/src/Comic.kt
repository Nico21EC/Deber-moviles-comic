import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


class Comic(var nombreComic: String, val vigencia: Boolean, val pags: Int, val precio: Double) {
    }

class comicControl() {

    var ficheroComic = File("archivoComic.txt")
    var arrComics: ArrayList<Comic> = ArrayList<Comic>()
    var objeto: Comic? = null
    val sup = superheroeControl()
    //val mix = comicSuperheroe()
    //val arrComic: ArrayList<Comic> = ArrayList<Comic>()
    //val comic =   Comic( nombre, vigencia,  pags,  precio)

///*******************FUNCIONES LISTAS PARA LLAMAR EN EL MENÚ
    ////////////////////********************* CRUD
    //////// CREATE
    fun createComic() {
        println("************ CÓMICS *****************")
        println("    Ingresa el nombre del CÓMIC      ")
        val nombreCom = readLine()!!.toString()
        println("   Ingresa la vigencia del CÓMIC      ")
        val vigencia = readLine()!!.toBoolean()
        println("    Ingresa el #páginas del CÓMIC     ")
        val pags = readLine()!!.toInt()
        println("    Ingresa el precio del CÓMIC      ")
        val precioCom = readLine()!!.toDouble()
        InsertarComic(nombreCom, vigencia, pags, precioCom)
    }

    ////////////////READ
    fun readComics() {
        if (arrComics.size == 0) {
            println(arrComics.size)
            deTxtAObjeto()
        }
        println("=========================== CÓMICS========================================================================")
        for (n in arrComics) {
            println("El nombre es:" + n.nombreComic + "\t" + "La vigencia es:" + n.vigencia + "\t" +
                    "El número de páginas es:" + n.pags + "\t" + "El precio es:" + n.precio)
        }
        println("============================YEAIH=:D======================================================================")
    }

    //UPDATE
    fun updateComic() {
        println("Ingrese el nombre del comic que desea actualizar el nombre")
        val nameComic1 = readLine().toString()
        println("Ingrese el nuevo nombre del Cómic")
        val nuevoNameComic1 = readLine().toString()
        modificarNombreComic(nameComic1, nuevoNameComic1)
    }

    // DELETE
    fun deleteComic() {
        println("Ingrese el nombre del cómic que desea eliminar")
        val nombreCom = readLine().toString()
        println("Esta seguro de eliminar el cómic"+nombreCom+"? \n Si lo elimina se eliminaran " +
                "los superheroes que luchan en el :( \n Ingrese 1 para seguir y 0 para cancelar la eliminación")
        val opc = readLine()!!.toInt()
        if (opc == 1){
        eliminarComic(nombreCom)
        }
    }

    //*********************************************************************************************
    //OPERACIONES NO TAN LIMPIAS AHHAHAHAH..... Estoy harta hhahhha
    //*********************************************************************************************

    fun InsertarComic(nombre: String, vigencia: Boolean, pags: Int, precio: Double) {
        try {
            val Fescribe = BufferedWriter(OutputStreamWriter(FileOutputStream(ficheroComic, true), "utf-8"))
            Fescribe.write(nombre + "\t" + vigencia.toString() + "\t" + pags.toString() + "\t" + precio.toString() + "\r\n")
            println("El comic ha sido insertado")
            Fescribe.close()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun deTxtAObjeto() {
        //Transforma el txt que consta en la carpeta de archivos a un objeto
        try {
            var linea: String? = null
            val leerFichero = BufferedReader(FileReader(ficheroComic))
            while (leerFichero.readLine().also { linea = it } != null) {
                val mistokens = StringTokenizer(linea, "\t")
                val nombre = mistokens.nextToken().trim { it <= ' ' }
                val vigencia = mistokens.nextToken().trim { it <= ' ' }
                val paginas = mistokens.nextToken().trim { it <= ' ' }
                val precio = mistokens.nextToken().trim { it <= ' ' }

                val vig = vigencia.toBoolean()
                val pags = paginas.toInt()
                val price = precio.toDouble()
                //lo paso al constructor para que me cree los objetos
                objeto = Comic(nombre, vig, pags, price)
                //lo añado al vecto para poder jugetear con el
                objeto?.let { arrComics.add(it) }
            }
            leerFichero.close()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun comprobarBd() {
        try {
            if (!ficheroComic.exists()) {
                ficheroComic.createNewFile()
                println("Base de datos de productos creada se insertara el comic")
            } else {
                println("La base de datos de productos existe")
            }
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun checkArrComic(): Boolean {
        if (arrComics.size == 0) {
            //println(arrComics.size)
            deTxtAObjeto()
            return true
        }
        return false
    }

    fun ActualizarArraList() {
        arrComics.clear()
        deTxtAObjeto()
    }

    fun modificarNombreComic(nomComic: String, nuevoNomComic: String) {
        if (arrComics.size == 0) {
            deTxtAObjeto()
        }
        for (n in arrComics) {
            if (n.nombreComic.equals(nomComic)) {
                n.nombreComic = nuevoNomComic
                println("=========================== COMIC==================================================================================================")
                println("El nombre es:" + n.nombreComic + "\t" + "La vigencia es:" + n.vigencia + "\t" + "El número de páginas es:" + n.pags + "\t" + "El precio es:" + n.precio)
                println("============================FIN====================================================================================================")
                val bw = BufferedWriter(FileWriter(ficheroComic))
                bw.write(n.nombreComic + "\t" + n.vigencia + "\t" + n.pags + "\t" + n.precio + "\r\n")
                bw.close()
            } else {
                println("El cómic no ha sido encontrado")
            }
        }
    }

    fun eliminarComic(nombreCom: String) {
        try {
            val bw = BufferedWriter(FileWriter(ficheroComic))
            for (n in arrComics) {
                /*if (n.nombre !== nombreCom) {
                    bw.write(n.nombre + "\t" + n.vigencia + "\t" + n.pags + "\t" + n.precio + "\r\n")
                } else {
                    println("El cómic ha sido eliminado")
                }*/
                if (n.nombreComic.equals(nombreCom)) {
                    println("")
                    //CUIDADO CON ESTA FUNCION NO SE SI ES AQUI
                    sup.eliminarSuperheroeComic(nombreCom)
                } else {
                    bw.write(n.nombreComic + "\t" + n.vigencia + "\t" + n.pags + "\t" + n.precio + "\r\n")
                    println("El cómic ha sido eliminado")
                }
            }
            bw.close()
            arrComics.clear()
            deTxtAObjeto()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }


    ////////////*************************MENUS Y WHENS HAHAHA AGHHHH ya matenme... :( tengo prueba de BI en 30 min ahhahah
    fun menu() {
        println("--------********    COMIC MENU  ********-------");
        println("                                               ")
        println("1.          CREATE A NEW COMIC                 ")
        println("2.          READ ALL COMICS                    ");
        println("3.          UPDATE NAME COMIC                  ");
        println("4.          DELETE A COMIC                     ");
        println("5.          RETURN TO MAIN MENU                ")
        println("0.          EXIT                               ");
    }

    fun comicMenuOptions() {
        println("***********   BIENVENIDO AMANTE DEL CÓMIC... ahhaha tengo sueño *************")
        //menu()
        var opcion: Int = 1
        while (opcion != 0) {
            menu()
            println("*****************Ingrese el número de la opción deseada************")
            opcion = readLine()?.toInt() as Int
            when (opcion) {
                1 -> createComic()
                2 -> readComics()
                3 -> updateComic()
                4 -> deleteComic()
                //5 -> mix.menuPrincipal()
                0 -> System.exit(0)

                /*2 -> {
                         println("Inserte la descripcion del producto y la  nueva descripcion del mismo")
                         val cadena = en.next()
                         val cadenaNueva = en.next()
                         for (n in cositas) {
                             if (n.getDescripcion().equals(cadena)) {
                                 n.descripcion = cadenaNueva
                                 println("=========================== Articulo=========================================================================================================================================================================================")
                                 System.out.println("El id es:" + n.getId().toString() + "\t" + "La descripcion es:" + n.getDescripcion().toString() + "\t" + "El precio es:" + n.getPrecio().toString() + "\t" + "La cantaidad es:" + n.getCantidad())
                                 println("============================FIN==============================================================================================================================================================================================")
                                 break
                             } else {
                                 println("el producto no ha sido encontrado")
                             }
                         }
                     }
                     3 -> {
                         println("Inserte la descripcion del producto y el precio nuevo")
                         cadena = en.next()
                         val precioNuevo = en.nextDouble()
                         for (n in cositas) {
                             if (n.getDescripcion().equals(cadena)) {
                                 n.precio = precioNuevo
                                 println("=========================== Articulo=========================================================================================================================================================================================")
                                 System.out.println("El id es:" + n.getId().toString() + "\t" + "La descripcion es:" + n.getDescripcion().toString() + "\t" + "El precio es:" + n.getPrecio().toString() + "\t" + "La cantaidad es:" + n.getCantidad())
                                 println("============================FIN==============================================================================================================================================================================================")
                                 break
                             } else {
                                 println("el producto no ha sido encontrado")
                             }
                         }
                     }
                     4 -> {
                         println("Inserte la descripcion y la cantidad nueva ")
                         cadena = en.next()
                         numero = en.nextInt()
                         for (n in cositas) {
                             if (n.getDescripcion().equals(cadena)) {
                                 n.cantidad = numero
                                 println("=========================== Articulo=========================================================================================================================================================================================")
                                 System.out.println("El id es:" + n.getId().toString() + "\t" + "La descripcion es:" + n.getDescripcion().toString() + "\t" + "El precio es:" + n.getPrecio().toString() + "\t" + "La cantaidad es:" + n.getCantidad())
                                 println("============================FIN==============================================================================================================================================================================================")
                                 break
                             } else {
                                 println("el producto no ha sido encontrado")
                             }
                         }
                     }*/
                /* 5 -> {
                         println("Guardar")
                         try {
                             val bw = BufferedWriter(FileWriter(FicheroProducto))
                             for (n in cositas) {
                                 bw.write(n.nombre + "\t" + n.vigencia + "\t" + n.pags + "\t" + n.precio + "\r\n")
                             }
                             bw.close()
                         } catch (ex: Exception) {
                             //Captura un posible error le imprime en pantalla
                             println(ex.message)
                         }
                     }*/
            }
        }
    }

    //**************FUNCIONES QUE NO ME SIRVIERON Y ME ESTRESARON MAS.... 77
    fun buscarComicNombre() {

        var vec: Array<String> = arrayOf()
        try {
            //Pasar las lineas del archivo a un arrayList
            val lines = ArrayList(Files.readAllLines(Paths.get("C:\\Users\\USER\\Documents\\EPN\\VIISEMESTRE\\Moviles\\MiniProyecto Comic\\archivoComic.txt")))
            for ((posicion, valor) in lines.withIndex()) {
                vec = lines[posicion].split(",").toTypedArray()
                println(vec)
            }
        } catch (e: IOException) {
        }

    }

    fun writeFile1(str: String) {
        try {
            val openFile = File("archivoComic.txt")
            openFile.appendText(str + "\n")
        } catch (ex: Exception) {
            println(ex.message)
        }
    }


}



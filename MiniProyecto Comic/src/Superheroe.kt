import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class Superheroe(var nameSuperheroe: String, var single: Boolean, var streghtForceLevel: Double, var age: Int, var comicName: String) {

}

class superheroeControl(){

    var FicheroSuperheroe = File("archivoSuperheroe.txt")
    var arrSuperHero: ArrayList<Superheroe> = ArrayList<Superheroe>()
    var objeto: Superheroe? = null

    var ficheroComic = File("archivoComic.txt")
    val arrComic: ArrayList<Comic> = ArrayList<Comic>()
    var objeto2: Comic? = null

    //val mix = comicSuperheroe()
    // **********************************************FUNCIONES LISTAS CRUD PARA MENU         :( YA ME CANSE
    ////////////////////********************* CRUD
    //////// CREATE
    fun createSuperhero() {
        println("************       SUPERHEROE !!!! *****************")
        println("          Como se llama el SUPERHEROE??????         ")
        val nombreSuperh = readLine()!!.toString()
        println("           El SUPERHEROE es soltero?                ")
        val single = readLine()!!.toBoolean()
        println("           Que nivel de fuerza tiene?               ")
        val streght = readLine()!!.toDouble()
        println("           Cuántos años tiene?                      ")
        val ageS = readLine()!!.toInt()
        println("           A que cómic pertenece?                   ")
        val comic = readLine()!!.toString()
        insertarSuperheroe(nombreSuperh, single, streght, ageS, comic)
    }

    ////// READ
    fun readSupeheroes() {
        if (arrSuperHero.size == 0) {
            println(arrSuperHero.size)
            deTxtAObjeto()
        }
        println("===========================             SUPERHEROES          =========================================================")
        for (n in arrSuperHero) {
            println("El nombre es:" + n.nameSuperheroe + "\n" + "Esta soltero?" + n.single + "\n" +
                    "Su nivel de fuerza es:" + n.streghtForceLevel + "\n" + "Su edad es:" + n.age + "\n" + "Pertenece al comic:" + n.comicName)
        }
        println("============================              YEAIH=:D            =========================================================")
    }

    ///// UPDATE
    fun updateSuperheroe() {
        println("Ingrese el nombre del superheroe que desea actualizar el nombre")
        val nameSuperheroe1 = readLine().toString()
        println("Ingrese el nuevo nombre del superheroe")
        val nuevoNameSuperheroe1 = readLine().toString()
        modificarNombreSuperheroe(nameSuperheroe1, nuevoNameSuperheroe1)
    }

    ////DELETE
    fun deleteSuperheroe() {
        println("Ingrese el nombre del SUPERHEROE que desea eliminar")
        val nombreSup = readLine().toString()
        eliminarSuperheroe(nombreSup)
    }

    //FUNCIONES NO LISTAS PARA EL MENU
    fun insertarSuperheroe(nameSuperheroe: String, single: Boolean, streghtForceLevel: Double, age: Int, comicName: String) {
        deTxtAObjeto2()
        for (n in arrComic){
            if (comicName.equals(n.nombreComic)){
                try {
                    println("Ingresa el if")
                    val Fescribe = BufferedWriter(OutputStreamWriter(FileOutputStream(FicheroSuperheroe, true), "utf-8"))
                    Fescribe.write(nameSuperheroe + "\t" + single.toString() + "\t" + streghtForceLevel.toString() + "\t" + age.toString() + "\t" + comicName.toString() + "\r\n")
                    println("El SUPERHEROE ha sido insertado")
                    Fescribe.close()
                } catch (ex: Exception) {
                    println(ex.message)
                }
            }else
            {
                println("No se puede insertar debido a que no existe dicho Comic")
            }
        }
            }

    fun modificarNombreSuperheroe(nomSuperheroe: String, nuevoNomSuperheroe: String) {
        if (arrSuperHero.size == 0) {
            deTxtAObjeto()
        }
        for (n in arrSuperHero) {
            if (n.nameSuperheroe.equals(nomSuperheroe)) {
                n.nameSuperheroe = nuevoNomSuperheroe
                println("==========================                     SUPERHEROES              ===========================================================")
                println("El nombre es:" + n.nameSuperheroe +
                        "\n" + "Esta soltero?" + n.single + "\n" +
                        "Su nivel de fuerza es:" + n.streghtForceLevel + "\n" +
                        "Su edad es:" + n.age + "\n" +
                        "Pertenece al comic:" + n.comicName + "\n")
                println("======================                             FIN                  ===========================================================")
                val bw = BufferedWriter(FileWriter(FicheroSuperheroe))
                bw.write(n.nameSuperheroe + "\t" + n.single + "\t" + n.streghtForceLevel + "\t" + n.age + "\t" + n.comicName + "\r\n")
                bw.close()
            } else {
                println("El superheroe no ha sido encontrado")
            }
        }
    }

    fun eliminarSuperheroe(nombreSup: String) {
        try {
            val bw = BufferedWriter(FileWriter(FicheroSuperheroe))
            for (n in arrSuperHero) {
                if (n.nameSuperheroe.equals(nombreSup)) {
                    println("")
                } else {
                    bw.write(n.nameSuperheroe + "\t" + n.single.toString() + "\t" +
                            n.streghtForceLevel.toString() + "\t" + n.age.toString() + "\t" + n.comicName.toString() + "\r\n")
                    println("El superheroe ha sido eliminado")
                }
            }
            bw.close()
            arrSuperHero.clear()
            deTxtAObjeto()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun eliminarSuperheroeComic(nombreComic: String) {
        try {
            val bw = BufferedWriter(FileWriter(FicheroSuperheroe))
            for (n in arrSuperHero) {
                if (n.comicName.equals(nombreComic)) {
                    println("")
                } else {
                    bw.write(n.nameSuperheroe + "\t" + n.single.toString() + "\t" +
                            n.streghtForceLevel.toString() + "\t" + n.age.toString() + "\t" + n.comicName.toString() + "\r\n")
                    println("El superheroe ha sido eliminado")
                }
            }
            bw.close()
            arrSuperHero.clear()
            deTxtAObjeto()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun deTxtAObjeto() {
        //Transforma el txt que consta en la carpeta de archivos a un objeto
        try {
            var linea: String? = null
            val leerFichero = BufferedReader(FileReader(FicheroSuperheroe))
            while (leerFichero.readLine().also { linea = it } != null) {
                val mistokens = StringTokenizer(linea, "\t")
                //var nameSuperheroe: String, var single: Boolean, var streghtForceLevel: Double, var age: Int, var comicName: String
                val nombreSup = mistokens.nextToken().trim { it <= ' ' }
                val single = mistokens.nextToken().trim { it <= ' ' }
                val streght = mistokens.nextToken().trim { it <= ' ' }
                val age = mistokens.nextToken().trim { it <= ' ' }
                val comicName = mistokens.nextToken().trim { it <= ' ' }
                //Transformo a los diferentes tipos de datos
                val singleS = single.toBoolean()
                val fuerza = streght.toDouble()
                val ageS = age.toInt()
                //lo paso al constructor para que me cree los objetos
                objeto = Superheroe(nombreSup, singleS, fuerza, ageS, comicName)
                //lo añado al vecto para poder jugetear con el
                objeto?.let { arrSuperHero.add(it) }
            }
            leerFichero.close()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }


    fun deTxtAObjeto2() {
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
                objeto2 = Comic(nombre,vig,pags,price)
                //lo añado al vecto para poder jugetear con el
                objeto2?.let { arrComic.add(it) }
            }
            leerFichero.close()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }
    //////***********************************************************+
    ////****************************************************
    //***************************************
    ////////////*************************MENUS Y WHENS HAHAHA AGHHHH ya matenme... :( tengo prueba de BI en 30 min ahhahah

    fun menuSuperheroe() {
        println("--------********    SUPERHEROE MENU  ********-------");
        println("                                                    ")
        println("1.          CREATE A NEW SUPERHEROE                 ")
        println("2.          READ ALL SUPERHEROES                    ");
        println("3.          UPDATE NAME SUPERHEROE                  ");
        println("4.          DELETE A SUPERHEROE                     ");
        println("5.          RETURN TO MAIN MENU                     ")
        println("0.          EXIT                                    ");
    }

    fun superheroeMenuOptions() {
        println("***********   BIENVENIDO A LOS SUPERHEROES... ahhaha tengo sueño *************")
        //menu()
        var opcion: Int = 1
        while (opcion != 0) {
            menuSuperheroe()
            println("*****************Ingrese el número de la opción deseada************")
            opcion = readLine()?.toInt() as Int
            when (opcion) {
                1 -> createSuperhero()
                2 -> readSupeheroes()
                3 -> updateSuperheroe()
                4 -> deleteSuperheroe()
               // 5 -> mix.menuPrincipal()
                0 -> System.exit(0)
            }
        }
    }
}

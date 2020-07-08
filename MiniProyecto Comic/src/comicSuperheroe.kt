class comicSuperheroe(){

    var comicPrincipal = comicControl()
    var superheroePrincipal = superheroeControl()

    fun menuPrincipal(){
        println("*********************     WELCOME     ************************\n" +
                "*********************     COMICS      ************************\n" +
                "*********************        &        ************************\n" +
                "*********************   SUPERHEROES   ************************\n")
        println("                     A donde deseas ir?                       \n" +
                "                     1.   COMIC                               \n" +
                "                     2.  SUPERHEROE                           \n" +
                "                     3.   SALIR                               \n")

        val opcion = readLine()!!.toInt()
        while (opcion != 3){
            when(opcion){
                1 -> comicPrincipal.comicMenuOptions()
                2 -> superheroePrincipal.superheroeMenuOptions()
                3 -> System.exit(0)
            }
        }
    }

}
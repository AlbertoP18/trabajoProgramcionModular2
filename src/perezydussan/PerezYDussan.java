package perezydussan;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PerezYDussan {

    public static double ObtenerMayor(double[] vector) {
        double Mayor = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] > Mayor) {
                Mayor = vector[i];
            }
        }
        return Mayor;
    }

    //Metodo para obtener el elemento menor
    public static double ObtenerMenor(double[] vector) {
        double Menor = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] < Menor) {
                Menor = vector[i];
            }
        }
        return Menor;
    }

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        FileWriter fichero = new FileWriter("ventasComputadores.txt");

        int nMarcas;
        int nCiudades;
        

        System.out.println("cuantas marcas de computadores ");
        nMarcas = input.nextInt();
        System.out.println("cuantas ciudades ");
        nCiudades = input.nextInt();

        String vMarcas[] = new String[nMarcas];
        String vCiudades[] = new String[nCiudades];

        //////llenando vectores marca y cuidad
        for (int i = 0; i < nMarcas; i++) {
            System.out.println("Digite el nombre de la marca");
            vMarcas[i] = input.next();
        }

        for (int i = 0; i < nCiudades; i++) {
            System.out.println("Digite el nombre de la cuidad");
            vCiudades[i] = input.next();
        }
        
        ///////////////variables
        double promGeneral=0;
        double[] promCiudades = new double[nCiudades];
        double[] promMarcas = new double[nMarcas];
        
        //Vector que tambien contendra todos los datos
        double tamañoMatriz = nMarcas * nCiudades;

        //Acumulados por marca y ciudad
        double AcuPorMarca[] = new double[nMarcas];
        double AcuPorCiudad[] = new double[nCiudades];

        //Ciudades mayores a 5 millones
        String CiudadesMayoresA5m[] = new String[nCiudades];

        //Mayor y menor por ciudad
        double MayorPorCiudad[] = new double[nCiudades];
        double MenorPorCiudad[] = new double[nCiudades];

        //Variables acumuladoras
        double TotalVentas = 0;

        //Variables contadoras
        int CantidadMayorA5m = 0;

        /////llenar matriz
        double[][] ventas = new double[nMarcas][nCiudades];

        for (int i = 0; i < nMarcas; i++) {
            for (int j = 0; j < nCiudades; j++) {
                System.out.println("Digite valor de ventas");
                ventas[i][j] = input.nextDouble();
            }
        }

        ////////operaciones 
        for (int i = 0; i < nMarcas; i++) {
            double AcumuladoMarca = 0;
            

            //Recorremos la fila
            for (int j = 0; j < nCiudades; j++) {
                AcumuladoMarca += ventas[i][j];
                TotalVentas += ventas[i][j];
            }

            AcuPorMarca[i] = AcumuladoMarca;


        }
        
        //////////recorremos columnas
        for (int j = 0; j < nCiudades; j++) { // Itera a través de las columnas
            double VentasCiudad[] = new double[nCiudades];
            for (int i = 0; i < nMarcas; i++) { // Itera a través de las filas
                AcuPorCiudad[j] += ventas[i][j];
                VentasCiudad[j] = ventas[i][j];
            }
            
                MayorPorCiudad[j] = ObtenerMayor(VentasCiudad);
                MenorPorCiudad[j] = ObtenerMenor(VentasCiudad);
        }

        for (int i = 0; i < nCiudades; i++) {
            if (AcuPorCiudad[i] > 5000000) {
                CiudadesMayoresA5m[i] = String.valueOf(AcuPorCiudad[i]);
                CantidadMayorA5m++;

            } else {
                CiudadesMayoresA5m[i] = "No es mayor a 5M";
            }
        }
        ////promedio general
        promGeneral = TotalVentas/tamañoMatriz;
        
        ////////promedio por marcas y ciudades
        for (int i = 0; i < nMarcas; i++) {
            promMarcas[i] = AcuPorMarca[i]/nCiudades;
        }
        
        for (int i = 0; i < nCiudades; i++) {
            promCiudades[i] = AcuPorCiudad[i]/nMarcas;
        }
        

        /////mostrar datos
        String menMatriz = "La  Matriz de las ventas: ";
        String menVMarca = "Las marcas son: \n";
        String MenVCiudades = "Las ciudades son: \n";
        String menAcuCiudad = "El acumulado por ciudades es: \n";
        String menAcuMarca = "El acumulado por Marca es: \n";
        String menMayorCiudad = "Las venta mas alta por ciudad es: \n";
        String menMenorCiudad = "Las venta mas baja por ciudad es: \n";
        String menMayora5M = "Las venta Mayores a 5 Millones son: \n";
        String menPromMarca = "Los promedios por marcas son: \n";
        String menPromCiudad = "Los promedios por ciudades son: \n";
        String menMatrizOrdenada = "La  Matriz de las ventas ordenada con metodo burbuja: ";

        for (int i = 0; i < nMarcas; i++) {
            menVMarca += "| " + vMarcas[i] + " |";
        }

        for (int i = 0; i < nCiudades; i++) {
            MenVCiudades += "| " + vCiudades[i] + " |";
        }

        //matriz
        for (int i = 0; i < nMarcas; i++) {
            menMatriz += "\n";
            for (int j = 0; j < nCiudades; j++) {
                menMatriz += "| " + ventas[i][j] + " |";
            }
        }

        for (int i = 0; i < nCiudades; i++) {
            menAcuCiudad += "| " + vCiudades[i] + " " + AcuPorCiudad[i] + " |";
        }

        for (int i = 0; i < nMarcas; i++) {
            menAcuMarca += "| " + vMarcas[i] + " " + AcuPorMarca[i] + " |";
        }

        for (int i = 0; i < nCiudades; i++) {

            menMayorCiudad += "| " + vCiudades[i] + "  " + MayorPorCiudad[i] + " |";
            menMenorCiudad += "| " + vCiudades[i] + "  " + MenorPorCiudad[i] + " |";

        }

        for (int i = 0; i < nCiudades; i++) {

            menMayora5M += "| " + vCiudades[i] + " " + CiudadesMayoresA5m[i] + " |";
        }
        for (int i = 0; i < nMarcas; i++) {
            menPromMarca += " | "+ vMarcas[i]+ "  "+ promMarcas[i]+" | ";
        }
        for (int i = 0; i < nCiudades; i++) {
            menPromCiudad += " | "+ vCiudades[i]+ "  "+ promCiudades[i]+" | ";
        }
        
        

        System.out.println(menVMarca + "\n");

        System.out.println(MenVCiudades + "\n");

        System.out.println(menMatriz + "\n");

        System.out.println(menAcuMarca + "\n");

        System.out.println(menAcuCiudad + "\n");

        System.out.println(menMayorCiudad + "\n");

        System.out.println(menMenorCiudad + "\n");
        
        System.out.println(menMayora5M + "\n");
        
        System.out.println(menPromCiudad +"\n");
        
        System.out.println(menPromMarca +"\n");
        
        System.out.println("El total de ventas en Pesos Colombianos es : \n"
                + TotalVentas+"\n"
                        + "El total de ventas en Dolares es: \n"
                        + TotalVentas/4213+"\n"
                        + "El total de ventas en Euros es: \n"
                        + TotalVentas/4462+"\n");
        
        
        System.out.println("El promedio general de la ventas es : \n"
                + promGeneral+"\n");
        

// matriz con el metodo de burbuja   
        for (int i = 0; i < nMarcas; i++) {
            for (int j = 0; j < nCiudades; j++) {
                for (int k = 0; k < nMarcas; k++) {
                    for (int l = 0; l < nCiudades; l++) {
                        if (ventas[i][j] >= ventas[k][l]) {
                            ventas[i][j] = ventas[i][j];
                        } else {
                            double temp = ventas[k][l];
                            ventas[k][l] = ventas[i][j];
                            ventas[i][j] = temp;
                        }
                    }
                }
            }
        }



        for (int i = 0; i < nMarcas; i++) {
            menMatrizOrdenada += "\n";
            for (int j = 0; j < nCiudades; j++) {
                menMatrizOrdenada += "| " + ventas[i][j] + " |";
            }
        }
        System.out.println(menMatrizOrdenada);
        
        
       ////////////////////// Guardado en txt 
       
        fichero.write(menVMarca + "\n");

        fichero.write(MenVCiudades + "\n");

        fichero.write(menMatriz + "\n");

        fichero.write(menAcuMarca + "\n");

        fichero.write(menAcuCiudad + "\n");

        fichero.write(menMayorCiudad + "\n");

        fichero.write(menMenorCiudad + "\n");
        
        fichero.write(menMayora5M + "\n");
        
        fichero.write(menPromCiudad +"\n");
        
        fichero.write(menPromMarca +"\n");
        
        fichero.write("El total de ventas en Pesos Colombianos es : \n" + TotalVentas + "\n" + "El total de ventas en Dolares es: \n" + TotalVentas / 4213 + "\n"
                + "El total de ventas en Euros es: \n" + TotalVentas/4462+"\n");
        
        
        fichero.write("El promedio general de la ventas es : \n" + promGeneral+ "\n");
        
        
        
        fichero.write(menMatrizOrdenada);

        
        fichero.close();
        
    }

}

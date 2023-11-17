package biblioteca;

import java.util.Random;
import java.util.Scanner;
 
public class Helper {
 
    //region Static Objects
 
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
 
    //endregion
 
    //region Integer Helper
 
    public static Integer getInt(String inputMessage, String errorMessage) {
        while(true){
            try {
                System.out.print(inputMessage);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(errorMessage);
            }
        }
    }
 
    public static Integer getInt(String inputMessage) {
        return getInt(inputMessage, "\nERROR: EL VALOR INGRESADO NO CORRESPONDE A UN NUMERO ENTERO\n");
    }
 
    public static Integer getPositiveInt(String inputMessage, String errorMessge) {
        while (true) {
            int num = getInt(inputMessage);
            if(num > 0)return num;
            System.out.print("\n" + errorMessge);
        }
    }
 
    public static Integer getPositiveInt(String inputMessage){
        return getPositiveInt(inputMessage, "\nERROR: EL NUMERO INGRESADO NO ES POSITIVO");
    }
 
    //endregion
 
    //region Double Helper
 
    public static Double getDouble(String inputMessage, String errorMessage) {
        while(true){
            try {
                System.out.print(inputMessage);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(errorMessage);
            }
        }
    }
 
    public static Double getDouble(String inputMessage){
        return getDouble(inputMessage, "\nERROR: EL VALOR INGRESADO NO CORRESPONDE A UN NUMERO");
    }
 
    //endregion
 
    //region Character Helper
 
    public static Character getChar(String inputMessage, String errorMessage) {
 
        while (true) {
            try {
                System.out.print("\n" + inputMessage);
                char caracter = scanner.next().toUpperCase().charAt(0);
        int valorASCII = (int)caracter;
                if (valorASCII == 165 || (valorASCII >= 65 && valorASCII <= 90))
                    return caracter;
                else
                    throw new Exception(errorMessage);
            } catch (Exception e) {
                System.out.print("\n" + e.getMessage());
                scanner.nextLine();
            }
        }
    }
 
    public static Character getChar(String inputMessage){
        return getChar(inputMessage, "\nERROR: INGRESE UN CARACTER VALIDO");
    }
 
    //endregion
 
    //region Random Double with two decimal
 
    public static double randomDouble(int bound) {
        double num;
        num = random.nextInt(bound) + random.nextDouble();
        return (double)Math.round(num * 100d)/100;
    }
 
    //endregion
 
    //region Question (yes or no)
 
    public static char yesOrNo(String question){
        char resp;
        do {
            System.out.print("\n" + question + "\nPRESIONE 'S'-SI\nPRESIONE 'N'-NO");
            resp = Character.toUpperCase(scanner.next().charAt(0));
            if(resp == 'N' || resp == 'S')return resp;
            System.out.print("\nERROR: LA OPCION INGRESADA NO ES CORRECTA INTENTELO DE NUEVO");
        } while (true);
    }
 
    //endregion
    public static String getString(String inputMessage) {
        System.out.print(inputMessage);
        return scanner.nextLine();
    }
}
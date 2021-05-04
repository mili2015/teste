package geral;

import java.io.IOException;   
import java.io.PrintStream;   
import java.net.Socket;   
import java.util.Scanner;
  
public class Cliente {   
  
    public static void main(String[] args) {   
           
        //Declaro o socket cliente   
        Socket s = null;   
           
        //Declaro a Stream de saida de dados   
        PrintStream ps = null;
        String campos = "";
        Scanner ler = new Scanner(System.in);
           
        try{   
               
            //Cria o socket com o recurso desejado na porta especificada   
            s = new Socket("172.2.1.59",8000);   
               
            //Cria a Stream de saida de dados   
            ps = new PrintStream(s.getOutputStream());   
               
            //Imprime uma linha para a stream de saída de dados   
            while(!campos.equalsIgnoreCase("FIM"))
            {
            	campos = ler.nextLine();
            	ps.println(campos);
            }
            
            ps.close();
               
        //Trata possíveis exceções   
        }catch(IOException e){   
               
            System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo socket.");   
           
        }finally
        {   
            try
            {   
                   
                //Encerra o socket cliente   
                s.close();   
                   
            }catch(IOException e){}   
        }   
  
    }   
}  
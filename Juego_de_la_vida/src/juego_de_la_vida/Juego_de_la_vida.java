/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_de_la_vida;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Cristhian Javier Ruiz León
 * it's program allows to play the game of live
 * 
 */

public class Juego_de_la_vida 
{
    JFrame marco;
    JPanel tablero;
    
    /**
     * here Add to variables
     */
    
    int[][] mortalidad;
    int[][] tableroA;
    int[][] tableroB ;
    int[][] tableroC ;
    
    int filas = 100;
    int columnas = 100;
    int milisegundos = 90;
    
    JButton[][] Cuadros;

    private void PanelInferior()
    {
        Cuadros = new JButton[filas][columnas];
        tablero= new JPanel();
        tablero.setLayout(new GridLayout(filas,columnas));
        
        for(int i = 0 ; i < filas ; i++)
        {
            for(int j = 0 ; j < columnas ; j++)
            {
                JButton cuadros = new JButton();
                String id = String.valueOf(i) +"-" + String.valueOf(j);
                cuadros.setName(id);

                Cuadros[i][j] = cuadros;
            }
        }
       
        
        for(int i = 0 ; i < filas ; i++)
        {
            for(int j = 0 ; j < columnas ; j++)
            {
                tablero.add(Cuadros[i][j]);
            }
        }
                  
    }     
    /**
     * in this part the size is gives to the window 
     */
    private void Ventana()
    {
        marco =new JFrame("JUEGO DE LA VIDA");
        marco.setSize(800, 800);
        marco.add(tablero);
        marco.setVisible(true);

    }
    
    /**
     * from this part the process begins
     */
    private void empiezaJuego()
    {

        mortalidad  = new int[3][3];
        tableroA   = new int[filas][columnas];
        tableroB   = new int[filas][columnas];
        tableroC   = new int[filas][columnas];
        
        inicializaTableros(tableroA);
        iniciaVida();
        colorTablero();
        pintaTablero(tableroA);

     do
     {
         
        for(int i= 0 ; i < filas -2 ; i++)
        {            
            for(int j = 0 ; j < columnas -2 ; j++)
            {
                int celda1 = tableroA[(i)][(j)];
                int celda2 = tableroA[(i)][(j+1)];
                int celda3 = tableroA[(i)][(j+2)];
                
                int celda4 = tableroA[(i+1)][(j)];
                int celda5 = tableroA[(i+1)][(j+1)];
                int celda6 = tableroA[(i+1)][(j+2)];
                
                int celda7 = tableroA[(i+2)][(j)];
                int celda8 = tableroA[(i+2)][(j+1)];
                int celda9 = tableroA[(i+2)][(j+2)];
                
                
                mortalidad[0][0] = celda1;
                mortalidad[0][1] = celda2;
                mortalidad[0][2] = celda3;
                
                mortalidad[1][0] = celda4;
                mortalidad[1][1] = celda5;
                mortalidad[1][2] = celda6;
                
                mortalidad[2][0] = celda7;
                mortalidad[2][1] = celda8;
                mortalidad[2][2] = celda9;
                
                    int contador = 0;
                    int k = 0;
                    int l = 0;
                    
                    for(k = 0 ; k < 3 ; k++)
                    {
                        for( l= 0 ; l < 3 ; l++)
                        {
                            if( !(k == 1 && l == 1))
                            {
                                if(mortalidad[k][l] == 1)
                                {
                                    contador++;
                                }
                            }
                           
                        }
                    }

                    if(contador < 2 && mortalidad[1][1] == 1) 
                    {  
                        tableroB[(i+1)][(j+1)] = 0;
                    }
                    else if(contador > 3 && mortalidad[1][1] == 1) 
                    { 
                        tableroB[(i+1)][(j+1)] = 0;
                    }
                    else if(contador == 3 && mortalidad[1][1] == 0 ) 
                    { 
                        tableroB[(i+1)][(j+1)] = 1;
                    }
                    else if(contador == 3 && mortalidad[1][1] == 1 ) 
                    { 
                        tableroB[(i+1)][(j+1)] = 1;
                    }
                    else if(contador == 2 ) 
                    {
                        tableroB[(i+1)][(j+1)] = tableroA[(i+1)][(j+1)];
                    }
            }
        } 
        tableroC = tableroA;
        tableroA = tableroB;
        tableroB = tableroC;
        
        try 
        {
            Thread.currentThread().sleep(milisegundos);
         } 
        catch (InterruptedException ie) 
        {
           System.out.println("Exception " + ie.toString() );
        }
        
        pintaTablero(tableroB);
        inicializaTableros(tableroB);

     }
     while(true);
     
     /**
      * here the color is added to the board
      */
        
    }     
    private void pintaTablero(int[][]tabla)
    {
        for(int i = 0 ; i < filas ; i++)
        {
            for(int j = 0; j < columnas ; j++)
            {
                if(tabla[i][j] == 1)
                {
                    Cuadros[i][j].setBackground(Color.cyan);
                }
                
                if(tabla[i][j] == 0)
                {
                    Cuadros[i][j].setBackground(Color.black);
                }
            }
        }
    }
    private void colorTablero()
    {
        for(int i = 0 ; i < filas ; i++)
        {
            for(int j = 0; j < columnas ; j++)
            {
                    Cuadros[i][j].setBackground(Color.black);
            }
        }
    }
    private void inicializaTableros(int[][] tablero)
    {
        for(int i = 0 ; i < filas ; i++)
        {
            for(int j= 0 ; j < columnas ; j++)
            {
                tablero[i][j] = 0;
            }
        }
    }
    private void iniciaVida()
    {
            for(int j= 0 ; j < 2000 ; j++)
            {
                int y = (int)Math.floor(Math.random()*(0-filas+1)+filas);
                int x = (int)Math.floor(Math.random()*(0-columnas+1)+columnas);
                
                tableroA[y][x] = 1;
            }    
    }
    public void mensaje(String texto,String titulo)
    {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.WARNING_MESSAGE);
    }
    public static void main(String [] game)
    {
        Juego_de_la_vida juego = new Juego_de_la_vida();
        juego.PanelInferior();
        juego.Ventana();
        juego.empiezaJuego();
    }  
}


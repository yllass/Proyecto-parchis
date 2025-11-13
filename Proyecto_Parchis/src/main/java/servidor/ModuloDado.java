/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.util.Random;

/**
 *
 * @author Infraestructura
 */
public class ModuloDado {
    private Random random = new Random();

    public int tirarDado() {
        return random.nextInt(6) + 1;
    }
}

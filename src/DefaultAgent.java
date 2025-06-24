/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.Agent;
/**
 *
 * @author gengji23
 */
public class DefaultAgent extends Agent {
     @Override
     protected void setup(){
        //initialisation de l'agent
        System.out.println("Agent" + getAID().getName()+" ready");
    }

     @Override
    protected void takeDown(){
        //traitement de fin
        System.out.println("Agent" + getAID().getName() +" done");
    }
}


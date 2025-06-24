/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.Agent;
/**
 * Agent de base qui affiche un message au démarrage et à la fin.
 */
public class DefaultAgent extends Agent {
    /**
     * Appelée à la création de l'agent.
     */
    @Override
    protected void setup(){
        // initialisation de l'agent
        System.out.println("Agent" + getAID().getName()+" ready");
    }

    /**
     * Appelée juste avant la destruction de l'agent.
     */
    @Override
    protected void takeDown(){
        // traitement de fin
        System.out.println("Agent" + getAID().getName() +" done");
    }
}


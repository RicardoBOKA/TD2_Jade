/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * Agent possédant deux comportements internes : un générique et un trivial.
 */
public class MyGenericAgent extends DefaultAgent{
    /**
     * Initialise l'agent et ajoute ses comportements internes.
     */
    @Override
    protected void setup(){
        // initialisation de l'agent
        super.setup();
        addBehaviour(new GenericBehaviour(this));
        addBehaviour(new TrivialBehaviour(this));
     }

    /**
     * Premier comportement qui incrémente un compteur à chaque appel.
     */
    public class GenericBehaviour extends Behaviour{

        private int counter = 0;
        GenericBehaviour(Agent a){
            super(a);
        }
        
        @Override
        public void action(){
            counter++;
            System.out.println("Comportement " + this.getBehaviourName());
            System.out.println("action of agent" + myAgent.getAID().getName());
            System.out.println("action counter: " + counter);
        }

        @Override
        public boolean done(){
            System.out.println("done" + getAgent().getAID().getName());
            return false;
        }

    }

    /**
     * Deuxième comportement qui se termine immédiatement.
     */
    public class TrivialBehaviour extends Behaviour{

        
        TrivialBehaviour(Agent a){
            super(a);
        }

        @Override
        public void action(){
            System.out.println("Comportement " + this.getBehaviourName());
            System.out.println("action of agent" + myAgent.getAID().getName());
        }

        @Override
        public boolean done(){
            System.out.println("done" + getAgent().getAID().getName());
            return true;
        }

    }
}

     


    
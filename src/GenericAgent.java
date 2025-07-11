/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * Agent simple disposant d'un seul comportement générique.
 */
public class GenericAgent extends DefaultAgent{

    /**
     * Initialise l'agent et ajoute son comportement unique.
     */
    @Override
    protected void setup(){
        // initialisation de l'agent
        super.setup();
        addBehaviour(new GenericBehaviour(this));
     }

    /**
     * Comportement qui supprime l'agent après un certain nombre d'itérations.
     */
    public class GenericBehaviour extends Behaviour{

        private int counter = 0;
        private static final int END = 3;
        GenericBehaviour(Agent a){
            super(a);
        }

        public void action(){
            counter++;
            System.out.println("action of agent" + myAgent.getAID().getName());
            System.out.println("counter: " + counter);
           
            if (isDone(counter)){
                doDelete();
            }
        }

        /**
         * Teste si le nombre d'actions effectuees a atteint la limite.
         */
        @Override
        public boolean done(){
            System.out.println("done" + getAgent().getAID().getName());
            System.out.println("counter: " + counter);
            return isDone(counter);
        }
        private boolean isDone(int counter){
            return counter == END;
        }

    }
}
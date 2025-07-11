/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Agent d'exemple utilisant un comportement à un coup et un comportement cyclique.
 */
public class Agent2 extends DefaultAgent{
    private int agentCount = 0;
   
    /**
     * Initialise l'agent et ajoute ses comportements.
     */
    @Override
    protected void setup(){
        // initialisation de l'agent
        super.setup();
       
        // Comportement exécuté une seule fois
        addBehaviour(new OneShotBehaviour(this) {
            @Override
            public void onStart() {
                System.out.println("Comportement " + this);
                System.out.println("onStart ::  " + myAgent.getAID().getName());
            }

            @Override
            public void action(){
                System.out.println("Comportement " + this);
                System.out.println("one shot action :: agent_count" + agentCount++);
            }
        });
       
        // Comportement répété jusqu'à la condition de fin
        addBehaviour(new CyclicBehaviour(this){
            private int counter = 0;
            private static final int END = 3;

            @Override
            public void action(){
                counter++;
                System.out.println("Comportement " + this);
                System.out.println("action of agent " + myAgent.getAID().getName());
                System.out.println("action counter: " + this.counter);
                System.out.println("agent counter: " + agentCount);

                if (isDone(this.counter)){
                    System.out.println("before doDelete");
                    doDelete();
                }
            }

            /**
             * Indique si le comportement doit se terminer.
             */
            private boolean isDone(int counter){
                return counter == END;
            }
        });
     }
}

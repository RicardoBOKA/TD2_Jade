/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Variante de {@link Agent2} où le compteur est incrémenté à chaque démarrage du comportement.
 */
public class Agent3 extends DefaultAgent {
    private int agentCount = 0;
    
    /**
     * Initialise l'agent et met en place ses comportements.
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
                System.out.println("one shot action :: agent_count" + agentCount);
            }
        });
        
        // Comportement répété qui incrémente un compteur
        addBehaviour(new CyclicBehaviour(this){
            private int counter = 0;
            private static final int END = 3;
            
            @Override
            public void onStart() {
                agentCount++;
                System.out.println("Comportement " + this);
                System.out.println("action of agent " + myAgent.getAID().getName());
            }

            @Override
            public void action(){
                counter++;
                
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

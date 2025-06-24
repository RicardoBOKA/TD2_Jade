/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

/**
 *
 * @author yexi24
 */
public class PrintBehaviourAgent extends DefaultAgent {
    private static final int TIMEOUT = 1000;
    private static final int WAKEUP_TIME = 5000;
    
    @Override
    protected void setup(){
        //initialisation de l'agent
        super.setup();
        
        // OneShotBehaviour
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void onStart() {
                System.out.println("Comportement à un coup associé à un message request " + this);
            }

            @Override
            public void action() {
                System.out.println("action: request de l'agent " + myAgent.getAID().getName());
                System.out.println("Tu veux voir le dernier film de Sofia Coppola : BLING RING");
            }

            @Override
            public int onEnd() {
                System.out.println("onEnd du comportement à un coup");
                return super.onEnd();
            }
        });

        // TickerBehaviour
        addBehaviour(new TickerBehaviour(this, TIMEOUT) {
            @Override
            public void onStart() {
                System.out.println("Comportement périodique associé à un message inform  " + this);
                super.onStart();
            }

            @Override
            protected void onTick() {
                System.out.println("envoi information toutes les secondes ");
                System.out.println("BLING RING : des jeunes qui rêvent de devenir riche et célèbre et qui se font arrêter à force de cambrioler les maisons des stars. ");
            }
        });

        //WakerBehaviour
        addBehaviour(new WakerBehaviour(this, WAKEUP_TIME) {
            @Override
            public void onStart() {
                System.out.println("comportement imprimant une confirmation au bout de 5 secondes " + this);
                super.onStart();
            }

            @Override
            protected void onWake() {
                System.out.println("onWake : A ce soir 20h ");
            }

            @Override
            public int onEnd() {
                System.out.println("onEnd : terminaison de l'agent ");
                myAgent.doDelete();
                return super.onEnd();
            }
        });
        
     }
}

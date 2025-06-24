/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
/**
 *
 * @author gengji23
 */
public class MyGenericBehaviourAgent2 extends DefaultAgent{
    @Override
    protected void setup(){
        //initialisation de l'agent
        super.setup();
        addBehaviour(new GenericBehaviour(this));
        addBehaviour(new TrivialBehaviour(this));
     }


    public class GenericBehaviour extends Behaviour{

        private int counter = 0;
        private static final int END = 3;
        GenericBehaviour(Agent a){
            super(a);
        }
       
        @Override
        public void action(){
            counter++;
            System.out.println("Comportement " + this.getBehaviourName());
            System.out.println("action of agent " + myAgent.getAID().getName());
            System.out.println("action counter: " + counter);
           
            if (isDone(counter)){
                System.out.println("before doDelete");
                doDelete();
            }
        }

        @Override
        public boolean done(){
            System.out.println("done " + getAgent().getAID().getName());
            return isDone(counter);
        }
       
        private boolean isDone(int counter){
            return counter == END;
        }

    }
   
    public class TrivialBehaviour extends Behaviour{

        TrivialBehaviour(Agent a){
            super(a);
        }

        @Override
        public void action(){
            System.out.println("Deuxième Comportement " + this.getBehaviourName());
            System.out.println("action of agent " + myAgent.getAID().getName());
        }

        @Override
        public boolean done(){
            System.out.println("done " + getAgent().getAID().getName());
            return true;
        }

    }
}
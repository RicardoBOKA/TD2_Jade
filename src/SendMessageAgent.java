import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Agent "lola" qui envoie différents types de messages 
 * (request, inform périodique et confirm différé).
 */
public class SendMessageAgent extends DefaultAgent {
    private static final int INFORM_PERIOD = 1000;
    private static final int CONFIRM_DELAY = 5000;

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new SendCompositeBehaviour(this));
    }

    /**
     * Comportement composé exécutant en parallèle les trois envois de messages.
     */
    private static class SendCompositeBehaviour extends ParallelBehaviour {
        SendCompositeBehaviour(Agent a) {
            super(a, WHEN_ALL);
            addSubBehaviour(new SendRequestBehaviour(a));
            addSubBehaviour(new SendInformBehaviour(a, INFORM_PERIOD));
            addSubBehaviour(new SendConfirmBehaviour(a, CONFIRM_DELAY));
        }
    }

    /** envoi du message request une seule fois */
    private static class SendRequestBehaviour extends OneShotBehaviour {
        SendRequestBehaviour(Agent a) { super(a); }
        @Override
        public void onStart() {
            System.out.println("onStart:: Comportement  à un coup " + this);
        }
        @Override
        public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(new AID("jack", AID.ISLOCALNAME));
            msg.setConversationId("plan-film");
            msg.setContent("Tu veux voir le dernier film de Sofia Coppola : BLING RING");
            System.out.println("Message de demande  " + msg);
            myAgent.send(msg);
            System.out.println("envoi du request de l'agent " + myAgent.getAID().getName());
        }
    }

    /** envoi régulier d'informations */
    private static class SendInformBehaviour extends TickerBehaviour {
        SendInformBehaviour(Agent a, long period) { super(a, period); }
        @Override
        protected void onTick() {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("jack", AID.ISLOCALNAME));
            msg.setConversationId("plan-film");
            msg.setContent("BLING RING : des jeunes qui rêvent de devenir riche et célèbre et qui se font arrêter à force de cambrioler les maisons des stars.");
            System.out.println("Comportement périodique  " + this);
            System.out.println("Message d'information " + msg);
            myAgent.send(msg);
            System.out.println("envoi information toutes les secondes de l'agent " + myAgent.getAID().getName());
        }
    }

    /** envoi d'un message confirm après un délai puis fin de l'agent */
    private static class SendConfirmBehaviour extends WakerBehaviour {
        SendConfirmBehaviour(Agent a, long timeout) { super(a, timeout); }
        @Override
        protected void onWake() {
            ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);
            msg.addReceiver(new AID("jack", AID.ISLOCALNAME));
            msg.setConversationId("plan-film");
            msg.setContent("A ce soir 20h");
            System.out.println("Comportement planifié au bout de 3 secondes  " + this);
            System.out.println("Message de confirmation " + msg);
            myAgent.send(msg);
            System.out.println("Envoi du message de confirmation de l'agent" + myAgent.getAID().getName());
            myAgent.doDelete();
            System.out.println("OnEnd ::  fin d'activité de l'agent");
        }
    }
}

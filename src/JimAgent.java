import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Agent "jim" qui envoie une proposition de boire un verre à Jack.
 */
public class JimAgent extends DefaultAgent {

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new SendDrinkRequestBehaviour());
    }

    /**
     * Envoie un message request à jack dès le démarrage de l'agent.
     */
    private class SendDrinkRequestBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(new AID("jack", AID.ISLOCALNAME));
            msg.setConversationId("drink");
            msg.setContent("On prend un pot à 20h ?");
            myAgent.send(msg);
        }
    }
}

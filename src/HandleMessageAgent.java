import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Agent "jack" qui traite différents types de messages.
 */
public class HandleMessageAgent extends DefaultAgent {

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new HandleCompositeBehaviour(this));
    }

    /**
     * Comportement parallèle regroupant tous les traitements de messages.
     */
    private static class HandleCompositeBehaviour extends ParallelBehaviour {
        HandleCompositeBehaviour(Agent a) {
            super(a, WHEN_ALL);
            addSubBehaviour(new HandleRequestBehaviour());
            addSubBehaviour(new HandleInformBehaviour());
            addSubBehaviour(new HandleConfirmBehaviour());
            addSubBehaviour(new HandleJimRequestBehaviour());
        }
    }

    /** Réception des messages REQUEST */
    private static class HandleRequestBehaviour extends CyclicBehaviour {
        @Override
        public void onStart() {
            System.out.println("comportement bloqué " + this);
        }
        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                System.out.println("Behaviour name " + this);
                System.out.println(myAgent.getAID().getName() + " receive message " + msg);
            } else {
                block();
            }
        }
    }

    /** Réception des INFORM de lola uniquement */
    private static class HandleInformBehaviour extends CyclicBehaviour {
        private final MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchSender(new AID("lola", AID.ISLOCALNAME)));
        @Override
        public void onStart() {
            System.out.println("comportement bloqué " + this);
        }
        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(template);
            if (msg != null) {
                System.out.println("Behaviour name " + this);
                System.out.println(myAgent.getAID().getName() + " receive message " + msg);
            } else {
                block();
            }
        }
    }

    /** Réception des CONFIRM de lola puis arrêt de l'agent */
    private static class HandleConfirmBehaviour extends CyclicBehaviour {
        private final MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
                MessageTemplate.MatchSender(new AID("lola", AID.ISLOCALNAME)));
        @Override
        public void onStart() {
            System.out.println("comportement bloqué " + this);
        }
        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(template);
            if (msg != null) {
                System.out.println("Behaviour name " + this);
                System.out.println(myAgent.getAID().getName() + " receive message " + msg);
                myAgent.addBehaviour(new EndedBehaviour());
            } else {
                block();
            }
        }
    }

    /** Traitement du message de jim */
    private static class HandleJimRequestBehaviour extends CyclicBehaviour {
        private final MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchSender(new AID("jim", AID.ISLOCALNAME)));
        @Override
        public void onStart() {
            System.out.println("comportement bloqué " + this);
        }
        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(template);
            if (msg != null) {
                System.out.println("Behaviour name " + this);
                System.out.println(myAgent.getAID().getName() + " receive message " + msg);
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.REFUSE);
                reply.setContent("Je ne suis pas libre");
                myAgent.send(reply);
            } else {
                block();
            }
        }
    }

    /**
     * Comportement exécuté une seule fois pour terminer l'agent.
     */
    private static class EndedBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            System.out.println("Behaviour name :: delete agent " + this);
            myAgent.doDelete();
        }
    }
}

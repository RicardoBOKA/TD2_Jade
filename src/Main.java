import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

/**
 * Classe principale permettant d'exécuter l'ensemble des agents de
 * l'application. Elle crée un conteneur JADE et lance chacun des agents
 * définis dans le projet.
 */
public class Main {
    /**
     * Point d'entrée de l'application.
     *
     * <p>Un conteneur JADE est créé puis chaque agent est démarré à son tour.</p>
     */
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);

        try {
            container.createNewAgent("agent2", Agent2.class.getName(), null).start();
            container.createNewAgent("agent3", Agent3.class.getName(), null).start();
            container.createNewAgent("genericAgent", GenericAgent.class.getName(), null).start();
            container.createNewAgent("myGenericAgent", MyGenericAgent.class.getName(), null).start();
            container.createNewAgent("myGenericBehaviourAgent2", MyGenericBehaviourAgent2.class.getName(), null).start();
            container.createNewAgent("printBehaviourAgent", PrintBehaviourAgent.class.getName(), null).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
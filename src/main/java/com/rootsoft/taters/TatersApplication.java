package com.rootsoft.taters;

import com.rootsoft.taters.models.Blockchain;
import com.rootsoft.taters.models.PeerNetwork;
import com.rootsoft.taters.models.consensus.ProofOfWork;
import com.rootsoft.taters.models.ConnectionCallback;
import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.repositories.block.BlockListRepository;
import com.rootsoft.taters.utils.NodeHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TatersApplication {

    //Constants
    public static final String TAG = TatersApplication.class.getSimpleName();

    //Attributes
    private Blockchain blockchain;
    private PeerNetwork network;

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(TatersApplication.class, args);

		System.out.println("Spring started!");

		TatersApplication application = new TatersApplication();
		application.boot();

	}

	public void boot() {

        //Create the blockchain
        blockchain = new Blockchain(new BlockListRepository(), new ProofOfWork(2));

        //Create the network
        network = new PeerNetwork(connectionCallback);

        //Create our nodes
        Node node = NodeHelper.createFullNode("Initial node");
        Node second = NodeHelper.createFullNode("Second node");

        //Let the new nodes join the network
        network.join(node);
        //network.join(second);

    }

	private static ConnectionCallback connectionCallback = new ConnectionCallback() {

        @Override
        public void onConnected(Node node) {
            System.out.println("Peer " + node.getName() + " (" + node.getPeer().peerID() + ") joined the network.");
        }

        @Override
        public void onDisconnect(Node node) {

        }

        @Override
        public void onError(Node peer) {

        }

    };

}

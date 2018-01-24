package com.rootsoft.taters;

import com.rootsoft.taters.models.protocols.messages.PingProtocol;
import com.rootsoft.taters.models.protocols.messages.VersionProtocol;
import com.rootsoft.taters.utils.Log;
import com.rootsoft.taters.models.Blockchain;
import com.rootsoft.taters.models.PeerNetwork;
import com.rootsoft.taters.models.consensus.ProofOfWork;
import com.rootsoft.taters.models.ConnectionCallback;
import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.factories.NodeFactory;
import com.rootsoft.taters.models.node.NodeType;
import com.rootsoft.taters.repositories.block.BlockListRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TatersApplication {

    //Constants
    public static final String TAG = TatersApplication.class.getSimpleName();

    //Attributes
    private Blockchain blockchain;
    private PeerNetwork network;

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(TatersApplication.class, args);

        Log.i("Spring started!");

		TatersApplication application = new TatersApplication();
		application.boot();

	}

	public void boot() {

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        //Create the blockchain
        blockchain = new Blockchain(new BlockListRepository(), new ProofOfWork(2));

        //Create the network
        network = new PeerNetwork(connectionCallback);

        //Create our nodes
        Node node = NodeFactory.createNode("SPV node", NodeType.SPV);
        Node second = NodeFactory.createNode("Remote node", NodeType.FULL);

        //Let the new nodes join the network
        network.join(node);
        //node.sendProtocol(new VersionProtocol(1, 12));

        //Join the network 5 seconds later
        Runnable task = () -> network.join(second);
        executor.schedule(task, 5, TimeUnit.SECONDS);

    }

	private static ConnectionCallback connectionCallback = new ConnectionCallback() {

        @Override
        public void onConnected(Node node, Node bootstrap) {
            //Send a protocol message Version that contains various fields
            node.sendProtocol(bootstrap.getPeer().peerAddress(), new VersionProtocol(1, 0)); //TODO Get version code and blockcount
        }

        @Override
        public void onDisconnect(Node node) {

        }

        @Override
        public void onError(Node peer) {

        }

    };

}

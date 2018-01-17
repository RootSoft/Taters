package com.rootsoft.taters.repositories.node;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class NodeListRepository implements Repository<Node> {

    //Constants
    public static final String TAG = Node.class.getSimpleName();

    //Properties
    private ArrayList<Node> peers;

    //Constructors
    public NodeListRepository() {
        this.peers = new ArrayList<>();
    }

    @Override
    public boolean add(Node peer) {
        return peer != null && peers.add(peer);

    }

    @Override
    public List<Node> getItems() {
        return peers;
    }

    @Override
    public Node getItem(int position) {
        return peers.get(position);
    }

    @Override
    public int getSize() {
        return peers.size();
    }

}

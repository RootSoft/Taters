package com.rootsoft.taters;

import com.rootsoft.taters.models.Blockchain;
import com.rootsoft.taters.models.block.Block;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TatersApplication {

	public static void main(String[] args) {
		SpringApplication.run(TatersApplication.class, args);
		System.out.println("Spring started!");

        Blockchain blockchain = new Blockchain();
        Block block = new Block(1, new DateTime().getMillis(), "Second block");
        Block block2 = new Block(2, new DateTime().getMillis(), "Third block");
        blockchain.addBlock(block);
        blockchain.addBlock(block2);

        System.out.println(blockchain.toString());


	}
}

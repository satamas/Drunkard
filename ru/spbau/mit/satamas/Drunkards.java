package ru.spbau.mit.satamas;
import java.io.IOException;


public class Drunkards {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Map.init();
		while(true){
			Map.step();
			System.in.read();
		}

	}

}

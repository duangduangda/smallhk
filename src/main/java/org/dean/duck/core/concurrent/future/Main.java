package org.dean.duck.core.concurrent.future;

public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		Data data = client.request("name");
		System.out.println("request done!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("data = " + data.get());
	}
}

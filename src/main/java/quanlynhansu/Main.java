package quanlynhansu;

import java.awt.EventQueue;

import quanlynhansu.views.App;

public class Main {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(() -> {
			new App();
		});
	}
}

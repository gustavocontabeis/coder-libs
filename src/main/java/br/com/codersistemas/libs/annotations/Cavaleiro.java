package br.com.codersistemas.libs.annotations;

import java.util.Scanner;

public class Cavaleiro {

	public static void main(String[] args) {

		String comando;
		int y;
		int x;

		Scanner marte = new Scanner(System.in);

		System.out.println("Informe o X");
		x = marte.nextInt();

		System.out.println("Informe o Y");
		y = marte.nextInt();

		System.out.println("Indique o comando");
		comando = marte.next();

		for (int i = 0; i < comando.length(); i++) {
			System.out.println(comando.substring(i, i+1));
			switch (comando.substring(i, i+1)) {

			case "N":
				y++;
				break;

			case "S":
				y--;
				break;

			case "L":
				x++;
				break;

			case "O":
				x--;
				break;

			}

		}

		System.out.println("A posição final foi  (" + x + "," + y + ")");

	}

}
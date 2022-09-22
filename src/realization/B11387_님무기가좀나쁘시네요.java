package realization;

import java.util.Scanner;

public class B11387_님무기가좀나쁘시네요 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double[][] stat = new double[5][5];
		for(int i=1; i<=4; i++) {
			for(int j=0; j<5; j++) {
				stat[i][j] = sc.nextDouble();
			}
		}
		double cri = getPower(stat[1][0], stat[1][1], stat[1][2], stat[1][3], stat[1][4]);
		double pabu = getPower(stat[2][0], stat[2][1], stat[2][2], stat[2][3], stat[2][4]);
		
		double[][] changeStat = new double[2][5];
		
		//change stat
		for(int j=0; j<5; j++) {
			changeStat[0][j] = stat[1][j] - stat[3][j] + stat[4][j];		//cri
			changeStat[1][j] = stat[2][j] - stat[4][j] + stat[3][j];		//pabu
		}
		double changeCri = getPower(changeStat[0][0], changeStat[0][1], changeStat[0][2], changeStat[0][3], changeStat[0][4]);
		double changePabu = getPower(changeStat[1][0], changeStat[1][1], changeStat[1][2], changeStat[1][3], changeStat[1][4]);
		
		System.out.println(cri);
		System.out.println(pabu);
		
		System.out.println(changeCri);
		System.out.println(changePabu);
		if(cri > changeCri ) {
			System.out.println("-");
		}else if(cri < changeCri) {
			System.out.println("+");
		}else {
			System.out.println("0");
		}
		
		if(pabu > changePabu ) {
			System.out.println("-");
		}else if(pabu < changePabu) {
			System.out.println("+");
		}else {
			System.out.println("0");
		}
	}
	
	static double getPower(double attack, double str, double critical, double damageRate, double  attackSpeed) {
		
		return attack * (1 + str / 100) * (     (1 - min(critical, 1)) + min(critical, 1) * (damageRate/100)   ) * (1 + (attackSpeed/100));
	}
	static double min(double critical, double one) {
		double output = critical / 100;
		if(output >= 1.0) {
			output = 1.0;
		}
		return output;
	}
}

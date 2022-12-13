package org.eni.encheres.utilitaire;

public class FicheMethodeBool {
	public static boolean bitToBool(int bit) {
		boolean vretour = false;
		if(bit == 1) {
			vretour = true;
		}
		return vretour;
	}
	public static int boolToBit(boolean bool) {
		int vretour = 0;
		if(bool) {
			vretour = 1;
		}
		return vretour;
	}
}

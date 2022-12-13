package org.eni.encheres.back.utilitaire;

public class FicheMethodeBool {
	public static boolean bitToBool(byte bit) {
		boolean vretour = false;
		if(bit == 1) {
			vretour = true;
		}
		return vretour;
	}
	public static byte boolToBit(boolean bool) {
		byte vretour = 0;
		if(bool) {
			vretour = 1;
		}
		return vretour;
	}
}

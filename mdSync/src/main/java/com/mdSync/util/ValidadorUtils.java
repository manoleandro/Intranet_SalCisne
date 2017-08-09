package com.mdSync.util;

public final class ValidadorUtils {
    public static boolean validaCpf(String valCPF) {
        boolean retorno = true;
        int soma = 0;
        int Resto = 0;
        int I = 0;
        if (valCPF == null || "".equals(valCPF.trim())) {
            retorno = true;
        }
        if (valCPF.length() != 11) {
            retorno = false;
        }
        soma = 0;
        for (I = 0; I <= 8; ++I) {
            soma+=Integer.valueOf(valCPF.substring(I, I + 1)) * (10 - I);
        }
        int Resultado = (soma - soma % 11) / 11;
        Resto = 11 - (soma - Resultado * 11);
        if (Resto == 10 || Resto == 11) {
            Resto = 0;
        }
        if (Resto != Integer.valueOf(valCPF.substring(9, 10))) {
            retorno = false;
        }
        soma = 0;
        for (I = 0; I <= 9; ++I) {
            soma+=Integer.valueOf(valCPF.substring(I, I + 1)) * (11 - I);
        }
        Resultado = (soma - soma % 11) / 11;
        Resto = 11 - (soma - Resultado * 11);
        if (Resto == 10 || Resto == 11) {
            Resto = 0;
        }
        if (Resto != Integer.valueOf(valCPF.substring(10, 11))) {
            retorno = false;
        }
        return retorno;
    }

    public static boolean validaCnpj(String valCNPJ) {
        boolean retorno = true;
        if (valCNPJ == null || valCNPJ.equals("")) {
            retorno = true;
        }
        if (valCNPJ.length() != 14) {
            retorno = false;
        }
        int iguais = 0;
        String carac = valCNPJ.substring(0, 1);
        for (int i = 1; i < valCNPJ.length(); ++i) {
            if (!carac.equals(valCNPJ.substring(i, i + 1))) continue;
            ++iguais;
        }
        if (iguais >= 12) {
            retorno = false;
        }
        int soma = 0;
        int resultado1 = 0;
        int resultado2 = 0;
        int[] Numero = new int[14];
        for (int j = 0; j < valCNPJ.length(); ++j) {
            Numero[j] = Integer.parseInt(valCNPJ.substring(j, j + 1));
        }
        soma = Numero[0] * 5 + Numero[1] * 4 + Numero[2] * 3 + Numero[3] * 2 + Numero[4] * 9 + Numero[5] * 8 + Numero[6] * 7 + Numero[7] * 6 + Numero[8] * 5 + Numero[9] * 4 + Numero[10] * 3 + Numero[11] * 2;
        resultado1 = (soma-=11 * (soma / 11)) == 0 || soma == 1 ? 0 : 11 - soma;
        if (resultado1 == Numero[12]) {
            soma = Numero[0] * 6 + Numero[1] * 5 + Numero[2] * 4 + Numero[3] * 3 + Numero[4] * 2 + Numero[5] * 9 + Numero[6] * 8 + Numero[7] * 7 + Numero[8] * 6 + Numero[9] * 5 + Numero[10] * 4 + Numero[11] * 3 + Numero[12] * 2;
            resultado2 = (soma-=11 * (soma / 11)) == 0 || soma == 1 ? 0 : 11 - soma;
            if (resultado2 != Numero[13]) {
                retorno = false;
            }
        } else {
            retorno = false;
        }
        return retorno;
    }
}
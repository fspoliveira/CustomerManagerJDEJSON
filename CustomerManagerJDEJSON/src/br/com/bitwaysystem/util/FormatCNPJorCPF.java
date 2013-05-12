package br.com.bitwaysystem.util;

/**
 * FormatCNPJorCPF é a classe Base que formata um CNPJ ou CPF
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */
public class FormatCNPJorCPF {

	/**
	 * Formata um CPF ou CNPJ a partir de uma mascara
	 * 
	 * @param valor
	 *            - String a ser formatada
	 * @param mascara
	 *            - Padrão da máscara
	 * @return saida - Retorna String saida formatada
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */
	public static String formatar(String valor, String mascara) {

		String dado = "";
		// remove caracteres nao numericos
		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (Character.isDigit(c)) {
				dado += c;
			}
		}

		int indMascara = mascara.length();
		int indCampo = dado.length();

		for (; indCampo > 0 && indMascara > 0;) {
			if (mascara.charAt(--indMascara) == '#') {
				indCampo--;
			}
		}

		String saida = "";
		for (; indMascara < mascara.length(); indMascara++) {
			saida += ((mascara.charAt(indMascara) == '#') ? dado
					.charAt(indCampo++) : mascara.charAt(indMascara));
		}
		return saida;
	}

	/**
	 * Formata um CPF
	 * 
	 * @param cpf
	 *            - CPF a ser formatado
	 * @return formatar - Retorna método que formata o CPF
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */
	public static String formatarCpf(String cpf) {
		while (cpf.length() < 11) {
			cpf = "0" + cpf;
		}
		return formatar(cpf, "###.###.###-##");
	}

	/**
	 * Formata um CPNJ
	 * 
	 * @param cnpj
	 *            - CPNJ a ser formatado
	 * @return formatar - Retorna método que formata o CPNJ
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */

	public static String formatarCnpj(String cnpj) {
		while (cnpj.length() < 14) {
			cnpj = "0" + cnpj;
		}
		return formatar(cnpj, "##.###.###/####-##");
	}
}
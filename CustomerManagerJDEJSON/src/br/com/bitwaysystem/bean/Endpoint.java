package br.com.bitwaysystem.bean;

/**
* Endpoint é a classe Bean que armazena o id do Endpoint
* e o endereço HTTP 
* @author      Fernando Santiago
* @version     %I%, %G%
* @since       1.0
* */

public class Endpoint {
	
	private int id;
	private String endpointAdress;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEndpointAdress() {
		return endpointAdress;
	}
	public void setEndpointAdress(String endpointAdress) {
		this.endpointAdress = endpointAdress;
	}
}
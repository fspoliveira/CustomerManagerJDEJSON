package br.com.bitwaysystem.bean;

/**
 * Entity é a classe Bean que armazena o idCliente, CPF/CNPJ e o endereço HTTP
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */

public class Entity {

	private String entityLongId;
	private String entityTaxId;
	private int entityId;

	public Entity() {
		super();
	}

	public Entity(int entityId) {
		super();
		this.entityId = entityId;
	}

	public Entity(String entityLongId, String entityTaxId, int entityId) {
		super();
		this.entityLongId = entityLongId;
		this.entityTaxId = entityTaxId;
		this.entityId = entityId;
	}

	public String getEntityLongId() {
		return entityLongId;
	}

	public void setEntityLongId(String entityLongId) {
		this.entityLongId = entityLongId;
	}

	public String getEntityTaxId() {
		return entityTaxId;
	}

	public void setEntityTaxId(String entityTaxId) {
		this.entityTaxId = entityTaxId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
}
package br.com.bitwaysystem.bean;

public class Entity {

	private int entityLongId;
	private int entityTaxId;
	private int entityId;

	public Entity() {
		super();
	}

	public Entity(int entityId) {
		super();
		this.entityId = entityId;
	}

	public Entity(int entityLongId, int entityTaxId, int entityId) {
		super();
		this.entityLongId = entityLongId;
		this.entityTaxId = entityTaxId;
		this.entityId = entityId;
	}

	public int getEntityLongId() {
		return entityLongId;
	}

	public void setEntityLongId(int entityLongId) {
		this.entityLongId = entityLongId;
	}

	public int getEntityTaxId() {
		return entityTaxId;
	}

	public void setEntityTaxId(int entityTaxId) {
		this.entityTaxId = entityTaxId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
}

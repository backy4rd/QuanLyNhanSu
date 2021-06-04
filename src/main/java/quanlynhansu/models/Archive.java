package quanlynhansu.models;

import java.sql.Date;

public class Archive {
	private int id;
	private String originalId;
	private String tableName;
	private String payload;
	private Date createdAt;

	public Archive(String originalId, String tableName, String payload) {
		this.originalId = originalId;
		this.tableName = tableName;
		this.payload = payload;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}

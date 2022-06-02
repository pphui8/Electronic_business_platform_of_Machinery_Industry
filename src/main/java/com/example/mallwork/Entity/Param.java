package com.example.mallwork.Entity;

import java.util.Date;
import java.util.List;

public class Param {
	private int id;
	private int parentId;
	private String name;
	private boolean status;
	private int sortOrder;
	private int level;
	private Date created;
	private Date updated;
	private List<Param> children;
	public List<Param> getChildren() {
		return children;
	}

	public void setChildren(List<Param> children) {
		this.children = children;
	}

	public Param() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Param(int id, int parentId, String name, boolean status, int sortOrder, int level, Date created,
                 Date updated) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.status = status;
		this.sortOrder = sortOrder;
		this.level = level;
		this.created = created;
		this.updated = updated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	
}

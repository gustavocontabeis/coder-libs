package br.com.codersistemas.libs.dto;

import lombok.Data;

@Data
public class ColumnDTO {//sss
	private String name;
	private int length;
	private boolean nullable;
	private int precision;
	private int scale;
	private String table;
	private boolean unique;
	private boolean updatable;
	private boolean insertable;
	private boolean fk;
	private boolean pk;

}

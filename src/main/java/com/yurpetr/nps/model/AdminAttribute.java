package com.yurpetr.nps.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean makeAdmin;

}

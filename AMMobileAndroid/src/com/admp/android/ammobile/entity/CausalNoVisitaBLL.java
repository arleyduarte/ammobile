package com.admp.android.ammobile.entity;

import java.util.ArrayList;
import com.amdp.android.basic.entity.APIEntity;
import com.amdp.android.basic.entity.DirectorBLL;

public class CausalNoVisitaBLL extends DirectorBLL {

	private static CausalNoVisitaBLL instance;

	private CausalNoVisitaBLL() {
		vItems = new ArrayList<APIEntity>();
	}

	public static CausalNoVisitaBLL getInstance() {
		if (instance == null) {
			instance = new CausalNoVisitaBLL();
		}
		return instance;
	}

}
